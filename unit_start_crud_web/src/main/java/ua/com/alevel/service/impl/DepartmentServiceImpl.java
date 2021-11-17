package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.entity.Department;
import ua.com.alevel.repository.DepartmentRepository;
import ua.com.alevel.repository.EmployeeRepository;
import ua.com.alevel.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void create(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void update(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            employeeRepository.deleteAllByDepartment(department.get());
            departmentRepository.delete(department.get());
        }
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
