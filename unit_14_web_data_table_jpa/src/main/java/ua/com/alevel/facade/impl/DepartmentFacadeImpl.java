package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.DepartmentRequestDto;
import ua.com.alevel.view.dto.response.DepartmentFullResponseDto;
import ua.com.alevel.view.dto.response.DepartmentSimpleResponseDto;
import ua.com.alevel.view.dto.response.EmployeeSimpleResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentFacadeImpl implements DepartmentFacade {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentFacadeImpl(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @Override
    public void create(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setDepartmentType(departmentRequestDto.getDepartmentType());
        department.setName(departmentRequestDto.getName());
        departmentService.create(department);
    }

    @Override
    public void update(DepartmentRequestDto departmentRequestDto, long id) {
        Department department = departmentService.findById(id);
        department.setDepartmentType(departmentRequestDto.getDepartmentType());
        department.setName(departmentRequestDto.getName());
        if (CollectionUtils.isNotEmpty(departmentRequestDto.getEmployeesIds())) {
            Set<Employee> employees = new HashSet<>();
            for (Long employeesId : departmentRequestDto.getEmployeesIds()) {
                employees.add(employeeService.findById(employeesId));
            }
            department.setEmployees(employees);
        }
    }

    @Override
    public void delete(long id) {
        employeeService.delete(id);
    }

    @Override
    public DepartmentFullResponseDto findById(long id) {
        return new DepartmentFullResponseDto(departmentService.findById(id));
    }

    @Override
    public PageData<DepartmentSimpleResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        DataTableResponse<Department> tableResponse = departmentService.findAll(dataTableRequest);
        List<DepartmentSimpleResponseDto> departments = tableResponse.getItems()
                .stream()
                .map(DepartmentSimpleResponseDto::new)
                .collect(Collectors.toList());
        PageData<DepartmentSimpleResponseDto> pageData = (PageData<DepartmentSimpleResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(departments);
        return pageData;
    }
}
