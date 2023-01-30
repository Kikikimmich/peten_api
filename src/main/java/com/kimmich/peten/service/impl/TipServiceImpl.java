package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.TipMapper;
import com.kimmich.peten.model.entity.Tip;
import com.kimmich.peten.service.ITipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TipServiceImpl extends ServiceImpl<TipMapper
        , Tip> implements ITipService {

    @Override
    public Tip getRandomTip() {
        Tip todayTip = null;
        try {
            todayTip = this.baseMapper.getRandomTip();
        } catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
