package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.entity.shop.Category;
import com.kimmich.peten.model.vo.product.ProductVO;
import com.kimmich.peten.model.vo.shoppingCart.ShoppingCartVO;
import com.kimmich.peten.service.ICategoryService;
import com.kimmich.peten.service.ICollectService;
import com.kimmich.peten.service.IProductService;
import com.kimmich.peten.service.IShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{

    @Resource
    ICategoryService categoryService;

    @Resource
    IProductService productService;

    @Resource
    IShoppingCartService shoppingCartService;

    @Resource
    ICollectService collectService;

    @PostMapping("/add-collect")
    @ApiOperation("添加收藏")
    public ApiResult<Boolean> addCollect(@RequestParam(name = "id") String productId){
        collectService.addCollect(getLoginUserId(), productId);
        return ApiResult.success(true);
    }

    @PostMapping("/add-shopping-cart")
    @ApiOperation("添加购物车")
    public ApiResult<ShoppingCartVO> addShoppingCart(@RequestParam(name = "id") String productId){

        return ApiResult.success(shoppingCartService.addShoppingCart(getLoginUserId(), productId));
    }

    @GetMapping("/get-detail")
    public ApiResult<ProductVO> getDetail(@RequestParam(name = "id") String id){
        ProductVO result = productService.getDetails(id);
        return ApiResult.success(result);
    }

    @GetMapping("/get-product")
    public ApiResult<ListPageDTO<ProductVO>> getProduct(@RequestParam(name = "categoryId", required = false, defaultValue = "") String categoryId,
                                                 @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                 @RequestParam(name = "pageSize", required = false, defaultValue = "20") Long pageSize){
        ListPageDTO<ProductVO> result = productService.getProduct(page, pageSize, categoryId);
        return ApiResult.success(result);
    }

    @GetMapping("/get-category")
    public ApiResult<List<Category>> getCategory(){
        return ApiResult.success(categoryService.getList());
    }

    @PostMapping("/add-category")
    public ApiResult<Boolean> addCategory(String name) {
        categoryService.add(name);
        return ApiResult.success(true);
    }
}
