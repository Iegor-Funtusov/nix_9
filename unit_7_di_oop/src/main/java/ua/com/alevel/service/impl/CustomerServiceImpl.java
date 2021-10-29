package ua.com.alevel.service.impl;

import nix.solutions.dinix.annotations.Autowired;
import nix.solutions.dinix.annotations.Service;

import ua.com.alevel.dao.CustomerDao;
import ua.com.alevel.dao.OrderDao;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.service.CustomerService;

import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public void create(Customer entity) {
        customerDao.create(entity);
        orderDao.create(null);
    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Customer> findAll() {
        return customerDao.findAll();
    }
}
