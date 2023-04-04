package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.dto.group.AllGroupDTO;
import com.kimmich.peten.model.entity.group.SuperGroup;

import java.util.List;

public interface ISuperGroupService extends IService<SuperGroup> {
    List<AllGroupDTO> getAllGroup();
}
