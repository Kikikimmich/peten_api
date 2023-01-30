package com.kimmich.peten.controller;

import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FileService fileService;

    @PostMapping("/upload-file")
    public ApiResult<Boolean> uploadFile(MultipartFile file) {
        // todo
        return ApiResult.success(true);
    }

    @PostMapping("/upload-image")
    public ApiResult<String> uploadImage(@RequestParam(value = "file") MultipartFile file) {
        String fileName = fileService.uploadImage(file);
        return ApiResult.success(fileName);
    }

    @GetMapping("/get-image/{fileName}")
    public File getImage(@PathVariable("fileName") String fileName,
                         HttpServletResponse response , HttpServletRequest request) throws Exception{
        File image = fileService.getImage(fileName);

        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition",
                "attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));

//        File file = new File(path,fileName);
        //2、 读取文件--输入流
        InputStream input = new FileInputStream(image);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return image;
    }
}
