package ua.com.alevel.dao;

import ua.com.alevel.entity.User;

public interface UserDao extends BaseDao<User> {

    boolean existByEmail(String email);
}
