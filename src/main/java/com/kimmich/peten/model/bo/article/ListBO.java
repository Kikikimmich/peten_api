package com.kimmich.peten.model.bo.article;

import com.kimmich.peten.model.common.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListBO {
    private PageInfo pageInfo;
    private List<ArticleBO> list;
}
