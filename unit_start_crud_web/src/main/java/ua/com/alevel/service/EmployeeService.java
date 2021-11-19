package ua.com.alevel.service;

import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import java.util.List;

public interface EmployeeService extends BaseService<Employee> {

    List<Employee> findAllByDepartment(Department department);
}
