package com.kimmich.peten.controller;

import com.alibaba.fastjson.JSONObject;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.bo.article.ListBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.article.ArticleDTO;
import com.kimmich.peten.model.dto.article.SimpleArticleDTO;
import com.kimmich.peten.model.dto.content.ContentDTO;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import com.kimmich.peten.service.IArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{
    @Resource
    IArticleService articleService;

    @GetMapping("/get-top-hot")
    public ApiResult<List<ArticleBO>> getTopHot(){
        return ApiResult.success(articleService.getHot(20));
    }

    @GetMapping("/recommend")
    public ApiResult<List<SimpleArticleDTO>> recommend(String id){
        return ApiResult.success(articleService.recommend(id));
    }

    @GetMapping("")
    @ApiOperation("获取文章详情")
    public ApiResult<ArticleBO> getDetail(@RequestParam(name = "id") String id) {
        return ApiResult.success(articleService.getDetail(id));
    }

    @GetMapping("/list")
    @ApiOperation("获取文章列表")
    public ApiResult<ListBO> list(@RequestParam(name = "page", required = false, defaultValue = "1") Long page,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize,
                                  @RequestParam(name = "query", required = false, defaultValue = "") String query
                                  ){
        return ApiResult.success(articleService.getList(page, pageSize, query));
    }

    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody String json){
        ArticleDTO dto = JSONObject.parseObject(json, ArticleDTO.class);
//        System.out.println(json);
        articleService.add(dto, getLoginUserId());
        return ApiResult.success();
    }
}
