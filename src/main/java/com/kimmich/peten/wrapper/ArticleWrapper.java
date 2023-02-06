package com.kimmich.peten.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kimmich.peten.mapper.ArticleMapper;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.entity.Article;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ArticleWrapper {
    @Resource
    ArticleMapper articleMapper;

    public Boolean exists(String id){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(Article::getId)
                .eq(Article::getId, id);
        return articleMapper.selectCount(queryWrapper) != 0;
    }
}
