package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.entity.Follow;


public interface IFollowService extends IService<Follow> {
    void unfollow(String userId, String follow);

    void follow(String userId, String follow);
}
