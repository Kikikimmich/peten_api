package com.kimmich.peten.es.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kimmich.peten.es.domain.ESArticle;
import com.kimmich.peten.es.repository.ESArticleRepository;
import com.kimmich.peten.es.service.ESArticleService;
import com.kimmich.peten.mapper.ArticleMapper;
import com.kimmich.peten.model.entity.Article;
import com.kimmich.peten.service.IArticleService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/es")
public class ESTestController {

    @Resource
    ESArticleService esArticleService;

    @Resource
    IArticleService articleService;

    @Resource
    ArticleMapper articleMapper;

    @Autowired
    ESArticleRepository esArticleRepository;

    @Autowired
    RestHighLevelClient highLevelClient;


    @GetMapping("/save")
    public String saveArticle() {

    QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
    queryWrapper.lambda().select(Article::getId, Article::getTitle);

        List<Article> articles = articleMapper.selectList(queryWrapper);

        for (Article article : articles) {
            ESArticle esArticle = ESArticle.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content("")
                    .build();
            esArticleRepository.save(esArticle);
//            esArticleRepository.refresh();
        }

        return esArticleRepository.count() + "";
    }

    @GetMapping("/get")
    public String get() {
        Iterable<ESArticle> all = esArticleRepository.findAll();
        System.out.println("");
        return null;
    }

    @GetMapping("/search")
    public Object search(String query) {
        Pageable pageable = PageRequest.of(1, 3);
         return esArticleRepository.findByTitle(query, pageable);
    }

    @GetMapping("/searchV2")
    public Object searchV2(String query) {
        Pageable pageable = PageRequest.of(1, 10);
        Page<SearchHit<ESArticle>> hits = esArticleRepository.findByTitleLike(query, pageable);
        return hits;
    }

    @GetMapping("/searchV3")
    public Object searchV3(String query, Integer page, Integer pageSize) {
        return esArticleService.searchByTitle(query, page, pageSize);
    }

    @GetMapping("/searchV4")
    public Object searchV4(String id) {
        return esArticleRepository.findById(id);
//        esArticleRepository.

    }

}
