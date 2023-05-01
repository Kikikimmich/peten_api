package com.kimmich.peten.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.GroupMapper;
import com.kimmich.peten.model.dto.group.GroupDTO;
import com.kimmich.peten.model.entity.group.Group;
import com.kimmich.peten.service.IGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {



    @Override
    public List<GroupDTO> getHotGroup() {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(Group::getHeat).last("LIMIT 5");
        List<Group> list = list(queryWrapper);

        List<GroupDTO> result = new ArrayList<>();
        for (Group group : list) {
            GroupDTO dto = new GroupDTO();
            BeanUtils.copyProperties(group, dto);
            result.add(dto);
        }

        return result;
    }


    @Override
    public List<Group> getBySuperGroup(String superGroup) {
        if (StrUtil.isBlank(superGroup)){
            return new ArrayList<>();
        }
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Group::getSuperGroup, superGroup);
        return super.list(queryWrapper);
    }

    @Override
    public Group getById(String id) {
        return super.getById(id);
    }

    @Override
    @Transactional
    public void edit(Group args) {
        if (StrUtil.isBlank(args.getId())){
            args.setCreateTime(new Date());
        }
        args.setModifyTime(new Date());
        saveOrUpdate(args);
    }
}
