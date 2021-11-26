package org.dinix.orm;

import org.dinix.strategy.ConnectionStrategy;
import org.dinix.strategy.ConnectionStrategyFactory;

public class DiNixOrmApplication {

    public static void run(Class<?> mainClass) {
        DiNixOrmApplicationContext context = new DiNixOrmApplicationContext(mainClass);
        DiNixOrmProperties diNixOrmProperties = new DiNixOrmProperties();
        diNixOrmProperties.init(mainClass.getClassLoader());
        ConnectionStrategyFactory connectionStrategyFactory = new ConnectionStrategyFactory(diNixOrmProperties);
        ConnectionStrategy connectionStrategy = connectionStrategyFactory.getConnectionStrategy();
        connectionStrategy.connect(diNixOrmProperties);
    }
}
