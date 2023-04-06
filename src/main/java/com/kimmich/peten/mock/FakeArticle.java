package com.kimmich.peten.mock;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.kimmich.peten.model.entity.Article;

public class FakeArticle {

    public static void main(String[] args) {
        // 创建一个faker对象，并指定语言为中文
        Faker faker = new Faker(Locale.CHINA);
        // 生成一个中文标题
        String title = faker.book().title();
        // 生成一个中文作者
        String author = faker.name().fullName();
        // 生成一段中文文章
        String article = faker.lorem().paragraph();

        // 打印结果
        System.out.println("标题：" + title);
        System.out.println("作者：" + author);
        System.out.println("文章：" + article);
        System.out.println(faker.regexify ("(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}"));
    }
}

