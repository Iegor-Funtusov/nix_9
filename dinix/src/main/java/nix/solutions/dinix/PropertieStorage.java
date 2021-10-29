package nix.solutions.dinix;

import nix.solutions.dinix.util.ResourceUtil;

import java.util.Map;

public class PropertieStorage {

    private static PropertieStorage instance;
    private Map<String, String> properties;

    private PropertieStorage() { }

    public static PropertieStorage getInstance() {
        if (instance == null) {
            instance = new PropertieStorage();
        }
        return instance;
    }

    public void initProperties(ClassLoader classLoader) {
        properties = ResourceUtil.getProperties(classLoader);
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
