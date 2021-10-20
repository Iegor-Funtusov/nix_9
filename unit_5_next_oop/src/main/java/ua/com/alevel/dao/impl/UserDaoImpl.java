package ua.com.alevel.dao.impl;

import ua.com.alevel.config.ApplicationConfig;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.db.UserDB;
import ua.com.alevel.entity.User;

import java.util.Collection;

public class UserDaoImpl implements UserDao {

    private final UserDB instanceDB = ApplicationConfig.getImpl(UserDB.class);

    @Override
    public void create(User entity) {
        instanceDB.create(entity);
    }

    @Override
    public void update(User entity) {
        instanceDB.update(entity);
    }

    @Override
    public void delete(String id) {
        instanceDB.delete(id);
    }

    @Override
    public User findById(String id) {
        return instanceDB.findById(id);
    }

    @Override
    public Collection<User> findAll() {
        return instanceDB.findAll();
    }

    @Override
    public boolean existByEmail(String email) {
        return instanceDB.existByEmail(email);
    }
}
