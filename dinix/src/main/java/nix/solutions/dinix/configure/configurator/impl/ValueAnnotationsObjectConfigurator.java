package nix.solutions.dinix.configure.configurator.impl;

import nix.solutions.dinix.ApplicationContext;
import nix.solutions.dinix.annotations.Value;
import nix.solutions.dinix.configure.configurator.ObjectConfigurator;
import nix.solutions.dinix.util.ResourceUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class ValueAnnotationsObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object o, ApplicationContext context) {
        Map<String, String> map = ResourceUtil.getProperties(context.getMainClassLoader());
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Value.class)) {
                Value properties = declaredField.getAnnotation(Value.class);
                String value = properties.value();
                String props = map.get(value);
                if (StringUtils.isNotBlank(props)) {
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(o, props);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
