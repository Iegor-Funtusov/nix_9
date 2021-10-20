package ua.com.alevel.config;

import org.reflections.Reflections;
import ua.com.alevel.db.UserDB;
import ua.com.alevel.db.impl.UserListDBImpl;
import ua.com.alevel.db.impl.UserSetDBImpl;
import ua.com.alevel.type.ApplicationType;
import ua.com.alevel.util.ResourcesUtil;

import java.util.Map;
import java.util.Set;

public class ApplicationConfig {

    public static <I> I getImpl(Class<I> iface) {
        Reflections scan = new Reflections("ua.com.alevel");
        Set<Class<? extends I>> imls = scan.getSubTypesOf(iface);
        for (Class<? extends I> iml : imls) {
            if (iface.isAssignableFrom(UserDB.class)) {
                Map<String, String> map = ResourcesUtil.getResource(iface.getClassLoader());
                String db = map.get(ApplicationType.DB_TYPE.getType());
                if (db.equals(ApplicationType.DB_SET_VALUE.getType())) {
                    return (I) UserSetDBImpl.getInstance();
                } else {
                    return (I) UserListDBImpl.getInstance();
                }
            }
            if (!iml.isAnnotationPresent(Deprecated.class)) {
                try {
                    return iml.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
