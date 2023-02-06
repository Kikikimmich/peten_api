package com.kimmich.peten.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kimmich.peten.mapper.CommentMapper;
import com.kimmich.peten.model.entity.Article;
import com.kimmich.peten.model.entity.Comment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CommentWrapper {
    @Resource
    CommentMapper commentMapper;

    public Boolean exists(String id){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(Comment::getId)
                .eq(Comment::getId, id);
        return commentMapper.selectCount(queryWrapper) != 0;
    }
}
