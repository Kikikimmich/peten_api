package com.kimmich.peten.controller;

import com.alibaba.fastjson.JSONObject;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.dto.article.ArticleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody String json){
        ArticleDTO dto = JSONObject.parseObject(json, ArticleDTO.class);
        System.out.println(json);
        return ApiResult.success();
    }
}
