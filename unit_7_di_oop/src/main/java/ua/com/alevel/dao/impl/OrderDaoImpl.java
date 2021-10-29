package ua.com.alevel.dao.impl;

import nix.solutions.dinix.annotations.Persistent;
import nix.solutions.dinix.db.DBStorage;
import ua.com.alevel.dao.OrderDao;
import ua.com.alevel.entity.Order;

import java.util.Collection;

public class OrderDaoImpl implements OrderDao {

    @Persistent
    private DBStorage dbStorage;

    @Override
    public void create(Order entity) {
        System.out.println("dbStorage = " + dbStorage.getConnection());
        System.out.println("dbStorage = " + dbStorage.getStatement());
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Order findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Order> findAll() {
        return null;
    }
}
