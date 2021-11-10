package ua.com.alevel.generics;

public class UserDao implements CrudDao<User, String> {

//    private EntityManager entityManager;

    @Override
    public void create(User user) {
//        entityManager.create(user);
    }

    @Override
    public User find(String s) {
        return null;
    }
}
