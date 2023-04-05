package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.bo.article.ListBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.content.ContentDTO;
import com.kimmich.peten.service.ContentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Resource
    ContentService contentService;

    @GetMapping("/list")
    @ApiOperation("获取内容列表")
    public ApiResult<ListPageDTO<ContentDTO>> list(@RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize,
                                                   @RequestParam(name = "contentType", required = false, defaultValue = "1") Integer contentType,
                                                   @RequestParam(name = "author", required = false, defaultValue = "") String author
    ){
        return ApiResult.success(contentService.getList(page, pageSize, contentType, author));
    }
}
