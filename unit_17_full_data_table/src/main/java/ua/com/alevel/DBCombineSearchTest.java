package ua.com.alevel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class DBCombineSearchTest {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final DataTableRequest dataTableRequest = initDataTableRequest();

    public DBCombineSearchTest(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    public void init() {
        System.out.println("start");
        long start = System.currentTimeMillis();
        Gson gson = new Gson();
        try(FileReader fileReader = new FileReader("names.json")) {
            List<String> names = gson.fromJson(fileReader, new TypeToken<List<String>>(){}.getType());
            System.out.println("names = " + names.size());
            Random random = new Random();
            for (int i = 0; i < 2551; i++) {
                if (i % 500 == 0) {
                    try {
                        Thread.sleep(3000);
                        System.out.println("new Date() = " + new Date().getTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Employee employee = new Employee();
                int age = random.nextInt(18, 65);
                employee.setAge(age);
                int first = random.nextInt(0, 21985);
                employee.setFirstName(names.get(first));
                int last = random.nextInt(0, 21985);
                employee.setLastName(names.get(last));
                employeeService.create(employee);
            }
            long end = System.currentTimeMillis() - start;
            System.out.println("finish: " + end + " .ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchEmployeesByParams() {
        System.out.println("DBCombineSearchTest.searchEmployeesByParams");

//        new Date() = 1638968006092
//        new Date() = 1638968010508
//        new Date() = 1638968014622
//        new Date() = 1638968018389
//        new Date() = 1638968022402
//        new Date() = 1638968026424
        Map<String, String[]> map = new HashMap<>();
        map.put("firstName", new String[] { "za", "b" });
        map.put("age", new String[] { "25", "45" });
        map.put("created", new String[] { "1638968006092", "1638968010508" });
        dataTableRequest.setRequestParamMap(map);

        DataTableResponse<Employee> employeeDataTableResponse = employeeService.findAll(dataTableRequest);
        System.out.println("employeeDataTableResponse = " + employeeDataTableResponse.getItemsSize());
    }

    public void searchDepartmentsByParams() {

    }

    private DataTableRequest initDataTableRequest() {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(10);
        dataTableRequest.setPage(1);
        dataTableRequest.setOrder("desc");
        dataTableRequest.setSort("id");
        return dataTableRequest;
    }
}
