package com.kimmich.peten.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.emun.OrderConst;
import com.kimmich.peten.mapper.OrderMapper;
import com.kimmich.peten.model.common.ListDTO;
import com.kimmich.peten.model.dto.order.OrderDTO;
import com.kimmich.peten.model.entity.shop.Order;
import com.kimmich.peten.model.vo.order.OrderVO;
import com.kimmich.peten.model.vo.product.ProductVO;
import com.kimmich.peten.service.IOrderService;
import com.kimmich.peten.service.IProductService;
import com.kimmich.peten.wrapper.OrderWrapper;
import com.kimmich.peten.wrapper.ProductWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSInput;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Resource
    OrderWrapper orderWrapper;

    @Resource
    IProductService productService;

    @Resource
    ProductWrapper productWrapper;


    @Override
    public List<List<OrderVO>> getList(String userId) {
        if (StrUtil.isBlank(userId)){
            throw new ApiException("参数异常");
        }
        // 1. 获取订单
        List<Order> orderList = orderWrapper.getList(userId);

        // Map<String, List<Order>> orderMap = orderList.stream().collect(Collectors.groupingBy(Order::getOrderId));
        Map<String, List<Order>> orderMap = new LinkedHashMap<>();
        for (Order order : orderList) {
            List<Order> orders = orderMap.get(order.getOrderId());
            if (ObjectUtil.isNull(orders)){
                orders = new ArrayList<>();
            }
            orders.add(order);
            orderMap.put(order.getOrderId(), orders);
        }

        List<List<OrderVO>> result = new ArrayList<>();

        // 2. 获取商品信息
        for (Map.Entry<String, List<Order>> entry : orderMap.entrySet()){
            List<Order> orders = entry.getValue();
            List<String> productIdList = orders.stream().map(Order::getProductId).collect(Collectors.toList());
            List<ProductVO> product = productService.getProduct(productIdList);
            Map<String, ProductVO> productMap = product.stream().collect(Collectors.toMap(ProductVO::getId, Function.identity()));

            List<OrderVO> voList = new ArrayList<>();
            for (Order order : orders) {
                ProductVO productVO = productMap.get(order.getProductId());

                OrderVO orderVO = OrderVO.builder()
                        .id(order.getId())
                        .orderId(order.getOrderId())
                        .productId(order.getProductId())
                        .createTime(order.getCreateTime())
                        .count(order.getCount())
                        .unitPrice(order.getUnitPrice())
                        .totalCost(order.getTotalCost())
                        .productName(productVO.getName())
                        .productCover(productVO.getCover())
                        .build();
                voList.add(orderVO);
            }
            result.add(voList);
        }

        return result;
    }

    @Override
    @Transactional
    public synchronized void submit(String userId, OrderDTO dto) {
        if (StrUtil.isBlank(userId) || ObjectUtil.isNull(dto)){
            throw new ApiException("参数异常");
        }
        if (CollUtil.isEmpty(dto.getOrderList())){
            throw new ApiException("订单不能为空");
        }
        if (StrUtil.isBlank(dto.getAddressId())){
            throw new ApiException("地址不能为空");
        }

        // 生成订单号，整个事务的订单号
        DefaultIdentifierGenerator generator = new DefaultIdentifierGenerator();
        Long orderId = generator.nextId(new Object());

        List<OrderDTO.Order> orderList = dto.getOrderList();
        for (OrderDTO.Order order : orderList) {
            // 1. 扣库存
            doSubmit(order);

            // 2. 生成订单
            generateOrder(userId, dto.getAddressId(), orderId.toString(), order);
        }

        // todo 生成日志信息
        log.info("生成订单成功：" + orderId);
    }



    /**
     *  生成订单信息
     * */
    private void generateOrder(String userId, String addressId, String orderId, OrderDTO.Order params){
        Order order = Order.builder()
                .orderId(orderId)
                .userId(userId)
                .productId(params.getProductId())
                .count(params.getCount())
                .unitPrice(params.getUnitPrice())
                .totalCost(params.getTotalCost())
                .addressId(addressId)
                .status(OrderConst.TYPE_UNPAID)
                .createTime(new Date())
                .build();
        save(order);
    }


    private void doSubmit(OrderDTO.Order order){
        doSubmit(order, 10);
    }

    /**
     *  扣库存， 写入订单库
     * */
    private void doSubmit(OrderDTO.Order order, Integer retryCount){

        if (retryCount <= 0){
            throw new ApiException("购买失败！");
        }

        // 1. 查库存
       Integer stock = productService.getStockForUpdate(order.getProductId());

       if (stock - order.getCount() < 0){
           throw new ApiException("库存不足");
       }

       // 2. 扣库存
        boolean update = productWrapper.updateStock(order.getProductId(), stock - order.getCount());
       // 本业务场景下，基本不涉及高并发购买，认为每一次购买都是可以成功的
       if (!update){
            doSubmit(order, retryCount -1 );
        }
    }
}
