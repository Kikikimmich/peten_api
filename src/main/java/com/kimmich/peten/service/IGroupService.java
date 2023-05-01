package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.dto.group.GroupDTO;
import com.kimmich.peten.model.entity.group.Group;

import java.util.List;

public interface IGroupService extends IService<Group> {

    List<GroupDTO> getHotGroup();

    List<Group> getBySuperGroup(String superGroup);

    Group getById(String id);

    void edit(Group args);
}
