package com.kimmich.peten.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 内容
     */
    @NotBlank(message = "内容不可以为空")
    @TableField(value = "content")
    private String content;


    /**
     * 作者ID
     */
    @TableField("userId")
    private String userId;

    /**
     * 文章ID
     */
    @TableField("articleId")
    private String articleId;

    /**
     * 回复评论
     */
    @TableField("rootCommentId")
    private String rootCommentId;

    /**
     * 话题对象
     */
    @TableField("type")
    private int type;

    /**
     * 创建时间
     */
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modifyTime", fill = FieldFill.UPDATE)
    private Date modifyTime;
}