package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.type.DepartmentType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ua.com.alevel.dao.query.JpaQueryUtil.*;

@Service
public class EmployeeDaoImpl implements EmployeeDao {

    private final JpaConfig jpaConfig;

    public EmployeeDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Employee employee) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_EMPLOYEE_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(employee.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(employee.getUpdated().getTime()));
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getFirstName());
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setLong(6, employee.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Employee employee) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_EMPLOYEE_BY_ID_QUERY + employee.getId())) {
            preparedStatement.setInt(1, employee.getAge());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_EMPLOYEES_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_EMPLOYEE_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
                System.out.println("count = " + count);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Employee findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_EMPLOYEE_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initEmployeeByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_EMPLOYEES_QUERY)) {
            while (resultSet.next()) {
                employees.add(initEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return employees;
    }

    @Override
    public void deleteAllByDepartmentId(Long departmentId) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_EMPLOYEES_BY_DEPARTMENT_ID_QUERY + departmentId)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findAllByDepartmentId(Long departmentId) {
        List<Employee> employees = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_EMPLOYEES_BY_DEPARTMENT_ID_QUERY + departmentId)) {
            while (resultSet.next()) {
                employees.add(initEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return employees;
    }

    private Employee initEmployeeByResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        Department department = new Department();
        long employeeId = resultSet.getLong("emp.id");
        Timestamp employeeCreated = resultSet.getTimestamp("emp.created");
        Timestamp employeeUpdated = resultSet.getTimestamp("emp.updated");
        int age = resultSet.getInt("age");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        employee.setId(employeeId);
        employee.setAge(age);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setCreated(new Date(employeeCreated.getTime()));
        employee.setUpdated(new Date(employeeUpdated.getTime()));

        long departmentId = resultSet.getLong("dep.id");
        Timestamp departmentCreated = resultSet.getTimestamp("dep.created");
        Timestamp departmentUpdated = resultSet.getTimestamp("dep.updated");
        String name = resultSet.getString("name");
        String departmentType = resultSet.getString("department_type");

        department.setId(departmentId);
        department.setName(name);
        department.setDepartmentType(DepartmentType.valueOf(departmentType));
        department.setCreated(new Date(departmentCreated.getTime()));
        department.setUpdated(new Date(departmentUpdated.getTime()));

        employee.setDepartment(department);
        return employee;
    }
}
