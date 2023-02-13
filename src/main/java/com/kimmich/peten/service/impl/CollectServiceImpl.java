package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.mapper.CollectMapper;
import com.kimmich.peten.mapper.ShoppingCartMapper;
import com.kimmich.peten.model.entity.shop.Collect;
import com.kimmich.peten.model.entity.shop.ShoppingCart;
import com.kimmich.peten.service.ICollectService;
import com.kimmich.peten.service.IShoppingCartService;
import com.kimmich.peten.utils.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {

    @Resource
    CollectMapper collectMapper;

    @Override
    @Transactional
    public void addCollect(String userId, String productId) {

        if (!CommonUtil.notNull(userId, productId)){
            throw new ApiException("参数不完整！");
        }

        Collect collect = Collect.builder()
                .userId(userId)
                .productId(productId)
                .createTime(new Date())
                .build();

        collectMapper.insert(collect);
    }
}
