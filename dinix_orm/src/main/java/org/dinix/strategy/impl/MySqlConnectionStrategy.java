package org.dinix.strategy.impl;

import org.dinix.configurator.JpaConfigurator;
import org.dinix.configurator.impl.MySqlJpaConfigurator;
import org.dinix.enums.DiNixOrmStrategy;
import org.dinix.orm.DiNixOrmProperties;
import org.dinix.strategy.ConnectionStrategy;

import java.sql.*;

public class MySqlConnectionStrategy implements ConnectionStrategy {

    @Override
    public void connect(DiNixOrmProperties diNixOrmProperties) {
        Connection connection = null;
        Statement statement = null;
        DiNixOrmStrategy strategy = diNixOrmProperties.getStrategy();
        String url = diNixOrmProperties.getUrl();
        System.out.println("url = " + url);
        String schema = url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));
        System.out.println("schema = " + schema);
        if (strategy.equals(DiNixOrmStrategy.INIT)) {
            String newUrl = url.replaceAll("/" + schema, "");
            System.out.println("newUrl = " + newUrl);
            diNixOrmProperties.setUrl(newUrl);
        }
        diNixOrmProperties.setSchema(schema);
        try {
            Class.forName(diNixOrmProperties.getDriver());
            connection = DriverManager.getConnection(diNixOrmProperties.getUrl(), diNixOrmProperties.getUser(), diNixOrmProperties.getPassword());
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        JpaConfigurator configurator = new MySqlJpaConfigurator(statement, connection, diNixOrmProperties);

        switch (strategy) {
            case INIT -> configurator.initConfigure();
            case CREATE_DROP -> configurator.createDropConfigure();
            case UPDATE -> configurator.updateConfigure();
            case NONE -> configurator.defaultConfigure();
        }
    }
}
