package com.kimmich.peten.model.vo.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private String id;
    private String orderId;
    private String productId;
    private String productName;
    private Integer count;
    private String unitPrice;
    private String totalCost;
    private String productCover;
    private Date createTime;

}
