package ua.com.alevel.dao;

import ua.com.alevel.entity.Employee;

import java.util.List;

public interface EmployeeDao extends BaseDao<Employee> {

    void deleteAllByDepartmentId(Long departmentId);
    List<Employee> findAllByDepartmentId(Long departmentId);
}
