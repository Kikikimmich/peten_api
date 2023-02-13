package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.entity.shop.ShoppingCart;
import com.kimmich.peten.model.vo.shoppingCart.ShoppingCartVO;

import java.util.List;

public interface IShoppingCartService extends IService<ShoppingCart> {
    void updateShoppingCart(String loginUserId, String productId, Integer count);

    void deleteShoppingCart(String id);

    List<ShoppingCartVO> getShoppingCart(String userId);

    ShoppingCartVO addShoppingCart(String userId, String productId);
}
