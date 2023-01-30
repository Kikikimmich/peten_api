package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.PromotionMapper;
import com.kimmich.peten.model.entity.Promotion;
import com.kimmich.peten.service.IPromotionService;
import org.springframework.stereotype.Service;


@Service
public class PromotionServiceImpl extends ServiceImpl<PromotionMapper, Promotion> implements IPromotionService {

}
