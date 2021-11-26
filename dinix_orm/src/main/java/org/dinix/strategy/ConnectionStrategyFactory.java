package org.dinix.strategy;

import org.dinix.enums.DiNixOrmDbType;
import org.dinix.orm.DiNixOrmProperties;
import org.dinix.strategy.impl.MySqlConnectionStrategy;

public class ConnectionStrategyFactory {

    private final DiNixOrmProperties properties;

    public ConnectionStrategyFactory(DiNixOrmProperties properties) {
        this.properties = properties;
    }

    public ConnectionStrategy getConnectionStrategy() {
        if (properties.getDriver().contains(DiNixOrmDbType.POSTGRES.getType())) {
            return null;
        }
        if (properties.getDriver().contains(DiNixOrmDbType.MY_SQL.getType())) {
            return new MySqlConnectionStrategy();
        }
        return null;
    }
}
