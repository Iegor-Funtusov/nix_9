package ua.com.alevel.dao;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.BaseEntity;

public interface BaseDao<E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    void delete(Long id);
    boolean existById(Long id);
    E findById(Long id);
    DataTableResponse<E> findAll(DataTableRequest request);
    long count();
}
