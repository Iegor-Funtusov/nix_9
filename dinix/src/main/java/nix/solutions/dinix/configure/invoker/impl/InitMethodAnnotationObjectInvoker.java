package nix.solutions.dinix.configure.invoker.impl;

import nix.solutions.dinix.annotations.InitMethod;
import nix.solutions.dinix.configure.invoker.ObjectInvoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InitMethodAnnotationObjectInvoker implements ObjectInvoker {

    @Override
    public void invoke(Object bean) {
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(InitMethod.class)) {
                declaredMethod.setAccessible(true);
                try {
                    declaredMethod.invoke(bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
