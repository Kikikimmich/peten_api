package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.dto.CommentDTO;
import com.kimmich.peten.model.entity.Comment;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.model.vo.CommentVO;

import java.util.List;


public interface ICommentService extends IService<Comment> {
    /**
     *
     *
     * @param topicid
     * @return {@link Comment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    Comment create(CommentDTO dto, User principal);
}
