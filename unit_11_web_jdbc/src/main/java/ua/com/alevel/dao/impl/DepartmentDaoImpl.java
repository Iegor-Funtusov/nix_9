package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.entity.Department;
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
public class DepartmentDaoImpl implements DepartmentDao {

    private final JpaConfig jpaConfig;

    public DepartmentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Department department) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_DEPARTMENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(department.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(department.getUpdated().getTime()));
            preparedStatement.setString(3, department.getDepartmentType().name());
            preparedStatement.setString(4, department.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Department department) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_BY_ID_QUERY + department.getId())) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getDepartmentType().name());
            preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_DEPARTMENT_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_DEPARTMENT_BY_ID_QUERY + id)) {
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
    public Department findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_DEPARTMENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initDepartmentByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_DEPARTMENTS_QUERY)) {
            while (resultSet.next()) {
                departments.add(initDepartmentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return departments;
    }

    private Department initDepartmentByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String type = resultSet.getString("department_type");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setDepartmentType(DepartmentType.valueOf(type));
        department.setCreated(new Date(created.getTime()));
        department.setUpdated(new Date(updated.getTime()));
        return department;
    }
}
