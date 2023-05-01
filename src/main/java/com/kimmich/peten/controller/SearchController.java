package com.kimmich.peten.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.content.ContentResultDTO;
import com.kimmich.peten.model.vo.PostVO;
import com.kimmich.peten.model.vo.product.ProductVO;
import com.kimmich.peten.service.IPostService;
import com.kimmich.peten.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

    @Resource
    private IPostService postService;

    @Resource
    SearchService searchService;

    @Deprecated
    @GetMapping("/common")
    public ApiResult<Object> search(@RequestParam("query") String query,
                                    @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        Object o = searchService.commonSearch(query, type, page, pageSize);
        return ApiResult.success();
    }

    @GetMapping("/goods")
    @ApiOperation("搜索商品")
    public ApiResult<ListPageDTO<ProductVO>> searchGoods(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                         @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                         @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
//        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(page, pageSize));
        ListPageDTO<ProductVO> result = searchService.searchGoods(keyword, page, pageSize);
        return ApiResult.success(result);
    }

    @GetMapping("/content")
    public ApiResult<ListPageDTO<ArticleBO>> searchContent(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                           @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
//        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(page, pageSize));
        ListPageDTO<ArticleBO> result = searchService.searchContent(keyword, page, pageSize);
        return ApiResult.success(result);
    }

}
