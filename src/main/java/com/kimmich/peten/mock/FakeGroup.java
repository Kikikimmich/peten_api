package com.kimmich.peten.mock;

import com.kimmich.peten.model.dto.group.AllGroupDTO;
import com.kimmich.peten.model.entity.group.Group;
import com.kimmich.peten.service.IGroupService;
import com.kimmich.peten.service.ISuperGroupService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FakeGroup {
    @Resource
    ISuperGroupService superGroupService;

    @Resource
    IGroupService groupService;


    @Transactional
    public void  generateGroup(String superName, String name){
        List<AllGroupDTO> allGroup = superGroupService.getAllGroup();
        Map<String, String> map = allGroup.stream().collect(Collectors.toMap(AllGroupDTO::getName, AllGroupDTO::getId));

        String superId = map.get(superName);

        Group group = Group.builder()
                .cover("")
                .createTime(new Date())
                .name(name)
                .slogan(String.format("我永远爱%s！", name))
                .superGroup(superId)
                .build();
        groupService.save(group);

    }
}
