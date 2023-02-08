package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.entity.shop.Product;
import com.kimmich.peten.model.vo.product.ProductVO;

import java.util.List;

public interface IProductService extends IService<Product> {
    ListPageDTO<ProductVO> getProduct(Long page, Long pageSize, String categoryId);
}
