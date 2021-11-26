package org.dinix.configurator;

public interface JpaConfigurator {

    void defaultConfigure();
    void updateConfigure();
    void createDropConfigure();
    void initConfigure();
}
