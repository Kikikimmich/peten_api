package com.kimmich.peten.controller;

import com.kimmich.peten.es.domain.ESArticle;
import com.kimmich.peten.es.repository.ESArticleRepository;
import com.kimmich.peten.es.service.ESArticleService;
import com.kimmich.peten.service.IOrderService;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    ESArticleService esArticleService;

    @Resource
    IOrderService orderService;

    @Autowired
    private ElasticsearchRestTemplate template;


    @Autowired
    ESArticleRepository esArticleRepository;

    @Autowired
    RestHighLevelClient highLevelClient;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @GetMapping("")
    public boolean test() throws IOException {
        GetRequest getRequest = new GetRequest("article", "66");
        GetResponse getResponse = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getIndex());
        System.out.println(getResponse.toString());
        return true;
    }

    @GetMapping("/save")
    public String save() {
        for (int i = 0; i < 100; i++) {

            ESArticle article = ESArticle.builder()
                    .id("666" + i)
                    .title("这是一个标题这是一个标题这是一个标题这是一个标题" + i)
                    .content("这是内容")
                    .build();
            esArticleRepository.save(article);
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
        esArticleService.searchByTitle(query, page, pageSize);
        return null;
    }

}
