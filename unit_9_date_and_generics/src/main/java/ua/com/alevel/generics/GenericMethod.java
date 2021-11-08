package ua.com.alevel.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericMethod {

    private Map<? extends AbstractEntity, String> map;

    public <T extends AbstractEntity> T findById(String id) {

        CrudDaoSuper<Number> crudDaoSuper = new CrudDaoSuper<Number>() {
            @Override
            public void find(List<? super Number> list) {

            }
        };

        List<Integer> integers = new ArrayList<>();
        crudDaoSuper.find(integers);

        T t = (T) new Object();
        return t;
    }

    public <T extends AbstractEntity> void create(T t) {
        System.out.println("t = " + t.getClass().getName());
    }

    public void create(User user) {
        System.out.println("t = " + user.getClass().getName());
    }
}
