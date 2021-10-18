package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UserDB {

    private final List<User> users;
    private static UserDB instance;

    private UserDB() {
        users = new ArrayList<>();
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
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

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (users.stream().anyMatch(user -> user.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
