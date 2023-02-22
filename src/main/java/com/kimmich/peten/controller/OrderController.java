package com.kimmich.peten.controller;

import com.alibaba.fastjson.JSONObject;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.common.ListDTO;
import com.kimmich.peten.model.dto.order.OrderDTO;
import com.kimmich.peten.model.vo.order.OrderVO;
import com.kimmich.peten.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController{

    @Resource
    IOrderService orderService;

    @GetMapping("/get-list")
    @ApiOperation("订单列表")
    public ApiResult<List<List<OrderVO>>> getList(){
        return ApiResult.success(orderService.getList(getLoginUserId()));
    }


    @PostMapping("/submit")
    @ApiOperation("提交订单")
    public ApiResult<Boolean> submit(@RequestBody String json){

        OrderDTO dto = JSONObject.parseObject(json, OrderDTO.class);
        orderService.submit(getLoginUserId(), dto);

        return ApiResult.success(true);
    }

}
