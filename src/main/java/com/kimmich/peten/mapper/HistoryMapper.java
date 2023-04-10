package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.model.entity.History;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryMapper extends BaseMapper<History> {
}
