package com.ehu.task1;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int bufferCapacity =2;
        int numProducers = 2;
        int numConsumers = 2;
        int tasksToProduce = 1;

        Buffer buffer = new Buffer(bufferCapacity);
        ExecutorService executor = Executors.newFixedThreadPool(numProducers + numConsumers);

        for (int i = 0; i < numProducers; i++) {
            executor.submit(new Producer(buffer, tasksToProduce));
        }

        for (int i = 0; i < numConsumers; i++) {
            executor.submit(new Consumer(buffer));
        }

        TimeUnit.SECONDS.sleep(10);
        executor.shutdown();
    }
}
