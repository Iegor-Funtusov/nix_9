package ua.com.alevel.util;

public final class StringPatternUtil {

    private static final String REGEX_CREATE_TIME_RANGE = "^[0-9]{13}:[0-9]{13}$";

    private StringPatternUtil() { }

    public static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }

    public static boolean dateRegExPattern(String parameter) {
        return parameter.matches(REGEX_CREATE_TIME_RANGE);
    }
}
