package com.kimmich.peten.controller;


import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.dto.LoginDTO;
import com.kimmich.peten.model.dto.RegisterDTO;
import com.kimmich.peten.model.dto.article.SimpleArticleDTO;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.service.IPostService;
import com.kimmich.peten.service.IUserService;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kimmich.peten.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private IUserService userService;

    @GetMapping("/get-my-follow-post")
    public ApiResult<List<ArticleBO>> getMyFollowPost(){
        String userId = getLoginUserId();
        return ApiResult.success(userService.getMyFollowPost(userId));
    }

    @GetMapping("/get-my-follow")
    public ApiResult<List<SimpleUserDTO>> getMyFollow(){
        String userId = getLoginUserId();
        return ApiResult.success(userService.getMyFollow(userId));
    }

    @GetMapping("/recommend")
    public ApiResult<List<SimpleUserDTO>> recommend(){
        String userId = getLoginUserId();
        return ApiResult.success(userService.recommend(userId));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        User user = userService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = userService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map, "登录成功");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<User> getUser(@RequestHeader(value = USER_NAME) String userName) {
        User user = userService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ApiResult<Object> logOut() {
        return ApiResult.success(null, "注销成功");
    }

    @GetMapping("/{username}")
    public ApiResult<Map<String, Object>> getUserByName(@PathVariable("username") String username,
                                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        User user = userService.getUserByUsername(username);
        Assert.notNull(user, "用户不存在");
//        Page<Post> page = iPostService.page(new Page<>(pageNo, size),
//                new LambdaQueryWrapper<Post>().eq(Post::getUserId, user.getId()));
        map.put("user", user);
//        map.put("topics", page);
        return ApiResult.success(map);
    }
    @PostMapping("/update")
    public ApiResult<User> updateUser(@RequestBody User user) {
        userService.updateById(user);
        return ApiResult.success(user);
    }
}
