package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Integer id);
    ENTITY findById(Integer id);
    Collection<ENTITY> findAll();
}
