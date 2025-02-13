package com.ehu.task1;

import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer implements Callable<Void> {
    private static final Logger logger = LogManager.getLogger(Consumer.class);
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public Void call() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            int task = buffer.removeTask();
            logger.info("Consumed task {}", task);
        }
        return null;
    }
}
