package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.bo.article.ListBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.article.ArticleDTO;
import com.kimmich.peten.model.dto.content.ContentDTO;
import com.kimmich.peten.model.entity.Article;

import java.util.List;

public interface IArticleService extends IService<Article> {

    List<Article> getTopHop(Integer limit);

    Boolean exists(String id);

    ArticleBO getDetail(String id);

    void add(ArticleDTO dto, String authorId);

    ListBO getList(Long page, Long pageSize, String query);
}
