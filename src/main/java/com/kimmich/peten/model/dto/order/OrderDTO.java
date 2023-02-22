package com.kimmich.peten.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private List<Order> orderList;

    // 地址信息
    private String addressId;

    // todo 其他需要完善的信息




    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order{
        private String productId;
        private Integer count;
        private String unitPrice;
        // 实际付款
        private String totalCost;
    }


}
