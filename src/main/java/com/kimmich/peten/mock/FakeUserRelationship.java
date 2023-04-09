package com.kimmich.peten.mock;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.service.IFollowService;
import com.kimmich.peten.service.IUserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FakeUserRelationship {

    @Resource
    IUserService userService;

    @Resource
    IFollowService followService;


    public void generateTransactSet(){

        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Follow::getUserId, Follow::getFollow);

        List<Follow> list = followService.list(queryWrapper);


        Map<String, List<String>> followMap = list.stream().collect(Collectors.groupingBy(Follow::getUserId, Collectors.mapping(Follow::getFollow, Collectors.toList())));
//        System.out.println(followMap.size());

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : followMap.entrySet()) {
            List<String> value = entry.getValue();

            sb.append(String.join(" ", value));
            sb.append("\n");
        }
//        System.out.println(sb.toString().length());


        try{
//            ClassPathResource resource = new ClassPathResource("static/file/TransactSet.txt");

//            File file = resource.getFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\MyProject\\peten_api\\src\\main\\resources\\static\\file\\TransactSet.txt"));

            bw.write(sb.toString());


            bw.flush();
            bw.close();

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void doMock() {

        List<String> idList = userService.getAllId();
        UserFollowGenerator generator = new UserFollowGenerator(idList.size(), 2);

        Map<String, List<String>> generate = generator.generate(idList);


        List<Follow> insertList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : generate.entrySet()) {
            String userID = entry.getKey();
            List<String> followers = entry.getValue();

            // 去掉无关紧要的数据
            if (followers == null || followers.size() == 1){
                continue;
            }

            // follower 关注了 userId
            for (String follower : followers) {
                insertList.add(Follow.builder()
                        .userId(follower)
                        .follow(userID)
                        .createTime(new Date())
                        .modifyTime(new Date())
                        .build());
            }
        }

        followService.saveBatch(insertList);
    }

}
