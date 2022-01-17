package ua.com.alevel;

public class ThreadSum extends Thread {

    private Integer[] ints;
    private long sum = 0;

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": start");
        this.sum = MathUtil.sum(ints);
        System.out.println(threadName + ": finish");
        this.interrupt();
    }

    public void setInts(Integer[] ints) {
        this.ints = ints;
    }

    public long getSum() {
        return sum;
    }
}
