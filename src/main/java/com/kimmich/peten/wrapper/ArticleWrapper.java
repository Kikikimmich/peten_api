package com.kimmich.peten.wrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.mapper.ArticleMapper;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.entity.Article;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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


    public Page<Article> getList(Long page, Long pageSize, String author){

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(author)){
            queryWrapper.lambda().eq(Article::getAuthor, author);
        }
        Page<Article> pageInfo = new Page<>(page, pageSize);
        return articleMapper.selectPage(pageInfo, queryWrapper);
    }
}
