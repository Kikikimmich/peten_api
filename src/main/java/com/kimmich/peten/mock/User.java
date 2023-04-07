package com.kimmich.peten.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private int id;
    private List<User> following;
    private int numFollowers;

    public User(int id) {
        this.id = id;
        this.following = new ArrayList<>();
        this.numFollowers = 0;
    }

    public void follow(User... otherUsers) {
        for(User otherUser : otherUsers) {
            if(!following.contains(otherUser)) {
                following.add(otherUser);
                otherUser.addFollower();
            }
        }
    }

    private void addFollower() {
        this.numFollowers++;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            users.add(new User(i));
        }

        // Make most users have no followers
        for(int i = 0; i < 80; i++) {
            users.get(i).numFollowers = 0;
        }

        // Randomly select some users to follow others
        Random rand = new Random();
        for(int i = 0; i < 50; i++) {
            int id1 = rand.nextInt(users.size());
            int id2 = rand.nextInt(users.size());
            User user1 = users.get(id1);
            User user2 = users.get(id2);
            user1.follow(user2);
        }

        // Output results
        System.out.println("User ID\t| Number of Followers");
        System.out.println("===========================");
        for(User user : users) {
            System.out.print(user.id + "\t| " + user.getNumFollowers() + "\t|");
            for (User follow : user.following) {
                System.out.print(follow.id + ",");
            }
            System.out.println();
        }
    }
}