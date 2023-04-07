package com.kimmich.peten.mock;

import com.github.javafaker.Faker;
import com.kimmich.peten.model.common.ListDTO;
import com.kimmich.peten.model.entity.User;

import java.util.*;

public class FakeUser {

    public static void main(String[] args) {
        Faker faker = new Faker(Locale.CHINA);
        List<User> userList = new ArrayList<>();

        Set<String> nameSet = new HashSet<>();
        Set<String> phoneSet = new HashSet<>();

        for (int i = 0; i < 10000; i++) {

            String name = faker.funnyName().name();
            String phone = faker.regexify("(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}");

            // 保证唯一
            if (nameSet.contains(name) || phoneSet.contains(phone)) {
                continue;
            }
            nameSet.add(name);
            phoneSet.add(phone);


            String email = name + "@fake.com";

            userList.add(User.builder()
                    .username(name)
                    .alias(name)
                    .mobile(phone)
                    .email(email)
                    .password("123456")
                    .avatar("")
                    .active(true)
                    .status(true)
                    .createTime(new Date())
                    .modifyTime(new Date())
                    .build());

        }
        System.out.println(userList);
    }
//
//    public void follow(User other) {
//        // 模拟关注事件
//        double prob = Math.exp((other.getPopularity() - popularity) / 100.0);
//        if (prob > 0.5) {
//            System.out.println(name + " is now following " + other.getName());
//        }
//    }
//
//    public void unfollow(User other) {
//        // 模拟取消关注事件
//        double prob = Math.exp((other.getPopularity() - popularity) / 100.0);
//        if (prob > 0.5) {
//            System.out.println(name + " has unfollowed " + other.getName());
//        }
//    }


}
