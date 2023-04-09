package com.kimmich.peten.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        for (int i = 100; i < 2000; i++) {
            list.add(i + "");
        }

        UserFollowGenerator generator = new UserFollowGenerator(list.size(), 2);

        Map<String, List<String>> generate = generator.generate(list);


        System.out.println(generate.size());

    }

}
