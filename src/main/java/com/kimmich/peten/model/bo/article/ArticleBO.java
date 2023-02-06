package com.kimmich.peten.model.bo.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBO {

    private String id;
    // 文章内容
    private String content;
    // 封面
    private String cover;
    // 标题
    private String title;
    // 作者
    private String author;
    // 发布时间
    private String createTime;
}

