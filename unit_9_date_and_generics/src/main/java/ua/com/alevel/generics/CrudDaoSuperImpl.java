package ua.com.alevel.generics;

import java.util.List;

public class CrudDaoSuperImpl implements CrudDaoSuper<Integer> {

    @Override
    public void find(List<? super Integer> list) {

    }
}
