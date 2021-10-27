package nix.solutions.dinix;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class ApplicationStarter {

    private final Collection<Object> controllers;

    public ApplicationStarter(Collection<Object> controllers) {
        this.controllers = controllers;
    }

    public void start() {
        for (Object controller : this.controllers) {
            Method[] methods = controller.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals("run")) {
                    try {
                        method.invoke(controller);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
