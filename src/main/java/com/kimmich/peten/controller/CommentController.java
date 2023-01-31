package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.emun.JwtConst;
import com.kimmich.peten.model.dto.CommentDTO;
import com.kimmich.peten.model.entity.Comment;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.model.vo.CommentVO;
import com.kimmich.peten.service.ICommentService;
import com.kimmich.peten.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Resource
    private ICommentService bmsCommentService;
    @Resource
    private IUserService umsUserService;

    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicid) {
        List<CommentVO> lstBmsComment = bmsCommentService.getCommentsByTopicID(topicid);
        return ApiResult.success(lstBmsComment);
    }
    @PostMapping("/add_comment")
    public ApiResult<Comment> add_comment(@RequestHeader(JwtConst.USER_NAME) String userName,
                                          @RequestBody CommentDTO dto) {
        User user = umsUserService.getUserByUsername(userName);
        Comment comment = bmsCommentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
