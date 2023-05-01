package com.kimmich.peten.controller;

import com.kimmich.peten.mock.FakeGroup;
import com.kimmich.peten.mock.FakeUser;
import com.kimmich.peten.mock.FakeUserRelationship;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mock")
public class MockController {

    @Autowired
    FakeUser fakeUser;

    @Resource
    FakeUserRelationship fakeUserRelationship;

    @Resource
    FakeGroup fakeGroup;

    @GetMapping("generate-group")
    @ApiOperation("生成圈子")
    public void generateGroup(String superName, String name){
        fakeGroup.generateGroup(superName, name);
    }


    @GetMapping("generate-transact-set")
    @ApiOperation("生成用户关注数据集")
    public void generateTransactSet(){
        fakeUserRelationship.generateTransactSet();
    }

    @GetMapping("mock-user-relationship")
    @ApiOperation("mock 用户关系数据")
    public void mockUserRelationship(){
        fakeUserRelationship.doMock();
    }


    @GetMapping("/mock-user")
    @ApiOperation("mock 用户数据")
    public boolean mockUser() throws InterruptedException {
        fakeUser.mockUser();
        return true;
    }

}
