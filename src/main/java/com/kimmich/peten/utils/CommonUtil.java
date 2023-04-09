package com.kimmich.peten.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommonUtil {

    public static File getSystemFile(String fileName) {
        ClassPathResource classPathResource = new ClassPathResource("static/file/" + fileName);
        File file;
        try {
            file = classPathResource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
            // 没有的换就在这里建一个
            file = new File("E:\\MyProject\\peten_api\\src\\main\\resources\\static\\file" + fileName);
        }
        return file;
    }


    public static boolean isEmpty(Object o){
        if (!notNull(o)){
            return true;
        }
        if (o instanceof Collection){
           return ((Collection<?>) o).isEmpty();
        }
        // todo
        return true;
    }

    public static boolean notNull(Object ...args){
        // 判断参数非空、非空字符串
        for (Object arg : args) {
            if (ObjectUtil.isNull(arg)){
                return false;
            }
            if (StrUtil.isBlankIfStr(arg)){
                return false;
            }
        }
        return true;
    }
}
