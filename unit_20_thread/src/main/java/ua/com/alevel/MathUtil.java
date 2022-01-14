package ua.com.alevel;

import java.util.Random;

public final class MathUtil {

    private MathUtil() { }

    public static int sum(final int[] ints) {
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        return sum;
    }

    public static int[] randomArrays(int size, int min, int max) {
        int[] ints = new int[size];
        Random random = new Random();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(min, max);
        }
        return ints;
    }
}
