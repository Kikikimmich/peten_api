package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.dto.article.ArticleDTO;
import com.kimmich.peten.model.entity.Article;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT * FROM tbl_article")
    IPage<ArticleBO> getList(IPage<ArticleBO> pageInfo, String query);
}
