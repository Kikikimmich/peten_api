package com.kimmich.peten.utils;

import cn.hutool.core.util.ObjectUtil;

import java.util.List;

public class ListUtil {
    public static boolean isEmpty(List list){
        return ObjectUtil.isNotNull(list) && list.isEmpty();
    }
}
