package com.kimmich.peten.controller;

import com.alibaba.fastjson.JSONObject;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.common.ListDTO;
import com.kimmich.peten.model.dto.comment.CommentDTO;
import com.kimmich.peten.model.vo.comment.CommentVO;
import com.kimmich.peten.service.ICommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Resource
    private ICommentService commentService;

    @GetMapping("/get-comments")
    @ApiOperation("获取文章评论")
    public ApiResult<ListDTO<CommentVO>> getComment(@RequestParam(value = "articleId") String articleId) {
        ListDTO<CommentVO> comment = commentService.getCommentsByArticleId(articleId);
        return ApiResult.success(comment);
    }

    @PostMapping("/add")
    @ApiOperation("新增评论")
    public ApiResult<Boolean> add(@RequestBody String json) {
        CommentDTO dto = JSONObject.parseObject(json, CommentDTO.class);
        commentService.add(dto, getLoginUserId());
        return ApiResult.success(true);
    }
}
