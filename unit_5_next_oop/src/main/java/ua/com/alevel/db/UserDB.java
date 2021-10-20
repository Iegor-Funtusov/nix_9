package ua.com.alevel.db;

import ua.com.alevel.entity.User;

public interface UserDB extends BaseDB<User> {

    boolean existByEmail(String email);
}
