package gtech.util;

public final class StopWatch {
    private long startTime;

    public StopWatch() {
        startTime = System.nanoTime();
    }

    public long elapsedTimeNanos() {
        return System.nanoTime() - startTime;
    }

    public double elapsedTime() {
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    public void reset() {
        startTime = System.nanoTime();
    }
}
