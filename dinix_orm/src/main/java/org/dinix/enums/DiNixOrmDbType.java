package org.dinix.enums;

public enum DiNixOrmDbType {

    MY_SQL("mysql"),
    POSTGRES("postgres"),
    ORACLE("oracle");

    DiNixOrmDbType(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }
}
