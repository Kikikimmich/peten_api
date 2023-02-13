package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.vo.shoppingCart.ShoppingCartVO;
import com.kimmich.peten.service.IShoppingCartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController extends BaseController{
    @Resource
    IShoppingCartService shoppingCartService;

    @PostMapping("/delete-shoppingCart")
    @ApiOperation("删除购物车")
    public ApiResult<Boolean> deleteShoppingCart(@RequestParam(name = "id") String id){
        shoppingCartService.deleteShoppingCart(id);
        return ApiResult.success(true);
    }

    @PostMapping("/update-shoppingCart")
    @ApiOperation("更新购物车商品数量")
    public ApiResult<Boolean> updateShoppingCart(@RequestParam(name = "productId") String productId,
                                                 @RequestParam(name = "count") Integer count){
        shoppingCartService.updateShoppingCart(getLoginUserId(), productId, count);
        return ApiResult.success(true);
    }

    @GetMapping("/get-shopping-cart")
    @ApiOperation("获取购物车内容")
    public ApiResult<List<ShoppingCartVO>> getShoppingCart(){
        List<ShoppingCartVO> shoppingCarts = shoppingCartService.
                getShoppingCart(getLoginUserId());
        return ApiResult.success(shoppingCarts);
    }
}
