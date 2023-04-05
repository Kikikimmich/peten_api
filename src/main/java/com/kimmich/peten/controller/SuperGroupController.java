package com.kimmich.peten.controller;

import com.alibaba.fastjson.JSONObject;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.dto.group.AllGroupDTO;
import com.kimmich.peten.model.entity.group.Group;
import com.kimmich.peten.model.entity.group.SuperGroup;
import com.kimmich.peten.service.ISuperGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/super-group")
public class SuperGroupController {

    @Resource
    ISuperGroupService superGroupService;


    @GetMapping("/all")
    @ApiOperation("获取全部圈子")
    public ApiResult<List<AllGroupDTO>> getAll(){
        return ApiResult.success(superGroupService.getAllGroup());
    }

    @PostMapping("/edit")
    @ApiOperation("编辑/添加")
    public ApiResult<Boolean> edit(@RequestBody String json){
        SuperGroup args = JSONObject.parseObject(json, SuperGroup.class);
        superGroupService.edit(args);
        return ApiResult.success(true);
    }

}
