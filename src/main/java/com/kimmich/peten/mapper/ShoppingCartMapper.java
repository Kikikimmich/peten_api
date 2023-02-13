package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimmich.peten.model.entity.shop.ShoppingCart;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
