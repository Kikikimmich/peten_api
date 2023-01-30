package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping("/file")
    public ApiResult<Boolean> uploadFile(MultipartFile file) {
        System.out.println("666");
        return ApiResult.success(true);
    }
}
