package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.entity.shop.Collect;

public interface ICollectService extends IService<Collect> {

    /**
     *  添加收藏
     * */
    void addCollect(String userId, String productId);
}
