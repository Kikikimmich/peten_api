package com.kimmich.peten.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.mapper.PostMapper;
import com.kimmich.peten.mapper.TopicMapper;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.common.PageInfo;
import com.kimmich.peten.model.dto.group.GroupDTO;
import com.kimmich.peten.model.dto.post.EditPostDTO;
import com.kimmich.peten.model.dto.post.PostDTO;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import com.kimmich.peten.model.entity.group.Group;
import com.kimmich.peten.model.entity.group.Post;
import com.kimmich.peten.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service
public class PostServiceImpl extends ServiceImpl<TopicMapper, Post> implements IPostService {

    @Resource
    PostMapper postMapper;

    @Resource
    IGroupService groupService;

    @Resource
    IUserService userService;

    @Resource
    IImageURLService imageURLService;

    @Override
    @Transactional
    public void edit(EditPostDTO args) {
        if (StrUtil.isBlank(args.getContent()) && StrUtil.isBlank(args.getTitle()) && StrUtil.isBlank(args.getImages())) {
            throw new ApiException("帖子不能为空！");
        }
        Post post = Post.builder()
                .id(args.getId())
                .title(args.getTitle())
                .content(args.getContent())
                .images(args.getImages())
                .author(args.getAuthorId())
                .groupId(args.getGroupId())
                .modifyTime(new Date())
                .build();
        if (StrUtil.isBlank(args.getId())){
            post.setCreateTime(new Date());
        }
        saveOrUpdate(post);
    }

    @Override
    public ListPageDTO<PostDTO> getList(String groupId, Long page, Long pageSize) {

        Page<Post> postPage = new Page<>(page, pageSize);
        postPage = postMapper.getList(groupId, postPage);

        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .pageSize(pageSize)
                .totalRow(postPage.getTotal())
                .build();
        List<Post> postList = postPage.getRecords();
        List<PostDTO> dtoList = new ArrayList<>();

        // 封装图片和其他信息
        for (Post post : postList) {

            // 图片
            String images = post.getImages();
            Map<String, String> imageMap = new LinkedHashMap<>();
            if (StrUtil.isNotBlank(images)) {
                List<String> idList = Arrays.asList((images.split(";")));
                imageMap = imageURLService.getURLById(idList);
            }

            // 作者
            SimpleUserDTO author = userService.getSimpleInfo(post.getAuthor());

            // 圈子
            Group group = groupService.getById(post.getId());
            GroupDTO groupDTO = new GroupDTO();
            if (ObjectUtil.isNotNull(group)) {
                String url = imageURLService.getURLById(group.getCover());
                groupDTO = GroupDTO.builder()
                        .id(group.getId())
                        .name(group.getName())
                        .cover(url)
                        .build();
            }

            dtoList.add(PostDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .images(imageMap)
                    .content(post.getContent())
                    .author(author)
                    .comments("0")
                    .like("0")
                    .group(groupDTO)
                    .build());
        }

        return ListPageDTO.<PostDTO>builder()
                .list(dtoList)
                .pageInfo(pageInfo)
                .build();
    }
}
