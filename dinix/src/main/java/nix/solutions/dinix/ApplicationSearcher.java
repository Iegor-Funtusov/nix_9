package nix.solutions.dinix;

import org.reflections.Reflections;

import java.util.Set;

public class ApplicationSearcher {

    private final Reflections scanner;

    public ApplicationSearcher(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    public <I> Class<? extends I> getImplementation(Class<I> type) {
        if (!type.getSimpleName().startsWith("Base")) {
            Set<Class<? extends I>> children = scanner.getSubTypesOf(type);
            for (Class<? extends I> child : children) {
                if (!child.isAnnotationPresent(Deprecated.class)) {
                    return child;
                }
            }
        }
//        throw new RuntimeException("impl not found");
        return null;
    }
}
