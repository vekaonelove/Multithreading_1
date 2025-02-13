package com.ehu.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        int bufferCapacity = 1;
        int numProducers = 2;
        int numConsumers = 2;
        int tasksToProduce = 10;

        Buffer buffer = new Buffer(bufferCapacity);
        ExecutorService executor = Executors.newFixedThreadPool(numProducers + numConsumers);

        for (int i = 0; i < numProducers; i++) {
            executor.submit(new Producer(buffer, tasksToProduce));
        }

        for (int i = 0; i < numConsumers; i++) {
            executor.submit(new Consumer(buffer));
        }
        executor.shutdown();

        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        logger.info("Execution completed.");
    }
}
