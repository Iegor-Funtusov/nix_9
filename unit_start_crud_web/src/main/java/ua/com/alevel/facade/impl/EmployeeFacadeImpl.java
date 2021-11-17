package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.employee.EmployeeRequestDto;
import ua.com.alevel.dto.employee.EmployeeResponseDto;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public EmployeeFacadeImpl(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @Override
    public void create(EmployeeRequestDto employeeRequestDto) {
        Department department = departmentService.findById(employeeRequestDto.getDepartmentId());
        Employee employee = new Employee();
        employee.setAge(employeeRequestDto.getAge());
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setDepartment(department);
        employeeService.create(employee);
    }

    @Override
    public void update(EmployeeRequestDto employeeRequestDto, Long id) {
        Employee employee = employeeService.findById(id);
        employee.setAge(employeeRequestDto.getAge());
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employeeService.update(employee);
    }

    @Override
    public void delete(Long id) {
        employeeService.delete(id);
    }

    @Override
    public EmployeeResponseDto findById(Long id) {
        return new EmployeeResponseDto(employeeService.findById(id));
    }

    @Override
    public List<EmployeeResponseDto> findAll() {
        return employeeService.findAll()
                .stream()
                .map(EmployeeResponseDto::new)
                .collect(Collectors.toList());
    }
}
