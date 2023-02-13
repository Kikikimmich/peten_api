package com.kimmich.peten.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kimmich.peten.mapper.ProductMapper;
import com.kimmich.peten.model.entity.shop.Product;
import com.kimmich.peten.model.vo.product.ProductVO;
import com.kimmich.peten.utils.ListUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductWrapper {
    @Resource
    ProductMapper productMapper;

    public List<Product> getProduct(List<String> productId){
        if (ListUtil.isEmpty(productId)){
            return new ArrayList<>();
        }
        return productMapper.selectBatchIds(productId);
    }

    public Product getProduct(String productId){
        return productMapper.selectById(productId);
    }
}
