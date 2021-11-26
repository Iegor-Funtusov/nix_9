package org.dinix.orm.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ResourceUtil {

    private ResourceUtil() { }

    public static Map<String, String> getProperties(ClassLoader classLoader) {
        try(InputStream inputStream = classLoader.getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Map<String, String> map = new HashMap<>();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException("file not found: " + e.getMessage());
        }
    }
}
