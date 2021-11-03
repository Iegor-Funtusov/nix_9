package ua.com.alevel;

public final class EvenUtil {

    private EvenUtil() { }

    public static boolean isEven(Integer integer) {
        return integer % 2 == 0;
    }

    public static boolean isEven(Integer integer, Integer d) {
        return integer % 2 == 0;
    }
}
