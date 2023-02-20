package com.kimmich.peten.controller;

import com.kimmich.peten.model.dto.order.OrderDTO;
import com.kimmich.peten.service.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    IOrderService orderService;

    @GetMapping("")
    public void test(){
        String userId = "1622243684553383938";

        OrderDTO.Order order = OrderDTO.Order.builder()
                .productId("1")
                .count(1)
                .totalCost("99")
                .build();

        OrderDTO dto = OrderDTO.builder()
                .orderList(Collections.singletonList(order))
                .addressId("123")
                .build();

        for (int i = 0; i < 100; i++){
            new Thread(()->{
                orderService.submit(userId, dto);
            }).start();
        }
    }
}
