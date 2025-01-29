package com.ehu.task1;

import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer implements Callable<Void> {
    private static final Logger logger = LogManager.getLogger(Producer.class);
    private final Buffer buffer;
    private final int tasksToProduce;

    public Producer(Buffer buffer, int tasksToProduce) {
        this.buffer = buffer;
        this.tasksToProduce = tasksToProduce;
    }

    @Override
    public Void call() throws InterruptedException {
        for (int i = 0; i < tasksToProduce; i++) {
            buffer.addTask(i);
            logger.info("Produced task " + i);
        }
        return null;
    }
}
