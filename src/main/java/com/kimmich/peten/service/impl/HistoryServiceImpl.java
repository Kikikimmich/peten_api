package com.kimmich.peten.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.mapper.FollowMapper;
import com.kimmich.peten.mapper.HistoryMapper;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.model.entity.History;
import com.kimmich.peten.service.IFollowService;
import com.kimmich.peten.service.IHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements IHistoryService {


    @Override
    @Transactional
    public void add(String userId, String contentId, Integer contentType) {

        // 查询当天记录
        LambdaQueryWrapper<History> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(History::getUserId, userId)
                .eq(History::getContentId, contentId)
                .eq(History::getContentType, contentType);

        String today = LocalDate.now().atStartOfDay().toString().replace("T", " ");
        String tomorrow = LocalDate.now().plusDays(1).atStartOfDay().toString().replace("T", " ");

        queryWrapper.ge(History::getCreateTime, today).le(History::getModifyTime, tomorrow);
        History one = getOne(queryWrapper);

        if (one == null || StrUtil.isBlank(one.getId())){
            History history = History.builder()
                    .userId(userId)
                    .contentId(contentId)
                    .contentType(contentType)
                    .click(1)
                    .createTime(new Date())
                    .modifyTime(new Date())
                    .build();
            save(history);
            return;
        }

        one.setClick(one.getClick() + 1);
        one.setModifyTime(new Date());
        saveOrUpdate(one);
    }
}
