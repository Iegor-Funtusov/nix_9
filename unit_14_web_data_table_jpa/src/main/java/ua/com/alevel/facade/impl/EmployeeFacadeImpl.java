package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.EmployeeRequestDto;
import ua.com.alevel.view.dto.response.EmployeeFullResponseDto;
import ua.com.alevel.view.dto.response.EmployeeSimpleResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeFacadeImpl(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void create(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setAge(employeeRequestDto.getAge());
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
//        Set<Department> departments = new HashSet<>();
//        if (CollectionUtils.isNotEmpty(employeeRequestDto.getDepartmentsIds())) {
//            for (Long departmentsId : employeeRequestDto.getDepartmentsIds()) {
//                departments.add(departmentService.findById(departmentsId));
//            }
//        }
//        employee.setDepartments(departments);
        employeeService.create(employee);
    }

    @Override
    public void update(EmployeeRequestDto employeeRequestDto, long id) {
        Employee employee = employeeService.findById(id);
        employee.setAge(employeeRequestDto.getAge());
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        Set<Department> departments = new HashSet<>();
        if (CollectionUtils.isNotEmpty(employeeRequestDto.getDepartmentsIds())) {
            for (Long departmentsId : employeeRequestDto.getDepartmentsIds()) {
                departments.add(departmentService.findById(departmentsId));
            }
        }
        employee.setDepartments(departments);
        employeeService.update(employee);
    }

    @Override
    public void delete(long id) {
        employeeService.delete(id);
    }

    @Override
    public EmployeeFullResponseDto findById(long id) {
        return new EmployeeFullResponseDto(employeeService.findById(id));
    }

    @Override
    public PageData<EmployeeSimpleResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        DataTableResponse<Employee> tableResponse = employeeService.findAll(dataTableRequest);
        List<EmployeeSimpleResponseDto> employees = tableResponse.getItems()
                .stream()
                .map(EmployeeSimpleResponseDto::new)
                .collect(Collectors.toList());
        PageData<EmployeeSimpleResponseDto> pageData = (PageData<EmployeeSimpleResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(employees);
        return pageData;
    }
}
