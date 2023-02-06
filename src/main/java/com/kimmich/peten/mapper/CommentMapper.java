package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimmich.peten.model.bo.comment.CommentBO;
import com.kimmich.peten.model.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentMapper extends BaseMapper<Comment> {


    List<CommentBO> getCommentsByTopicID(@Param("articleId") String articleId);
}
