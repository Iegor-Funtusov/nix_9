package org.dinix.orm;

import org.dinix.storage.JpaStorage;
import org.reflections.Reflections;
import org.reflections.Store;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class DiNixOrmApplicationContext {

    private final Reflections reflections;
    private final Set<Class<?>> daos = new HashSet<>();
    private final ClassLoader appClassLoader;

    public DiNixOrmApplicationContext(Class<?> mainClass) {
        reflections = new Reflections(mainClass.getPackage().getName());
        appClassLoader = mainClass.getClassLoader();
        Store store = reflections.getStore();
        store.forEach((k, v) -> {
            if (k.equals("SubTypes")) {
                v.forEach((kk, vv) -> {
                    Set<Class<?>> childrenClasses = new HashSet<>();
                    vv.forEach(nameClass -> {
                        try {
                            childrenClasses.add(Class.forName(nameClass));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                    initDaoClasses(childrenClasses);
                });
            }
            if (k.equals("TypesAnnotated")) {
                v.forEach((kk, vv) -> {
                    if (kk.equals(Entity.class.getName())) {
                        vv.forEach(entity -> {
                            try {
                                JpaStorage.getInstance().getEntities().add(Class.forName(entity));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                });
            }
        });
    }

    private void initDaoClasses(Set<Class<?>> childrenClasses) {
        childrenClasses.forEach(childrenClass -> {
            Field[] fields = childrenClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType().isAssignableFrom(EntityManager.class)) {
                    daos.add(childrenClass);
                }
            }
        });
        daos.forEach(dao -> {

        });

    }
}
