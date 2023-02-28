package com.kimmich.peten.es.bo;

import com.kimmich.peten.es.domain.ESArticle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSearchBO {
    private PageInfoBO pageInfo;
    private List<ESArticle> list;
}
