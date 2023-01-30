package com.kimmich.peten.service;

import com.kimmich.peten.emun.PathConst;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;

@Service
public class FileService {

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
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
            logger.info("[ "+ new Date() + " ] " + "图片上传成功");
        }catch (IOException e){
            e.printStackTrace();
        }
        return PathConst.IMAGE_URL_ROOT + fileName;
    }

    public File getImage(String fileName) {
        File file = new File(PathConst.IMAGE_ROOT + fileName);
        return file;
    }
}
