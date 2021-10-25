package ua.com.alevel.service;

import ua.com.alevel.entity.User;

public class UserGenerationUtil {

    public static final int AGE = 10;
    public static final String NAME = "test";

    public static User generateUser() {
        User user = new User();
        user.setName(NAME);
        user.setAge(AGE);
        return user;
    }

    public static User generateUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    public static User generateUser(int age) {
        User user = new User();
        user.setName(NAME);
        user.setAge(age);
        return user;
    }
}
