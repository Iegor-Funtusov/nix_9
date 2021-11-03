package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StreamParallel {

    private static final int SIZE = 100_000_000;

    public void run() {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            integers.add(random.nextInt(1000));
        }

        long start = System.currentTimeMillis();
        int sum = integers.stream().reduce(Integer::sum).get();
        long end = System.currentTimeMillis() - start;
        System.out.println("end stream = " + end);

        start = System.currentTimeMillis();
        sum = integers.stream().parallel().reduce(Integer::sum).get();
        end = System.currentTimeMillis() - start;
        System.out.println("end parallel = " + end);

        start = System.currentTimeMillis();
        sum = integers.parallelStream().reduce(Integer::sum).get();
        end = System.currentTimeMillis() - start;
        System.out.println("end parallelStream = " + end);
    }
}
