package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.dto.order.OrderDTO;
import com.kimmich.peten.model.entity.shop.Order;

public interface IOrderService extends IService<Order> {
    void submit(String userId, OrderDTO dto);
}
