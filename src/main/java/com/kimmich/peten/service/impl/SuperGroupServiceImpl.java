package com.kimmich.peten.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.SuperGroupMapper;
import com.kimmich.peten.model.dto.group.AllGroupDTO;
import com.kimmich.peten.model.dto.group.GroupDTO;
import com.kimmich.peten.model.entity.group.Group;
import com.kimmich.peten.model.entity.group.SuperGroup;
import com.kimmich.peten.service.IGroupService;
import com.kimmich.peten.service.ISuperGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class SuperGroupServiceImpl extends ServiceImpl<SuperGroupMapper, SuperGroup> implements ISuperGroupService {

    @Resource
    SuperGroupMapper superGroupMapper;

    @Resource
    IGroupService groupService;

    @Override
    public List<AllGroupDTO> getAllGroup() {
        // 获取所有的superGroup
        List<SuperGroup> superGroups = list();

        List<AllGroupDTO> allGroups = new ArrayList<>();

        for (SuperGroup superGroup : superGroups) {

            List<Group> groupList = groupService.getBySuperGroup(superGroup.getId());
            List<GroupDTO> groups = new ArrayList<>();
            for (Group group : groupList) {
                GroupDTO dto = new GroupDTO();
                BeanUtil.copyProperties(group, dto);
                groups.add(dto);
            }

            allGroups.add(AllGroupDTO.builder()
                    .id(superGroup.getId())
                    .name(superGroup.getName())
                    .groups(groups)
                    .total(String.valueOf(groups.size()))
                    .build());
        }

        return allGroups;
    }
}
