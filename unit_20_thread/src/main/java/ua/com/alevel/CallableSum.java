package ua.com.alevel;

import java.util.concurrent.Callable;

public class CallableSum {

    private Integer[] ints;
    private long sum = 0;

    public void run() {
        Callable<Long> callSum = new Callable<Long>() {
            @Override
            public Long call() {
                return MathUtil.sum(ints);
            }
        };
        try {
            sum = callSum.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInts(Integer[] ints) {
        this.ints = ints;
    }

    public long getSum() {
        return sum;
    }
}
