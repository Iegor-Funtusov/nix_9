package nix.solutions.dinix;

import nix.solutions.dinix.factory.ObjectFactory;

import java.util.Collection;
import java.util.Map;

public class DinixApplication {

    public static void run(Class<?> mainClass) {
        ApplicationContext applicationContext = new ApplicationContext(mainClass);
        PropertieStorage.getInstance().initProperties(applicationContext.getMainClassLoader());
        ObjectFactory objectFactory = new ObjectFactory(applicationContext);
        applicationContext.setObjectFactory(objectFactory);
        applicationContext.applicationInit();
        Map<Class<?>, Object> mapInterfaceAndImplementation = applicationContext.getMapInterfaceAndImplementation();
        Collection<Object> controllers = mapInterfaceAndImplementation.values();
        ApplicationStarter applicationStarter = new ApplicationStarter(controllers);
        applicationStarter.start();
    }
}
