package ua.com.alevel.generics;

public interface CrudDao<E extends AbstractEntity, ID> {

    void create(E e);
    E find(ID id);
}
