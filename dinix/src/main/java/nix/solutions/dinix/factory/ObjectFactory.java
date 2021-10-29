package nix.solutions.dinix.factory;

import nix.solutions.dinix.ApplicationContext;
import nix.solutions.dinix.configure.configurator.ObjectConfigurator;
import nix.solutions.dinix.configure.invoker.ObjectInvoker;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ObjectFactory {

    private final ApplicationContext context;
    private final List<ObjectConfigurator> objectConfigurators = new ArrayList<>();
    private final List<ObjectInvoker> objectInvokers = new ArrayList<>();

    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        initObjectConfiguratorList();
        initObjectInvokers();
    }

    public <I> I createObject(Class<I> impl) {
        I i;
        try {
            i = create(impl);
            configure(i);
            invoke(i);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return i;
    }

    private void initObjectConfiguratorList() {
        Set<Class<? extends ObjectConfigurator>> subTypesOfObjectConfigurator =
                this.context.getScanner().getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> aClass : subTypesOfObjectConfigurator) {
            try {
                objectConfigurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private void initObjectInvokers() {
        Set<Class<? extends ObjectInvoker>> subTypesOfObjectConfigurator =
                this.context.getScanner().getSubTypesOf(ObjectInvoker.class);
        for (Class<? extends ObjectInvoker> aClass : subTypesOfObjectConfigurator) {
            try {
                objectInvokers.add(aClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private <I> I create(Class<? extends I> impl) {
        try {
            return impl.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can not create");
    }

    private <I> void configure(I i) {
        objectConfigurators.forEach(objectConfigurator -> objectConfigurator.configure(i, context));
    }

    private <I> void invoke(I i) {
        objectInvokers.forEach(objectInvoker -> objectInvoker.invoke(i));
    }
}
