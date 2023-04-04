package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.post.PostDTO;
import com.kimmich.peten.service.IPostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    @Resource
    private IPostService postService;

    @GetMapping("/list")
    public ApiResult< ListPageDTO<PostDTO>> list(@RequestParam(value = "groupId", required = false, defaultValue = "") String groupId,
                                        @RequestParam(value = "page", required = false, defaultValue = "1")  Long page,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        return ApiResult.success(postService.getList(groupId, page, pageSize));
    }

}
