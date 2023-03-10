package com.kimmich.peten.model.vo.product;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private String id;
    private String name;
    private String title;
    private String introduction;
    private String cover;
    private String categoryId;
    private BigDecimal price;
    private BigDecimal specialPrice;
    private Integer stock;
    private Integer sales;

    private Integer limitCount;

    private Integer status;

    private List<String> images;
}
