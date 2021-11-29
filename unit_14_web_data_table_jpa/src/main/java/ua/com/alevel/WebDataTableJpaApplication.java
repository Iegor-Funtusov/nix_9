package ua.com.alevel;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.view.dto.request.EmployeeRequestDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class WebDataTableJpaApplication {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final EmployeeFacade employeeFacade;

    public WebDataTableJpaApplication(EmployeeService employeeService, DepartmentService departmentService, EmployeeFacade employeeFacade) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.employeeFacade = employeeFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebDataTableJpaApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
//        Department department = new Department();
//        department.setDepartmentType(DepartmentType.JAVA);
//        department.setName("name2");
//        departmentService.create(department);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(10);
        dataTableRequest.setPage(1);
        dataTableRequest.setSort("id");
        dataTableRequest.setOrder("asc");

        DataTableResponse<Department> dataTableResponse = departmentService.findAll(dataTableRequest);

        for (Department item : dataTableResponse.getItems()) {
            System.out.println("department id = " + item.getId());
            System.out.println("department name = " + item.getName());
            System.out.println("department type = " + item.getDepartmentType().name());
        }

//        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
//        employeeRequestDto.setAge(100);
//        employeeRequestDto.setFirstName("name21");
//        employeeRequestDto.setLastName("name22");
//        employeeRequestDto.setDepartmentsIds(Arrays.asList(1L,2L));

//        employeeFacade.create(employeeRequestDto);

        Employee employee = employeeService.findById(5L);
        employee.setFirstName("new name");
        Set<Department> departments = new HashSet<>(dataTableResponse.getItems());
        employee.setDepartments(departments);

        employeeService.update(employee);

        DataTableResponse<Employee> dataTableResponse1 = employeeService.findAll(dataTableRequest);

        for (Employee item : dataTableResponse1.getItems()) {
            System.out.println("id = " + item.getId());
            System.out.println("age = " + item.getAge());
            System.out.println("f = " + item.getFirstName());
            System.out.println("l = " + item.getLastName());
            if (CollectionUtils.isNotEmpty(item.getDepartments())) {
                System.out.println("dep size = " + item.getDepartments().size());
            }
        }
    }
}
