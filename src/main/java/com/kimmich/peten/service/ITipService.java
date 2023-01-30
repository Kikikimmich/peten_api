package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.entity.Tip;

public interface ITipService extends IService<Tip> {
    Tip getRandomTip();
}
