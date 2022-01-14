package ua.com.alevel;

public class Task1 extends Thread {

    private final String threadName;

    public Task1(String threadName) {
        super(threadName);
        this.threadName = threadName;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": Task1.run");
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        System.out.println(threadName + ": Task1.exit");
    }
}
