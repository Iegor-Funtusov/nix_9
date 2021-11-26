package org.dinix.strategy;

import org.dinix.orm.DiNixOrmProperties;

public interface ConnectionStrategy {

    void connect(DiNixOrmProperties diNixOrmProperties);
}
