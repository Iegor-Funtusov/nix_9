package ua.com.alevel.type;

public enum ApplicationType {

    DB_TYPE("db"),
    DB_LIST_VALUE("list"),
    DB_SET_VALUE("set");

    ApplicationType(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }
}
