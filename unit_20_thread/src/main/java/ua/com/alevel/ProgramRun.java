package ua.com.alevel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class ProgramRun {

    public static void run() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                navigation();
                String line = reader.readLine();
                threadExam(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void threadExam(String line) {
        switch (line) {
            case "1" -> correctlyStop();
            case "2" -> incorrectlyStop();
            case "3" -> threadSum();
            case "0" -> System.exit(0);
        }
        navigation();
    }

    private static void navigation() {
        System.out.println();
        System.out.println("if you want run correctly stop thread, please enter 1");
        System.out.println("if you want run incorrectly stop thread, please enter 2");
        System.out.println("if you want run thread sum thread, please enter 3");
        System.out.println();
    }

    private static void correctlyStop() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": ProgramRun.correctlyStop");
        System.out.println();
        Thread thread = new Task1("task1 thread");
        thread.start();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("e = " + e.getMessage());
        }
        thread.interrupt();
    }

    private static void incorrectlyStop() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": ProgramRun.incorrectlyStop");
        System.out.println();
        Thread thread = new Task1("task2 thread");
        thread.start();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("e = " + e.getMessage());
        }
        thread.stop();
    }

    private static void threadSum() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": ProgramRun.threadSum");
        int[] arr = MathUtil.randomArrays(1000, 1, 1_000_000);
        runSimpleSum(arr);
        runThreadSum(arr);
    }

    private static void runSimpleSum(int[] arr) {
        System.out.println("ProgramRun.runSimpleSum start");
        long start = System.currentTimeMillis();
        int sum = MathUtil.sum(arr);
        long end = System.currentTimeMillis() - start;
        System.out.println("end = " + end);
        System.out.println("ProgramRun.runSimpleSum finish, sum: " + sum);
    }

    private static void runThreadSum(int[] arr) {
        System.out.println("ProgramRun.runThreadSum start");
        long start = System.currentTimeMillis();
        int sum = 0;

        int threadCount = 2;
        int av = arr.length / 2;

        int[] left = new int[av];
        int[] right = new int[arr.length - av];

        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i < av) {
                left[i] = arr[i];
            } else {
                right[j++] = arr[i];
            }
        }

        int[][] ints = new int[][]{ left, right };

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            ThreadSum thread = new ThreadSum();
            thread.setInts(ints[i]);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            integers.add(thread.getSum());
        }

        for (Integer integer : integers) {
            sum += integer;
        }

        long end = System.currentTimeMillis() - start;
        System.out.println("end = " + end);
        System.out.println("ProgramRun.runThreadSum finish, sum: " + sum);
    }
}
