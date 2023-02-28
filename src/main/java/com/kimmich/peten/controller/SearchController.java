package com.kimmich.peten.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.vo.PostVO;
import com.kimmich.peten.service.IPostService;
import com.kimmich.peten.service.SearchService;
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

    @GetMapping("/common")
    public ApiResult<Object> search(@RequestParam("query") String query,
                                    @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        Object o = searchService.search(query, type, page, pageSize);
        return ApiResult.success();
    }

    @GetMapping
    @Deprecated
    public ApiResult<Page<PostVO>> searchList(@RequestParam("keyword") String keyword,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ApiResult.success(results);
    }

}
