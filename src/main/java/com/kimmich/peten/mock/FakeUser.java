package com.kimmich.peten.mock;

import cn.hutool.core.util.StrUtil;
import com.github.javafaker.Faker;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.service.IUserService;
import com.kimmich.peten.utils.MD5Utils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static cn.hutool.core.lang.Console.log;

@Component
public class FakeUser {

    @Resource
    IUserService userService;

    public void mockUser() throws InterruptedException {

        Faker faker = new Faker(Locale.CHINA);

        List<User> userList = new CopyOnWriteArrayList<>();
        ConcurrentHashMap<String, Boolean> nameSet = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Boolean> phoneSet = new ConcurrentHashMap<>();

        Thread t1 = new SetInsertThread(nameSet, phoneSet, userList, "name");
        Thread t2 = new SetInsertThread(nameSet, phoneSet, userList, "funnyName");
        Thread t3 = new SetInsertThread(nameSet, phoneSet, userList, "fullName");
        Thread t4 = new SetInsertThread(nameSet, phoneSet, userList, "dog");
        Thread t5 = new SetInsertThread(nameSet, phoneSet, userList, "cat");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        log("t1 done");
        t2.join();
        log("t2 done");
        t3.join();
        log("t3 done");
        t4.join();
        log("t4 done");
        t5.join();
        log("t5 done");

        log("start insert");
        userService.saveBatch(userList);


//        for (int i = 0; i < 10000; i++) {
//
//            String name = faker.funnyName().name();
//            String phone = faker.regexify("(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}");
//
//            // 保证唯一
//            if (nameSet.contains(name) || phoneSet.contains(phone)) {
//                continue;
//            }
//            nameSet.add(name);
//            phoneSet.add(phone);
//
//
//            String email = name + "@fake.com";
//
//            userList.add(User.builder()
//                    .username(name)
//                    .alias(name)
//                    .mobile(phone)
//                    .email(email)
//                    .password("123456")
//                    .avatar("")
//                    .active(true)
//                    .status(true)
//                    .createTime(new Date())
//                    .modifyTime(new Date())
//                    .build());
//
//        }
        System.out.println(userList.size());
    }

    private static class SetInsertThread extends Thread {

        final ConcurrentHashMap<String, Boolean> nameSet;
        final ConcurrentHashMap<String, Boolean> phoneSet;
        final List<User> userList;

        final String nameType;


        public SetInsertThread(ConcurrentHashMap<String, Boolean> nameSet, ConcurrentHashMap<String, Boolean> phoneSet, List<com.kimmich.peten.model.entity.User> userList, String nameType) {
            this.nameSet = nameSet;
            this.phoneSet = phoneSet;
            this.userList = userList;

            this.nameType = nameType;

        }

        @Override
        public void run() {
            Faker faker = new Faker(Locale.CHINA);

            for (int i = 0; i < 8000; i++) {


                String name = "";
                switch (nameType) {
                    case "name":
                        name = faker.name().username();
                        break;
                    case "funnyName":
                        name = faker.funnyName().name();
                        break;
                    case "fullName":
                        name = faker.name().fullName();
                        break;
                    case "dog":
                        name = faker.dog().name();
                        break;
                    case "cat":
                        name = faker.cat().name();
                }

                String phone = faker.regexify("(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}");

                if (StrUtil.isBlank(name)) continue;

                // 保证唯一
                if (nameSet.containsKey(name) || phoneSet.containsKey(phone)) {
                    continue;
                }

                synchronized (userList) {
                    if (nameSet.putIfAbsent(name, true) == null && phoneSet.putIfAbsent(phone, true) == null) {
                        String email = phone + "@fake.com";
                        userList.add(User.builder()
                                .username(name)
                                .alias(name)
                                .mobile(phone)
                                .email(email)
                                .password(MD5Utils.getPwd("123456"))
                                .avatar("")
                                .active(true)
                                .status(true)
                                .createTime(new Date())
                                .modifyTime(new Date())
                                .build());
                    }
                }
            }

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
}