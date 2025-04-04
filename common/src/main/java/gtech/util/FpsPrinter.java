package gtech.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FpsPrinter extends IntervalTask {
    private static final Logger L = LoggerFactory.getLogger(FpsPrinter.class);

    private long lastFrameCount = 0;

    public FpsPrinter(double interval, boolean triggerImmediately) {
        super(interval, triggerImmediately);
    }

    @Override
    protected void execute() {
        long frameCount = updateCounter - lastFrameCount;
        L.info("FPS: {}", ((double)frameCount / elapsedTime));
        lastFrameCount = updateCounter;
    }
}
