package com.kimmich.peten.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kimmich.peten.mapper.ShoppingCartMapper;
import com.kimmich.peten.model.entity.shop.ShoppingCart;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ShoppingCartWrapper {
    @Resource
    ShoppingCartMapper shoppingCartMapper;

    public List<ShoppingCart> getShoppingCart(String userId){
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ShoppingCart::getUserId, userId)
                .orderByDesc(ShoppingCart::getCreateTime);

        return shoppingCartMapper.selectList(queryWrapper);
    }

    public ShoppingCart getShoppingCart(String userId, String productId){
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ShoppingCart::getUserId, userId)
                .eq(ShoppingCart::getProductId, productId);
        return shoppingCartMapper.selectOne(queryWrapper);
    }

}
