package com.kimmich.peten.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.mapper.FollowMapper;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.service.IFollowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

    @Override
    @Transactional
    public void unfollow(String userId, String follow) {
        if (StrUtil.isBlank(userId) || StrUtil.isBlank(follow)){
            throw new ApiException("参数不能为空");
        }
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getUserId, userId).eq(Follow::getFollow, follow);
        remove(queryWrapper);
    }

    @Override
    @Transactional
    public void follow(String userId, String follow) {
        if (StrUtil.isBlank(userId) || StrUtil.isBlank(follow)){
            throw new ApiException("参数不能为空");
        }
        if (userId.equals(follow)){
            throw new ApiException("不能关注自己");
        }

        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getUserId, userId).eq(Follow::getFollow, follow);
        Follow one = getOne(queryWrapper);

        if (one == null || StrUtil.isBlank(one.getId())){
            one = new Follow();
            one.setUserId(userId);
            one.setFollow(follow);
            one.setCreateTime(new Date());
            one.setModifyTime(new Date());

            save(one);
        }
    }
}
