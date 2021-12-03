package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Employee;

@Repository
public interface EmployeeRepository extends AbstractRepository<Employee> { }
