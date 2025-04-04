package gtech.util;

/**
 * Task that is executed on regular interval on a thread that calls update.
 */
public abstract class IntervalTask {
    protected long updateCounter;
    protected double elapsedTime;
    protected final double interval;
    protected final boolean triggerImmediately;

    public IntervalTask(double interval, boolean triggerImmediately) {
        this.interval = interval;
        this.triggerImmediately = triggerImmediately;
    }

    public void update(double deltaTime) {
        elapsedTime += deltaTime;
        if (elapsedTime >= interval || (triggerImmediately && updateCounter == 0)) {
            execute();
            elapsedTime = 0;
        }
        updateCounter++;
    }

    protected abstract void execute();

}
