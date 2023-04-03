package com.kimmich.peten.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.CreateTopicDTO;
import com.kimmich.peten.model.dto.post.PostDTO;
import com.kimmich.peten.model.entity.Post;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.model.vo.PostVO;
import com.kimmich.peten.service.IPostService;
import com.kimmich.peten.service.IUserService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.kimmich.peten.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    @Resource
    private IPostService iPostService;
    @Resource
    private IUserService umsUserService;

    @GetMapping("/list")
    public ApiResult<Object> list(@RequestParam(value = "groupId", required = false, defaultValue = "") String groupId,
                                        @RequestParam(value = "page", required = false, defaultValue = "1")  Long page,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        ListPageDTO<PostDTO> list = iPostService.getList(groupId, page, pageSize);
        return ApiResult.success(null);
    }

}
