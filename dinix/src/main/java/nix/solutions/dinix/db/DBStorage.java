package nix.solutions.dinix.db;

import nix.solutions.dinix.PropertieStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBStorage {

    private Connection connection;
    private Statement statement;

    private static DBStorage instance;

    private DBStorage() {
        PropertieStorage propertieStorage = PropertieStorage.getInstance();
        String driver = propertieStorage.getProperties().get("db.driver");
        String url = propertieStorage.getProperties().get("db.url");
        String user = propertieStorage.getProperties().get("db.user");
        String password = propertieStorage.getProperties().get("db.password");
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    public static DBStorage getInstance() {
        if (instance == null) {
            instance = new DBStorage();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
