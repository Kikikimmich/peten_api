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
@TableName("tbl_history")
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 被关注人id
     */
    @TableField("userId")
    private String userId;

    /**
     * 内容id
     */
    @TableField("contentId")
    private String contentId;

    /**
     * 内容type
     */
    @TableField("contentType")
    private Integer contentType;

    /**
     * 一天内点击量
     */
    @TableField("click")
    private Integer click;

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
