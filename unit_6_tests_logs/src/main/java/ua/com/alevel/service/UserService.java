package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

import java.util.List;

public class UserService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final UserDao userDao = new UserDao();

    public void create(User user) {
        LOGGER_INFO.info("user start created");
        userDao.create(user);
        LOGGER_INFO.info("user finish created");
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(String id) {
        LOGGER_WARN.warn("user start deleted");
        userDao.delete(id);
        LOGGER_WARN.warn("user finish deleted");
    }

    public User findById(String id) {
        try {
            return userDao.findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("user not found by id: " + id);
        }
        return null;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
