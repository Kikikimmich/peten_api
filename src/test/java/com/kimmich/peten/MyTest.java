package com.kimmich.peten;

import com.kimmich.peten.model.dto.order.OrderDTO;
import com.kimmich.peten.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collections;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Resource
    IOrderService orderService;


    @Test
    public void  test(){
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

        for (int i = 0; i < 1000; i++){
            new Thread(()->{
                orderService.submit(userId, dto);
            }).start();
        }
    }
}
