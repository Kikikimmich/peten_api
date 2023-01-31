package com.kimmich.peten.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.kimmich.peten.emun.PathConst;
import com.kimmich.peten.emun.UploadImageConst;
import com.kimmich.peten.utils.MD5Utils;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;

import javax.annotation.Resource;

@Service
public class FileService {

    @Resource
    RestTemplate restTemplate;
    @Resource
    IImageURLService imageURLService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String uploadImage(MultipartFile file) {

        //设置允许上传文件类型
        String suffixList = ".jpg,.png,.ico,.bmp,.jpeg,.gif";

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        int lastIndexOf = originalFilename.lastIndexOf(".");
        //获取文件后缀
        String suffix = originalFilename.substring(lastIndexOf - 1);

        assert suffixList.contains(suffix);

        //使用UUID随机产生文件名称，防止同名文件覆盖
        String fileName = UUID.randomUUID() + suffix;

        File dest = new File(PathConst.IMAGE_ROOT + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            logger.info("[ " + new Date() + " ] " + "图片上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PathConst.IMAGE_URL_ROOT + fileName;
    }

    public File getImage(String fileName) {
        File file = new File(PathConst.IMAGE_ROOT + fileName);
        return file;
    }

    @Transactional
    public String uploadImageV2(MultipartFile file) throws IOException {
        File tempFile = new File(PathConst.IMAGE_ROOT + file.getOriginalFilename());

        if (tempFile.exists()) {
            tempFile.delete();
        }
        if (!tempFile.exists()) {
            //直接复制文件到指定路径
            file.transferTo(tempFile);
        }

        // 避免重复上传
        String md5 = MD5Utils.getMD5(tempFile);
        String url = imageURLService.getURL(md5);
        if (StrUtil.isAllNotBlank(url)) {
            // 重复上传
            return url;
        }

        //此时可以用FileSystemResource读取文件了
        FileSystemResource fileSystemResource = new FileSystemResource(tempFile);
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set(UploadImageConst.HEADER_KEY, UploadImageConst.HEADER_KEY_VALUE);
        params.add("source", fileSystemResource);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);

        String result = "";
        try {
            result = restTemplate.postForObject(UploadImageConst.TARGET_URL, requestEntity, String.class);
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject image = jsonObject.getJSONObject("image");
            url = image.getString("url");

            // 保存到数据库
            imageURLService.add(md5, url);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        } finally {
            // 删除临时文件
            tempFile.delete();
        }
        assert url != null;
        return url;
    }
}
