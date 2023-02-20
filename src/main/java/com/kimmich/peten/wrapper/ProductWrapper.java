package com.kimmich.peten.wrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.ProductMapper;
import com.kimmich.peten.model.entity.shop.Product;
import com.kimmich.peten.utils.ListUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductWrapper extends ServiceImpl<ProductMapper, Product> {
    @Resource
    ProductMapper productMapper;


    public boolean updateStock(String productId, Integer count){
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set((count != null && count >= 0), Product::getStock, count)
                .eq(Product::getId, productId);

        return update(updateWrapper);
    }

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
