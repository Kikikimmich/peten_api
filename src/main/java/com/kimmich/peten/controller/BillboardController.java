package com.kimmich.peten.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.entity.Billboard;
import com.kimmich.peten.service.IBillboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/billboard")
public class BillboardController extends BaseController {

    @Resource
    private IBillboardService bmsBillboardService;

    @GetMapping("/show")
    public ApiResult<Billboard> getNotices(){
        List<Billboard> list = bmsBillboardService.list(new
                LambdaQueryWrapper<Billboard>().eq(Billboard::isShow,true));
        return ApiResult.success(list.get(list.size()- 1));
    }
}
