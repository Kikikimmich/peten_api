package com.kimmich.peten.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_article")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("cover")
    private String cover;

    @TableField("author")
    private String author;

    /*
    *  todo
    *
    *  Hotness = (log10(views) + likes + favorites + comments) / age
    *
    *  ALTER TABLE table_name ADD column_name datatype DEFAULT default_value;
    * */

    // 热度
    @TableField("hots")
    private Double hots;

    // 观看
    @TableField("views")
    private Integer views;

    // 点赞
    @TableField("likes")
    private Integer likes;

    // 收藏
    @TableField("favorites")
    private Integer favorites;

    // 评论
    @TableField("comments")
    private Integer comments;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;
}
