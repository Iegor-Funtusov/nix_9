package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entitydto.DepEmp;
import ua.com.alevel.persistence.type.DepartmentType;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface DepartmentService extends BaseService<Department> {

    List<Department> findByEmployees(Set<Long> employees);
    Set<Department> findByEmployeesIds(Set<Long> employees);
    Set<Department> findByDepartmentTypeOrCreatedBetween(DepartmentType departmentType, Date start, Date end);
    Set<Department> findByVisibleTrue();
    Set<DepEmp> findCountByJSAndJava();
}
