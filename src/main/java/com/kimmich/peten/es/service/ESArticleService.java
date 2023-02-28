package com.kimmich.peten.es.service;

import com.kimmich.peten.es.bo.ArticleSearchBO;
import com.kimmich.peten.es.bo.PageInfoBO;
import com.kimmich.peten.es.domain.ESArticle;
import com.kimmich.peten.es.repository.ESArticleRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ESArticleService {

    @Autowired
    private ESArticleRepository articleRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;


    public ArticleSearchBO searchByTitle(String keyword, Integer page, Integer pageSize){
        //需要查询的字段
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", keyword));

        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("title"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
//                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
                .withPageable(PageRequest.of(page, pageSize))
                .build();

        SearchHits<ESArticle> hits = elasticsearchRestTemplate.search(searchQuery, ESArticle.class);

        List<ESArticle> list = new ArrayList<>();
        // 修改高亮内容
        for (SearchHit<ESArticle> hit : hits) {
            List<String> highlightField = hit.getHighlightField("title");
            if (!highlightField.isEmpty()){
                ESArticle article = hit.getContent();
                article.setTitle(highlightField.get(0));

                list.add(article);
            }
        }

        return ArticleSearchBO.builder()
                .pageInfo(PageInfoBO.builder()
                        .page(Long.valueOf(page))
                        .pageSize(Long.valueOf(pageSize))
                        .totalRow(hits.getTotalHits())
                        .build())
                .list(list)
                .build();
    }

    public void save(ESArticle article) {
        articleRepository.save(article);
    }

    public void delete(String id) {
        articleRepository.deleteById(id);
    }

    public void delete(ESArticle article) {
        articleRepository.delete(article);
    }
}
