package ua.com.alevel.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {

    void deleteAllByDepartment(Department department);
}
