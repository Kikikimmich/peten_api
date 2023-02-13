package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kimmich.peten.model.bo.shop.ProductBO;
import com.kimmich.peten.model.entity.shop.Product;
import com.kimmich.peten.model.vo.product.ProductVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    ProductBO getProductById(@Param("id") String id);

    IPage<ProductVO> getProductByCategoryId(IPage<ProductVO> pageInfo, @Param("categoryId") String categoryId);
}
