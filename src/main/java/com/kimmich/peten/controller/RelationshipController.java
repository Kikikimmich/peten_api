package com.kimmich.peten.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.common.exception.ApiAsserts;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.service.IFollowService;
import com.kimmich.peten.service.IUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.kimmich.peten.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/relationship")
public class RelationshipController extends BaseController {

    @Resource
    private IFollowService followService;

    @Resource
    private IUserService userService;

    @PostMapping("/follow")
    public ApiResult<Object> follow(String follow) {
        String loginUserId = getLoginUserId();
        followService.follow(loginUserId, follow);
        return ApiResult.success(null, "关注成功");
    }

    @PostMapping("/unfollow")
    public ApiResult<Object> unfollow(String follow) {
        String loginUserId = getLoginUserId();
        followService.unfollow(loginUserId, follow);
        return ApiResult.success(null, "操作成功");
    }

}
