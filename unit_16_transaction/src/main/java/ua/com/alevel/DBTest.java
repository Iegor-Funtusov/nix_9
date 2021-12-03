package ua.com.alevel;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.type.DepartmentType;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;

import java.util.Set;

@Component
public class DBTest {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public DBTest(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void createDepartment() {
        Department department1 = new Department();
        department1.setDepartmentType(DepartmentType.DEV_OPS);
        department1.setName("name3");
//        departmentService.create(department1);

//        Department department2 = new Department();
//        department2.setDepartmentType(DepartmentType.JAVA);
//        department2.setName("name2");
//        departmentService.create(department2);
    }

    public void createEmployee() {
        Employee employee1 = new Employee();
        employee1.setAge(17);
        employee1.setFirstName("test11");
        employee1.setLastName("test12");
        employeeService.create(employee1);

        Employee employee2 = new Employee();
        employee2.setAge(19);
        employee2.setFirstName("test21");
        employee2.setLastName("test22");
        employeeService.create(employee2);

        Employee employee3 = new Employee();
        employee3.setAge(35);
        employee3.setFirstName("test31");
        employee3.setLastName("test32");
        employeeService.create(employee3);
    }

    public void updateDepartment() {
        Department departmentJS = departmentService.findById(1L).get();

        Employee employee1 = employeeService.findById(1L).get();
        Employee employee2 = employeeService.findById(2L).get();

        departmentJS.getEmployees().add(employee1);
        departmentJS.getEmployees().add(employee2);

        departmentService.update(departmentJS);
    }

    public void showAllEmployeesByDepartment() {
        Department departmentJS = departmentService.findById(1L).get();

        System.out.println("departmentJS = " + departmentJS.getEmployees().size());

        Set<Employee> employees = departmentJS.getEmployees();
        for (Employee employee : employees) {
            int size = employee.getDepartments().size();
        }
    }
}
