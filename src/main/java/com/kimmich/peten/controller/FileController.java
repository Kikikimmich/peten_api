package com.kimmich.peten.controller;

import com.alibaba.fastjson.JSONObject;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.emun.PathConst;
import com.kimmich.peten.service.FileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FileService fileService;

    @Resource
    RestTemplate restTemplate;

    @PostMapping("/upload-file")
    public ApiResult<Boolean> uploadFile(MultipartFile file) {
        // todo
        return ApiResult.success(true);
    }

    @PostMapping("/upload-image")
    public ApiResult<String> uploadImage(@RequestParam(value = "source") MultipartFile file) {
        String fileName = fileService.uploadImage(file);
        return ApiResult.success(fileName);
    }

    @PostMapping(value = "/upload-image-v2")
    public ApiResult<String> uploadImageV2(@RequestParam(value = "source") MultipartFile file) throws IOException {

        // todo 避免重复上传

        File tempFile = new File(PathConst.IMAGE_ROOT + file.getOriginalFilename());
        if(tempFile.exists()){
            tempFile.delete();
        }
        if(!tempFile.exists()){
            //直接复制文件到指定路径
            file.transferTo(tempFile);
        }
        //此时可以用FileSystemResource读取文件了
        FileSystemResource fileSystemResource = new FileSystemResource(tempFile);

        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", "chv_y3cU_b5f300250003742bd2c78c7384c1b16f2349e35699d2809f61ac5ad801cd464f4eef04546df6f52e34caca8d57ef4ee95de3812a320ebe25a0e21cf26510d81c");
        params.add("source",fileSystemResource);
        HttpEntity<MultiValueMap<String,Object>> requestEntity  = new HttpEntity<>(params, headers);

        String result = "";
        String url = null;
        try{
            result = restTemplate.postForObject("https://imgloc.com/api/1/upload",requestEntity ,String.class);
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject image = jsonObject.getJSONObject("image");
            url = image.getString("url");
        }catch (HttpClientErrorException e){
            e.printStackTrace();
        }
        assert url != null;
        return ApiResult.success(url);
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
