package ua.com.alevel.dao.impl;

import nix.solutions.dinix.annotations.InitMethod;
import nix.solutions.dinix.annotations.Service;
import nix.solutions.dinix.annotations.Value;

import ua.com.alevel.dao.CustomerDao;
import ua.com.alevel.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomerDaoImpl implements CustomerDao {

    private Connection connection;
    private Statement statement;

    @Value("db.driver")
    private String driver;

    @Value("db.url")
    private String url;

    @Value("db.user")
    private String user;

    @Value("db.password")
    private String password;

    private static final String CREATE_USER = "insert into customers values(default,?,?,?,?,?)";
    private static final String UPDATE_BY_ID_QUERY = "update customers set name = ?, age = ? where id = ?";
    private static final String FIND_ALL_USER_QUERY = "select * from customers";
    private static final String FIND_USER_BY_ID_QUERY = "select * from customers where id = ";
    private static final String DELETE_USER_BY_ID_QUERY = "delete from customers where id = ";

    @InitMethod
    private void connect() {
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void create(Customer entity) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPhone());
            preparedStatement.setInt(5, 0);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public void delete(Integer id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY + id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public Customer findById(Integer id) {
        try {
            ResultSet resultSet = this.statement.executeQuery(FIND_USER_BY_ID_QUERY + id);
            while (resultSet.next()) {
                return initUserByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Customer> findAll() {
        System.out.println("CustomerDaoImpl.findAll");
        List<Customer> users = new ArrayList<>();
        try {
            ResultSet resultSet = this.statement.executeQuery(FIND_ALL_USER_QUERY);
            while (resultSet.next()) {
                users.add(initUserByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return users;
    }

    private Customer initUserByResultSet(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        int age = resultSet.getInt("age");
        int id = resultSet.getInt("id");
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        return customer;
    }
}
