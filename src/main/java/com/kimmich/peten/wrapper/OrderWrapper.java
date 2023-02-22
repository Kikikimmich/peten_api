package com.kimmich.peten.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kimmich.peten.mapper.OrderMapper;
import com.kimmich.peten.model.entity.shop.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class OrderWrapper {

    @Resource
    OrderMapper orderMapper;

    public List<Order> getList(String userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Order::getUserId, userId)
                .orderByDesc(Order::getCreateTime);
        return orderMapper.selectList(queryWrapper);
    }
}
