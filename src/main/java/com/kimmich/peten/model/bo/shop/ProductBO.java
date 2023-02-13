package com.kimmich.peten.model.bo.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductBO {
    private String id;
    private String name;
    private String title;
    private String introduction;
    private String cover;
    private String categoryId;
    private BigDecimal price;
    private BigDecimal specialPrice;
    private int stock;
    private int sales;
    private int status;

    private String images;
}
