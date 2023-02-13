package com.kimmich.peten.model.vo.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVO {
    private String id;
    private String productId;
    private String productName;
    private String productCover;
    private BigDecimal price;
    private BigDecimal specialPrice;
    private Integer count;
    // 限购
    private Integer limitCount;
}
