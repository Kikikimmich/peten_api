package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.BillboardMapper;
import com.kimmich.peten.model.entity.Billboard;
import com.kimmich.peten.service.IBillboardService;
import org.springframework.stereotype.Service;

@Service
public class BillboardServiceImpl extends ServiceImpl<BillboardMapper
        , Billboard> implements IBillboardService {

}
