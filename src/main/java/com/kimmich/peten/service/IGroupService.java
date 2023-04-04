package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.entity.group.Group;

import java.util.List;

public interface IGroupService extends IService<Group> {

    List<Group> getBySuperGroup(String superGroup);

    Group getById(String id);

    void edit(Group args);
}
