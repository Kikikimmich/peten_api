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
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "orderId")
    private String orderId;

    @TableField(value = "userId")
    private String userId;

    @TableField(value = "productId")
    private String productId;

    @TableField(value = "count")
    private Integer count;

    @TableField(value = "unitPrice")
    private String unitPrice;

    @TableField(value = "totalCost")
    private String totalCost;

    @TableField(value = "addressId")
    private String addressId;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "createTime")
    private Date createTime;

    @TableField(value = "modifyTime")
    private Date modifyTime;


}
