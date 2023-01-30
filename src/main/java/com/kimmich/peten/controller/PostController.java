package com.kimmich.peten.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.dto.CreateTopicDTO;
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
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<Post> create(@RequestHeader(value = USER_NAME) String userName
            , @RequestBody CreateTopicDTO dto) {
        User user = umsUserService.getUserByUsername(userName);
        Post topic = iPostService.create(dto, user);
        return ApiResult.success(topic);
    }
    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = iPostService.viewTopic(id);
        return ApiResult.success(map);
    }

    @GetMapping("/recommend")
    public ApiResult<List<Post>> getRecommend(@RequestParam("topicId") String id) {
        List<Post> topics = iPostService.getRecommend(id);
        return ApiResult.success(topics);
    }

    @PostMapping("/update")
    public ApiResult<Post> update(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody Post post) {
        User user = umsUserService.getUserByUsername(userName);
        Assert.isTrue(user.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        iPostService.updateById(post);
        return ApiResult.success(post);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@RequestHeader(value = USER_NAME) String userName, @PathVariable("id") String id) {
        User user = umsUserService.getUserByUsername(userName);
        Post byId = iPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(user.getId()), "你为什么可以删除别人的话题？？？");
        iPostService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }

}
