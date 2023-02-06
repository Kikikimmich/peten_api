package com.kimmich.peten.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommonUtil {

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
