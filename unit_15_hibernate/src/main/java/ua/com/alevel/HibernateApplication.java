package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.dao.EmployeeDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.type.DepartmentType;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class HibernateApplication {

    private final DepartmentDao departmentDao;
    private final EmployeeDao employeeDao;

    private static final DataTableRequest dataTableRequest = new DataTableRequest();

    static {
        dataTableRequest.setOrder("asc");
        dataTableRequest.setSort("id");
        dataTableRequest.setPage(1);
        dataTableRequest.setSize(10);
    }

    public HibernateApplication(DepartmentDao departmentDao, EmployeeDao employeeDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        createDepartments();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createEmployees();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        attachExistEmployeeToDepartments();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        attachNotExistEmployeeToDepartments();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deattachEmployeeToDepartments();
    }

    private void createDepartments() {
        System.out.println("HibernateApplication.createDepartments");
        Department department = new Department();
        department.setDepartmentType(DepartmentType.DEV_OPS);
        department.setName("devs");
        departmentDao.create(department);

        department = new Department();
        department.setDepartmentType(DepartmentType.JAVA);
        department.setName("javists");
        departmentDao.create(department);
    }

    private void createEmployees() {
        System.out.println("HibernateApplication.createEmployees");
        Employee employee = new Employee();
        employee.setFirstName("name11");
        employee.setLastName("name12");
        employee.setAge(34);
        employeeDao.create(employee);

        employee = new Employee();
        employee.setFirstName("name21");
        employee.setLastName("name22");
        employee.setAge(24);
        employeeDao.create(employee);

        employee = new Employee();
        employee.setFirstName("name31");
        employee.setLastName("name32");
        employee.setAge(24);
        employeeDao.create(employee);
    }

    private void attachExistEmployeeToDepartments() {
        System.out.println("HibernateApplication.attachExistEmployeeToDepartments");
        Department department1 = departmentDao.findById(1L);
        Department department2 = departmentDao.findById(2L);

        Employee employee1 = employeeDao.findById(1L);

        department1.addEmployee(employee1);
        departmentDao.update(department1);

        employee1 = employeeDao.findById(1L);
        department2.addEmployee(employee1);
        Employee employee2 = employeeDao.findById(2L);
        department2.addEmployee(employee2);
        departmentDao.update(department2);
    }

    private void attachNotExistEmployeeToDepartments() {
        System.out.println("HibernateApplication.attachNotExistEmployeeToDepartments");
        Department department1 = departmentDao.findById(1L);

        Employee employee = new Employee();
        employee.setFirstName("name1");
        employee.setLastName("name42");
        employee.setAge(44);
        department1.addEmployee(employee);
        departmentDao.update(department1);
    }

    private void deattachEmployeeToDepartments() {
        System.out.println("HibernateApplication.deattachEmployeeToDepartments");
        Department department1 = departmentDao.findById(1L);
        Employee employee = employeeDao.findById(1L);

        department1.removeEmployee(employee);
        departmentDao.update(department1);
    }
}
