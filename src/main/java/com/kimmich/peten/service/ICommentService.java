package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.common.ListDTO;
import com.kimmich.peten.model.dto.comment.CommentDTO;
import com.kimmich.peten.model.entity.Comment;
import com.kimmich.peten.model.vo.comment.CommentVO;

import java.util.List;


public interface ICommentService extends IService<Comment> {

    void add(CommentDTO dto, String userId);

    ListDTO<CommentVO> getCommentsByArticleId(String articleId);
}
