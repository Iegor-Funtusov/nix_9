package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;
    private final EmployeeDao employeeDao;

    public DepartmentServiceImpl(
            DepartmentDao departmentDao,
            EmployeeDao employeeDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public void create(Department department) {
        departmentDao.create(department);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public void delete(Long id) {
        if (departmentDao.existById(id)) {
            employeeDao.deleteAllByDepartmentId(id);
            departmentDao.delete(id);
        }
    }

    @Override
    public Department findById(Long id) {
        return departmentDao.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
