package ua.com.alevel.dao;

import org.springframework.stereotype.Service;
import ua.com.alevel.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @Override
    public void create(User user) {
        System.out.println("UserDaoImpl.create");
//        entityManager.persist(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
