package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimmich.peten.model.entity.Tip;
import org.springframework.stereotype.Repository;

@Repository
public interface TipMapper extends BaseMapper<Tip> {
    Tip getRandomTip();
}
