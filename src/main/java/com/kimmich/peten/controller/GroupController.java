package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.entity.group.Group;
import com.kimmich.peten.service.IGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Resource
    IGroupService groupService;

    @GetMapping("/list")
    @ApiOperation("获取列表")
    public ApiResult<List<Group>> getList(String id){
        return ApiResult.success(groupService.getBySuperGroup(id));
    }


    @GetMapping("/get")
    @ApiOperation("获取圈子")
    public ApiResult<Group> get(String id){
        return ApiResult.success(groupService.getById(id));
    }

    @PostMapping("/edit")
    @ApiOperation("编辑/添加")
    public ApiResult<Boolean> edit(Group args){
        groupService.edit(args);
        return ApiResult.success(true);
    }

}
