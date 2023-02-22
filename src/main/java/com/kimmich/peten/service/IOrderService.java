package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.common.ListDTO;
import com.kimmich.peten.model.dto.order.OrderDTO;
import com.kimmich.peten.model.entity.shop.Order;
import com.kimmich.peten.model.vo.order.OrderVO;

import java.util.List;

public interface IOrderService extends IService<Order> {

    List<List<OrderVO>> getList(String userId);

    void submit(String userId, OrderDTO dto);
}
