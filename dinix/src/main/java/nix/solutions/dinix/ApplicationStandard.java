package nix.solutions.dinix;

public enum ApplicationStandard {

    APP_PROPERTIES("application.properties"),
    SERVICE_CLASS_DAO("Dao"),
    SERVICE_CLASS_SERVICE("Service"),
    SERVICE_CLASS_FACADE("Facade"),
    SERVICE_CLASS_CONTROLLER("Controller");

    ApplicationStandard(String applicationsParam) {
        this.applicationsParam = applicationsParam;
    }

    private final String applicationsParam;

    public String getApplicationsParam() {
        return applicationsParam;
    }
}
