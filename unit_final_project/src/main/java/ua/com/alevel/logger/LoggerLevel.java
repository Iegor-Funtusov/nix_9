package ua.com.alevel.logger;

public enum LoggerLevel {

    INFO("info"),
    WARN("warn"),
    ERROR("error");

    LoggerLevel(String level) {
        this.level = level;
    }

    private final String level;

    public String getLevel() {
        return level;
    }
}
