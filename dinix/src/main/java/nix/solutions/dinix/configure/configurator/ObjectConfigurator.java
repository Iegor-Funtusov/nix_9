package nix.solutions.dinix.configure.configurator;

import nix.solutions.dinix.ApplicationContext;

public interface ObjectConfigurator {

    void configure(Object o, ApplicationContext context);
}
