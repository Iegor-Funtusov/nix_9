package nix.solutions.dinix.configure.configurator.impl;

import nix.solutions.dinix.ApplicationContext;
import nix.solutions.dinix.annotations.Persistent;
import nix.solutions.dinix.configure.configurator.ObjectConfigurator;
import nix.solutions.dinix.db.DBStorage;

import java.lang.reflect.Field;

public class PersistentAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object o, ApplicationContext context) {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Persistent.class) && declaredField.getType().isAssignableFrom(DBStorage.class)) {
                declaredField.setAccessible(true);
                try {
                    declaredField.set(o, DBStorage.getInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
