package ua.com.alevel.config;

public enum PropertiesConfig {

    APPLICATION_PROPERTIES("application.properties");

    PropertiesConfig(String properties) {
        this.properties = properties;
    }

    private final String properties;

    public String getProperties() {
        return properties;
    }
}
