package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.model.entity.History;


public interface IHistoryService extends IService<History> {

    void add(String userId, String contentId, Integer contentType);

}
