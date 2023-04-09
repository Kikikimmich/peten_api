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

//    @Resource
//    private IFollowService bmsFollowService;
//
//    @Resource
//    private IUserService umsUserService;
//
//    @GetMapping("/subscribe/{userId}")
//    public ApiResult<Object> handleFollow(@RequestHeader(value = USER_NAME) String userName
//            , @PathVariable("userId") String parentId) {
//        User user = umsUserService.getUserByUsername(userName);
//        if (parentId.equals(user.getId())) {
//            ApiAsserts.fail("您脸皮太厚了，怎么可以关注自己呢 😮");
//        }
//        Follow one = bmsFollowService.getOne(
//                new LambdaQueryWrapper<Follow>()
//                        .eq(Follow::getParentId, parentId)
//                        .eq(Follow::getFollowerId, user.getId()));
//        if (!ObjectUtils.isEmpty(one)) {
//            ApiAsserts.fail("已关注");
//        }
//
//        Follow follow = new Follow();
//        follow.setParentId(parentId);
//        follow.setFollowerId(user.getId());
//        bmsFollowService.save(follow);
//        return ApiResult.success(null, "关注成功");
//    }
//
//    @GetMapping("/unsubscribe/{userId}")
//    public ApiResult<Object> handleUnFollow(@RequestHeader(value = USER_NAME) String userName
//            , @PathVariable("userId") String parentId) {
//        User user = umsUserService.getUserByUsername(userName);
//        Follow one = bmsFollowService.getOne(
//                new LambdaQueryWrapper<Follow>()
//                        .eq(Follow::getParentId, parentId)
//                        .eq(Follow::getFollowerId, user.getId()));
//        if (ObjectUtils.isEmpty(one)) {
//            ApiAsserts.fail("未关注！");
//        }
//        bmsFollowService.remove(new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, parentId)
//                .eq(Follow::getFollowerId, user.getId()));
//        return ApiResult.success(null, "取关成功");
//    }
//
//    @GetMapping("/validate/{topicUserId}")
//    public ApiResult<Map<String, Object>> isFollow(@RequestHeader(value = USER_NAME) String userName
//            , @PathVariable("topicUserId") String topicUserId) {
//        User user = umsUserService.getUserByUsername(userName);
//        Map<String, Object> map = new HashMap<>(16);
//        map.put("hasFollow", false);
//        if (!ObjectUtils.isEmpty(user)) {
//            Follow one = bmsFollowService.getOne(new LambdaQueryWrapper<Follow>()
//                    .eq(Follow::getParentId, topicUserId)
//                    .eq(Follow::getFollowerId, user.getId()));
//            if (!ObjectUtils.isEmpty(one)) {
//                map.put("hasFollow", true);
//            }
//        }
//        return ApiResult.success(map);
//    }
}
