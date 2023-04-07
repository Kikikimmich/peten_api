package com.kimmich.peten.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FakeUserRelationship {
    public static void main(String[] args) {
        int numUsers = 100;
        int numPopularUsers = 10;
        int maxFollowers = 50;

        boolean[][] followMatrix = new boolean[numUsers][numUsers];
        int[] numFollowers = new int[numUsers];

        // 先生成前几个用户之间的关注关系
        for (int i = 1; i < numPopularUsers; i++) {
            for (int j = 0; j < numUsers; j++) {
                if (j != i && j % i == 0) { // 每i个用户关注该用户
                    followMatrix[j][i] = true;
                    numFollowers[i]++;
                }
            }
        }

        // 再生成其他用户之间的关注关系
        for (int i = numPopularUsers; i < numUsers; i++) {
            double[] probabilities = new double[numUsers - numPopularUsers];
            int count = 0;

            for (int j = numPopularUsers; j < numUsers; j++) {
                if (i != j && !followMatrix[i][j]) {
                    probabilities[count++] = Math.max(0, numFollowers[j] - numFollowers[i]);
                }
            }

            while (count > 0 && numFollowers[i] < maxFollowers) {
                double sum = 0;
                for (int j = 0; j < count; j++) {
                    sum += probabilities[j];
                }

                double r = Math.random() * sum;

                int j = 0;
                while (j < count - 1 && r >= probabilities[j]) {
                    r -= probabilities[j++];
                }

                followMatrix[i][j + numPopularUsers] = true;
                numFollowers[i]++;
                numFollowers[j + numPopularUsers]++;
                probabilities[j] = probabilities[--count];
            }
        }

        // 引入更多的随机性和偏好性，让大部分用户没有粉丝
        for (int i = numPopularUsers; i < numUsers; i++) {
            if (Math.random() < 0.2) {
                continue;
            }

            int numRandomFollowers = (int) (Math.random() * numPopularUsers);
            boolean[] randomFollowers = new boolean[numPopularUsers];
            int count = 0;

            while (count < numRandomFollowers) {
                int j = (int) (Math.random() * numPopularUsers);
                if (!randomFollowers[j]) {
                    randomFollowers[j] = true;
                    count++;

                    followMatrix[i][j] = true;
                    numFollowers[i]++;
                    numFollowers[j]++;
                }
            }
        }

        // 打印每个用户关注的其他用户
        for (int i = 0; i < numUsers; i++) {
            System.out.print("User " + i + " follows: ");
            for (int j = 0; j < numUsers; j++) {
                if (followMatrix[i][j]) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }
}
