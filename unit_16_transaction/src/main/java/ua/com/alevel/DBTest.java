package ua.com.alevel;

import org.apache.commons.collections4.MapUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.entitydto.DepEmp;
import ua.com.alevel.persistence.type.DepartmentType;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    public void findByEmployees() {
        Employee employee1 = employeeService.findById(1L).get();
        Employee employee2 = employeeService.findById(2L).get();

        System.out.println("employee1 = " + employee1.getId());
        System.out.println("employee2 = " + employee2.getId());

        Set<Long> ids = new HashSet<>();
        ids.add(employee1.getId());
        ids.add(employee2.getId());

        List<Department> departments1 = departmentService.findByEmployees(ids);
        System.out.println("departments1 = " + departments1.size());

        Set<Department> departments2 = departmentService.findByEmployeesIds(ids);
        System.out.println("departments2 = " + departments2.size());

        Department department1 = departmentService.findById(1L).get();
        Department department2 = departmentService.findById(2L).get();

        Set<Department> departments3 = departmentService.findByDepartmentTypeOrCreatedBetween(
                DepartmentType.JAVA, department1.getCreated(), new Date());
        System.out.println("departments3 = " + departments3.size());

        Set<Department> departments4 = departmentService.findByVisibleTrue();
        System.out.println("departments4 = " + departments4.size());

        Set<DepEmp> depEmp1 = departmentService.findCountByJSAndJava();
        System.out.println("depEmp1 = " + depEmp1.size());
        for (DepEmp depEmp : depEmp1) {
            System.out.println("depEmp = " + depEmp);
        }
    }

    void searchByCustomQuery() {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(10);
        dataTableRequest.setPage(1);
        dataTableRequest.setOrder("desc");
        dataTableRequest.setSort("id");

        Map<String, String[]> requestParamMap = new HashMap<>();
        requestParamMap.put("firstName", new String[]{ "11" });
        requestParamMap.put("lastName", new String[]{ "22" });
        requestParamMap.put("age", new String[]{ "35" });

        dataTableRequest.setRequestParamMap(requestParamMap);

        DataTableResponse<Employee> employees = employeeService.findAll(dataTableRequest);
    }


    public void createQuery(Class<? extends BaseEntity> entityClass, DataTableRequest request) {
        StringBuilder query = new StringBuilder();
        query.append("select e from ").append(entityClass.getSimpleName());

        Map<String, String[]> map = request.getRequestParamMap();
        if (MapUtils.isNotEmpty(map)) {
            query.append(" where ");
        }
        for (Field declaredField : entityClass.getDeclaredFields()) {
            String[] req = map.get(declaredField.getName());
            if (req != null) {
                if (declaredField.getType().isAssignableFrom(String.class)) {
                    query.append("e.");
                    query.append(declaredField.getName());
                    query.append(" like %");
                    query.append(req[0]);
                    query.append("%");
                }
                if (declaredField.getType().isAssignableFrom(Number.class)) {
                    query.append("e.");
                    query.append(declaredField.getName());
                    query.append(" = ");
                    query.append(req[0]);
                }
                if (declaredField.getType().isAssignableFrom(Enum.class)) {
                    query.append("e.");
                    query.append(declaredField.getName());
                    query.append(" in (");
                    query.append(req.toString());
                    query.append(")");
                }
            }
        }

    }
}
