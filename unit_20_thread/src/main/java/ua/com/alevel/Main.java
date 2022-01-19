package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {
//        ProgramRun.run();
        parallelSum();
    }

    private static void parallelSum() throws Exception {
//        Integer[] arr = MathUtil.randomArrays(1_000_000, 1, 1_000_000);
//        Integer[][] arrr = MathUtil.divideArr(arr);
//        Long sum = 0L;
//        for (Integer[] integers : arrr) {
//            System.out.println("next");
//            Callable<Long> callable = () -> {
//                Thread.sleep(2000);
//                return MathUtil.sum(integers);
//            };
//            sum += callable.call();
//        }
//        System.out.println("sum = " + sum);
//
//        sum = 0L;
//
//        ExecutorService executorService = Executors.newFixedThreadPool(arrr.length);
//        List<Future<Long>> futures = new ArrayList<>();
//        for (Integer[] integers : arrr) {
//            System.out.println("next");
//            Future<Long> future = executorService.submit(() -> {
//                Thread.sleep(2000);
//                return MathUtil.sum(integers);
//            });
//            futures.add(future);
//        }
//
//        while (true) {
//            if (futures.stream().allMatch(Future::isDone)) {
//                for (Future<Long> future : futures) {
//                    sum += future.get();
//                }
//                break;
//            }
//        }

//        System.out.println("sum = " + sum);


        SomeThread someThread = new SomeThread();
        System.out.println("someThread start");
        someThread.start();
//        Thread.sleep(1000);
        someThread.interrupt();
    }

    private static class SomeThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello world");
        }
    }
}
