package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.FollowMapper;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.service.IFollowService;
import org.springframework.stereotype.Service;


@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {
}
