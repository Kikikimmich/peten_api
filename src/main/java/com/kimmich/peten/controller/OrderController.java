package com.kimmich.peten.controller;

import com.alibaba.fastjson.JSONObject;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.dto.order.OrderDTO;
import com.kimmich.peten.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController{

    @Resource
    IOrderService orderService;


    @PostMapping("/submit")
    @ApiOperation("提交订单")
    public ApiResult<Boolean> submit(@RequestBody String json){

        OrderDTO dto = JSONObject.parseObject(json, OrderDTO.class);
        orderService.submit(getLoginUserId(), dto);

        return ApiResult.success(true);
    }

}
