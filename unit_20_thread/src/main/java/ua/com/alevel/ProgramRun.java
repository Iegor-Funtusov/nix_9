package ua.com.alevel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
            case "4" -> increment();
            case "0" -> System.exit(0);
        }
        navigation();
    }

    private static void navigation() {
        System.out.println();
        System.out.println("if you want run correctly stop thread, please enter 1");
        System.out.println("if you want run incorrectly stop thread, please enter 2");
        System.out.println("if you want run thread sum thread, please enter 3");
        System.out.println("if you want run thread count increment, please enter 4");
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
        Integer[] arr = MathUtil.randomArrays(1_000_000, 1, 1_000_000);
        runSimpleSum(arr);
        runThreadSum(arr);
        try {
            callable(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        future(arr);
    }

    private static void runSimpleSum(Integer[] arr) {
        System.out.println("ProgramRun.runSimpleSum start");
        long start = System.currentTimeMillis();
        long sum = MathUtil.sum(arr);
        long end = System.currentTimeMillis() - start;
        System.out.println("end = " + end);
        System.out.println("ProgramRun.runSimpleSum finish, sum: " + sum);
    }

    private static void runThreadSum(Integer[] arr) {
        System.out.println("ProgramRun.runThreadSum start");

        long sum = 0;

        Integer[][] ints = MathUtil.divideArr(arr);
        List<Integer[]> list = new ArrayList<>();

        List<ThreadSum> threadSums = new ArrayList<>();
        for (Integer[] anInt : ints) {
            Integer[][] ints1 = MathUtil.divideArr(anInt);
            list.add(ints1[0]);
            list.add(ints1[1]);
        }

        long start = System.currentTimeMillis();

        for (Integer[] integers : list) {
            ThreadSum thread = new ThreadSum();
            thread.setInts(integers);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += thread.getSum();
//            threadSums.add(thread);
        }

//        int count = 0;
//        boolean breakWhile = true;
//        while (breakWhile) {
//            count = 0;
//            for (ThreadSum threadSum : threadSums) {
//                if (threadSum.isInterrupted()) {
//                    count += 1;
//                }
//            }
//            if (count == threadSums.size()) {
//                breakWhile = false;
//            }
//        }
//
//        for (ThreadSum threadSum : threadSums) {
//            sum += threadSum.getSum();
//        }

        long end = System.currentTimeMillis() - start;
        System.out.println("end = " + end);
        System.out.println("ProgramRun.runThreadSum finish, sum: " + sum);
    }

    private static void increment() {
        new CustomExecutorService().test();
    }

    private static void callable(Integer[] arr) throws Exception {
        System.out.println("ProgramRun.callable");
        long sum = 0;

        List<Integer[]> list = new ArrayList<>();

        Integer[][] ints = MathUtil.divideArr(arr);

        for (Integer[] anInt : ints) {
            Integer[][] ints1 = MathUtil.divideArr(anInt);
            list.add(ints1[0]);
            list.add(ints1[1]);
        }

        long start = System.currentTimeMillis();

        for (Integer[] integers : list) {
//            CallableSum callableSum = new CallableSum();
//            callableSum.setInts(integers);
//            callableSum.run();

            Callable<Long> callable = () -> MathUtil.sum(integers);

            sum += callable.call();

        }

        long end = System.currentTimeMillis() - start;
        System.out.println("end = " + end);
        System.out.println("ProgramRun.callable finish, sum: " + sum);
    }

    private static void future(Integer[] arr) {
        long sum = 0;
        List<Integer[]> list = new ArrayList<>();
        Integer[][] ints = MathUtil.divideArr(arr);
        for (Integer[] anInt : ints) {
            Integer[][] ints1 = MathUtil.divideArr(anInt);
            list.add(ints1[0]);
            list.add(ints1[1]);
        }
        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(list.size());
        for (Integer[] integers : list) {
            Future<Long> future = executor.submit(() -> MathUtil.sum(integers));
            try {
                sum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis() - start;
        System.out.println("end = " + end);
        System.out.println("ProgramRun.future finish, sum: " + sum);
    }
}
