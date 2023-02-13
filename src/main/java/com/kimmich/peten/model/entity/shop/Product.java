package com.kimmich.peten.model.entity.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "title")
    private String title;

    @TableField(value = "introduction")
    private String introduction;

    @TableField(value = "cover")
    private String cover;

    @TableField(value = "categoryId")
    private String categoryId;

    @TableField(value = "price")
    private BigDecimal price;

    @TableField(value = "specialPrice")
    private BigDecimal specialPrice;

    @TableField(value = "stock")
    private int stock;

    @TableField(value = "sales")
    private int sales;

    @TableField(value = "limitCount")
    private int limitCount;

    @TableField(value = "images")
    private String images;

    @TableField(value = "status")
    private int status;

    @TableField(value = "createTime")
    private String createTime;

    @TableField(value = "modifyTime")
    private String modifyTime;

}
