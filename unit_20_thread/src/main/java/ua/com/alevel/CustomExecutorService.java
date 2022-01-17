package ua.com.alevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class CustomExecutorService {

    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    private void increment() {
        synchronized (this) {
            count = count + 1;
        }
    }

    private void incrementLock() {
        lock.lock();
        try {
            count = count + 1;
        } finally {
            lock.unlock();
        }
    }

    public void test() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        IntStream.range(0, 10000)
//                .forEach(i -> executor.submit(this::increment));
                .forEach(i -> executor.submit(this::incrementLock));
        stop(executor);
        System.out.println(count);
    }

    private void stop(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
