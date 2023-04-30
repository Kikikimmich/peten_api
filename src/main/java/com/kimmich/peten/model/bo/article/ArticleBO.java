package com.kimmich.peten.model.bo.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private SimpleUserDTO authorInfo;

    // 观看
    private Integer views;

    // 点赞
    private Integer likes;

    // 收藏
    private Integer favorites;

    // 评论
    private Integer comments;

    // 发布时间
    private String createTime;
}

