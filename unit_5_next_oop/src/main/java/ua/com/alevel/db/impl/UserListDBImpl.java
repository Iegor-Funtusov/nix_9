package ua.com.alevel.db.impl;

import ua.com.alevel.db.UserDB;
import ua.com.alevel.entity.User;
import ua.com.alevel.util.GenerateIdUtil;

import java.util.ArrayList;
import java.util.List;

public final class UserListDBImpl implements UserDB {

    private final List<User> users;
    private static UserListDBImpl instance;

    private UserListDBImpl() {
        System.out.println("UserListDBImpl.UserListDBImpl");
        users = new ArrayList<>();
    }

    public static UserListDBImpl getInstance() {
        if (instance == null) {
            instance = new UserListDBImpl();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(GenerateIdUtil.generateId(users));
        users.add(user);
    }

    public void update(User user) {
        User current = findById(user.getId());
        current.setAge(user.getAge());
        current.setName(user.getName());
    }

    public void delete(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public User findById(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("user not found by id"));
    }

    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean existByEmail(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }
}
