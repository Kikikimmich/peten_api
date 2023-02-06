package com.kimmich.peten.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.emun.CommentConst;
import com.kimmich.peten.mapper.CommentMapper;
import com.kimmich.peten.model.bo.comment.CommentBO;
import com.kimmich.peten.model.common.ListDTO;
import com.kimmich.peten.model.dto.comment.CommentDTO;
import com.kimmich.peten.model.entity.Comment;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.model.vo.comment.CommentVO;
import com.kimmich.peten.model.vo.comment.UserVO;
import com.kimmich.peten.service.IArticleService;
import com.kimmich.peten.service.ICommentService;
import com.kimmich.peten.service.IUserService;
import com.kimmich.peten.utils.CommonUtil;
import com.kimmich.peten.utils.ListUtil;
import com.kimmich.peten.wrapper.CommentWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Resource
    CommentMapper commentMapper;

    @Resource
    CommentWrapper commentWrapper;

    @Resource
    IArticleService articleService;
    @Resource
    IUserService userService;

    @Override
    @Transactional
    public void add(CommentDTO dto, String userId) {
        if (!CommonUtil.notNull(dto.getArticleId(), dto.getContent(), userId)) {
            throw new ApiException("参数不完整！");
        }
        if (!articleService.exists(dto.getArticleId())) {
            throw new ApiException("文章不存在或已被删除！");
        }
        if (StrUtil.isNotBlank(dto.getRootComment()) && !exists(dto.getRootComment())) {
            throw new ApiException("评论不存在或已被删除！");
        }
        Comment comment = Comment.builder()
                .articleId(dto.getArticleId())
                .content(dto.getContent())
                .userId(userId)
                .rootCommentId(dto.getRootComment())
                .createTime(new Date())
                .type(CommentConst.TYPE_ARTICLE)
                .build();
        this.save(comment);
    }

    @Override
    public ListDTO<CommentVO> getCommentsByArticleId(String articleId) {
        List<CommentBO> list = commentMapper.getCommentsByTopicID(articleId);

        Map<String, CommentBO> replyMap = list.stream().collect(Collectors.toMap(CommentBO::getId, Function.identity()));

        Map<String, List<CommentBO>> replyListMap = new LinkedHashMap<>();
        for (CommentBO comment : list) {
            // 没有回复对象的被认为是一级评论
            if (StrUtil.isBlank(comment.getRootCommentId())) {
                replyListMap.put(comment.getId(), new ArrayList<>());
            } else {
                List<CommentBO> replyList = new ArrayList<>();
                replyList.add(comment);
                String root = comment.getRootCommentId();
                while (StrUtil.isNotBlank(root)) {
                    CommentBO commentBO = replyMap.get(root);
                    // 已经到了根节点
                    if (StrUtil.isBlank(commentBO.getRootCommentId())) {
                        List<CommentBO> commentList = replyListMap.get(commentBO.getId());
                        if (ListUtil.isEmpty(commentList) ) {
                            replyListMap.put(commentBO.getId(), replyList);
                        } else {
                            commentList.addAll(replyList);
                            replyListMap.put(commentBO.getId(), commentList);
                        }
                        break;
                    } else {
                        root = commentBO.getRootCommentId();
                    }
                }
            }
        }

        List<CommentVO> result = new ArrayList<>();
        for (Map.Entry<String, List<CommentBO>> entry : replyListMap.entrySet()) {
            CommentBO rootComment = replyMap.get(entry.getKey());
            CommentVO commentVO = CommentVO.builder()
                    .id(rootComment.getId())
                    .content(rootComment.getContent())
                    .articleId(rootComment.getArticleId())
                    .from(getUserById(rootComment.getUserId()))
                    .createTime(rootComment.getCreateTime())
                    .build();

            List<CommentBO> replyBOList = entry.getValue();
            List<CommentVO> replyVOList = new ArrayList<>();
            for (CommentBO replyBO : replyBOList) {
                replyVOList.add(CommentVO.builder()
                        .id(replyBO.getId())
                        .content(replyBO.getContent())
                        .articleId(replyBO.getArticleId())
                        .from(getUserById(replyBO.getUserId()))
                        .to(getUserById(replyMap.get(replyBO.getRootCommentId()).getUserId()))
                        .rootCommentId(replyBO.getRootCommentId())
                        .createTime(replyBO.getCreateTime())
                        .build());
            }
            commentVO.setReply(replyVOList);

            result.add(commentVO);
        }
        return ListDTO.<CommentVO>builder().list(result).build();
    }

    private UserVO getUserById(String id) {
        User user = userService.getUserByUserId(id);
        if (ObjectUtil.isNull(user)){
            return UserVO.builder()
                    .id("-1")
                    .userName("用户已注销")
                    .build();
        }
        // todo
        return UserVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .build();
    }

    private Boolean exists(String rootComment) {
        return commentWrapper.exists(rootComment);
    }

}
