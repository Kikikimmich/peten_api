package com.kimmich.peten.model.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    // 文章内容
    private String content;
    // 封面
    private String cover;
    // 话题标签
    private List<String> tags;
    // 标题
    private String title;
}
