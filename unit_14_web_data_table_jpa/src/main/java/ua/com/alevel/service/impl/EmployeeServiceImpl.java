package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.EmployeeDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void create(Employee entity) {
        employeeDao.create(entity);
    }

    @Override
    public void update(Employee entity) {
        if (!employeeDao.existById(entity.getId())) {
            throw new EntityNotFoundException("employee not found");
        }
        employeeDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!employeeDao.existById(id)) {
            throw new EntityNotFoundException("employee not found");
        }
        employeeDao.delete(id);
    }

    @Override
    public Employee findById(Long id) {
        if (!employeeDao.existById(id)) {
            throw new EntityNotFoundException("employee not found");
        }
        return employeeDao.findById(id);
    }

    @Override
    public DataTableResponse<Employee> findAll(DataTableRequest request) {
        return employeeDao.findAll(request);
    }
}
