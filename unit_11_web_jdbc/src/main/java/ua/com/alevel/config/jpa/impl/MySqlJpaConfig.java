package ua.com.alevel.config.jpa.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.config.jpa.JpaPropertyConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class MySqlJpaConfig implements JpaConfig {

    private final JpaPropertyConfig jpaPropertyConfig;

    private Connection connection;
    private Statement statement;

    public MySqlJpaConfig(JpaPropertyConfig jpaPropertyConfig) {
        this.jpaPropertyConfig = jpaPropertyConfig;
    }

    @Override
    public void connect() {
        System.out.println("jpaPropertyConfig = " + jpaPropertyConfig);
        try {
            Class.forName(jpaPropertyConfig.getDriverClassName());
            connection = DriverManager.getConnection(
                    jpaPropertyConfig.getUrl(),
                    jpaPropertyConfig.getUsername(),
                    jpaPropertyConfig.getPassword()
            );
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Statement getStatement() {
        return statement;
    }
}
