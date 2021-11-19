package ua.com.alevel.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {

    void deleteAllByDepartment(Department department);
    List<Employee> findAllByDepartment(Department department);
}
