package com.kimmich.peten.es.repository;

import com.kimmich.peten.es.domain.ESArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ESArticleRepository extends ElasticsearchRepository<ESArticle, String> {

    @Highlight(
            fields = {@HighlightField(name = "title"), @HighlightField(name = "content")},
            parameters = @HighlightParameters(preTags = {"<span style='color:red'>"}, postTags = {"</span>"}, numberOfFragments = 0)
    )
    List<SearchHit<ESArticle>> findByTitle(String title, Pageable pageable);

    @Highlight(
            fields = {@HighlightField(name = "title"), @HighlightField(name = "content")},
            parameters = @HighlightParameters(preTags = {"<span style='color:red'>"}, postTags = {"</span>"}, numberOfFragments = 0)
    )
    Page<SearchHit<ESArticle>> findByTitleLike(String title, Pageable pageable);

}
