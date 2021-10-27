package nix.solutions.dinix.configure.configurator.impl;

import nix.solutions.dinix.ApplicationContext;
import nix.solutions.dinix.annotations.Autowired;
import nix.solutions.dinix.configure.configurator.ObjectConfigurator;

import java.lang.reflect.Field;

public class AutowiredAnnotationsObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object implClass, ApplicationContext context) {
        Field[] fields = implClass.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                try {
                    field.set(implClass, context.getObjectImpl(field.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
