package nix.solutions.dinix;

import nix.solutions.dinix.annotations.Service;
import nix.solutions.dinix.factory.ObjectFactory;
import nix.solutions.dinix.util.ClassLoaderUtil;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContext {

    private final Map<Class<?>, Object> mapInterfaceAndImplementation = new HashMap<>();
    private Set<Class<?>> interfaces;
    private ApplicationSearcher applicationSearcher;
    private Reflections scanner;
    private ClassLoader mainClassLoader;
    private ObjectFactory objectFactory;

    public ApplicationContext(Class<?> mainClass) {
        try {
            Package mainPackage = mainClass.getPackage();
            String basePackage = mainPackage.getName();
            this.mainClassLoader = mainClass.getClassLoader();
            List<Class<?>> allClasses = ClassLoaderUtil.getClasses(basePackage, this.mainClassLoader);
            Set<Class<?>> interfaces = allClasses.stream().filter(Class::isInterface).collect(Collectors.toSet());
            this.interfaces = initOnlyInterfacesClasses(interfaces);
            this.applicationSearcher = new ApplicationSearcher(basePackage);
            this.scanner = new Reflections(this.getClass().getPackage().getName());
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    public void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    public void applicationInit() {
        initMapInterfaceAndImplementation();
    }

    public Reflections getScanner() {
        return scanner;
    }

    public Map<Class<?>, Object> getMapInterfaceAndImplementation() {
        return mapInterfaceAndImplementation;
    }

    public ClassLoader getMainClassLoader() {
        return mainClassLoader;
    }

    private void initMapInterfaceAndImplementation() {
        for (Class<?> serviceInterface : this.interfaces) {
            getObjectImpl(serviceInterface);
        }
    }

    public  <I> I getObjectImpl(Class<I> type) {
        Class<? extends I> impl = type;
        if (mapInterfaceAndImplementation.containsKey(type)) {
            return (I) mapInterfaceAndImplementation.get(type);
        }
        if (type.isInterface()) {
            impl = this.applicationSearcher.getImplementation(type);
        }
        if (impl == null) {
            return null;
        }
        I i = objectFactory.createObject(impl);
        if (impl.isAnnotationPresent(Service.class)) {
            mapInterfaceAndImplementation.put(type, i);
        }
        return i;
    }

    private Set<Class<?>> initOnlyInterfacesClasses(Set<Class<?>> interfaces) {
        return interfaces
                .stream()
                .filter(this::isServiceClass)
                .collect(Collectors.toSet());
    }

    private boolean isServiceClass(Class<?> currentInterface) {
        return currentInterface.getSimpleName().endsWith(ApplicationStandard.SERVICE_CLASS_DAO.getApplicationsParam()) ||
                currentInterface.getSimpleName().endsWith(ApplicationStandard.SERVICE_CLASS_SERVICE.getApplicationsParam()) ||
                currentInterface.getSimpleName().endsWith(ApplicationStandard.SERVICE_CLASS_FACADE.getApplicationsParam()) ||
                currentInterface.getSimpleName().endsWith(ApplicationStandard.SERVICE_CLASS_CONTROLLER.getApplicationsParam());
    }
}
