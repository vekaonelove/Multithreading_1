package com.ehu.task1;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer implements Callable<Void> {
    private static final Logger logger = LogManager.getLogger(Consumer.class);
    private final Buffer buffer;
    private volatile boolean isRunning = true;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public Void call() throws InterruptedException {
        while (isRunning) {
            if (!buffer.isEmpty()) {
                int task = buffer.removeTask();
                logger.info("Consumed task " + task);
                TimeUnit.MILLISECONDS.sleep(200);
            } else {
                logger.warn("Buffer is empty, consumer is waiting...");
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }
        logger.info("Consumer stopped.");
        return null;
    }
}
