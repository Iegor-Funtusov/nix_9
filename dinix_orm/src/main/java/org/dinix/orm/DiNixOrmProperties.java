package org.dinix.orm;

import org.dinix.enums.DiNixOrmStrategy;
import org.dinix.orm.util.ResourceUtil;

import java.util.Map;

public class DiNixOrmProperties {

    private String driver;
    private String user;
    private String password;
    private String url;
    private DiNixOrmStrategy strategy;
    private String schema;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void init(ClassLoader classLoader) {
        Map<String, String> map = ResourceUtil.getProperties(classLoader);
        this.driver = map.get("datasource.driver");
        this.url = map.get("datasource.url");
        this.user = map.get("datasource.username");
        this.password = map.get("datasource.password");
        this.strategy = DiNixOrmStrategy.valueOf(map.get("datasource.strategy").toUpperCase());

        System.out.println("this = " + this);
    }

    public DiNixOrmStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(DiNixOrmStrategy strategy) {
        this.strategy = strategy;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        return "DiNixOrmProperties{" +
                "driver='" + driver + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", strategy=" + strategy +
                ", schema=" + schema +
                '}';
    }
}
