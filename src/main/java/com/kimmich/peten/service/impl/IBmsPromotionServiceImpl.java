package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.BmsPromotionMapper;
import com.kimmich.peten.model.entity.BmsPromotion;
import com.kimmich.peten.service.IBmsPromotionService;
import org.springframework.stereotype.Service;


@Service
public class IBmsPromotionServiceImpl extends ServiceImpl<BmsPromotionMapper, BmsPromotion> implements IBmsPromotionService {

}
