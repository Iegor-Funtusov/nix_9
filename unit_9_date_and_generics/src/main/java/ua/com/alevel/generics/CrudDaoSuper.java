package ua.com.alevel.generics;

import java.util.List;

public interface CrudDaoSuper<E> {

    void find(List<? super E> list);
}
