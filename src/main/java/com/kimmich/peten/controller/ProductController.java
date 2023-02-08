package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.entity.shop.Category;
import com.kimmich.peten.model.vo.product.ProductVO;
import com.kimmich.peten.service.ICategoryService;
import com.kimmich.peten.service.IProductService;
import io.swagger.annotations.Api;
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

    @PostMapping("/add")
    public ApiResult<Boolean> add(String name) {
        categoryService.add(name);
        return ApiResult.success(true);
    }
}
