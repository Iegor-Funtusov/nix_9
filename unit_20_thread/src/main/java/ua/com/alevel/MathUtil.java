package ua.com.alevel;

import java.util.Random;

public final class MathUtil {

    private MathUtil() { }

    public static long sum(final Integer[] ints) {
        long sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        return sum;
    }

    public static Integer[] randomArrays(int size, int min, int max) {
        Integer[] ints = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(min, max);
        }
        return ints;
    }

    public static Integer[][] divideArr(final Integer[] arr) {
        int av = arr.length / 2;
        Integer[]left = new Integer[av];
        Integer[]right = new Integer[arr.length - av];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i < av) {
                left[i] = arr[i];
            } else {
                right[j++] = arr[i];
            }
        }
        return new Integer[][]{ left, right };
    }
}
