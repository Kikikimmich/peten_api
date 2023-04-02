package com.kimmich.peten.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.content.ContentResultDTO;
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

    @Deprecated
    @GetMapping("/common")
    public ApiResult<Object> search(@RequestParam("query") String query,
                                    @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        Object o = searchService.commonSearch(query, type, page, pageSize);
        return ApiResult.success();
    }

    @GetMapping("/content")
    @Deprecated
    public ApiResult<ListPageDTO<ContentResultDTO>> searchContent(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
//        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(page, pageSize));
        ListPageDTO<ContentResultDTO> result = searchService.searchContent(keyword, page, pageSize);
        return ApiResult.success(result);
    }

}
