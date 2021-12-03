package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.type.DepartmentType;

import java.util.List;
import java.util.Set;

@Repository
public interface DepartmentRepository extends AbstractRepository<Department> {

    List<Department> findByDepartmentType(DepartmentType departmentType);
    List<Department> findByNameStartingWithIgnoreCase(String name);
    List<Department> findByDepartmentTypeIn(List<DepartmentType> departmentTypes);
//    List<Department> findByEmployees(Set<Employee> employees);
//    void deleteByEmployees(Set<Employee> employees);
}
