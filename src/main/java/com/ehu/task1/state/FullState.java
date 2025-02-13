package com.ehu.task1.state;

import com.ehu.task1.Buffer;

public class FullState implements State {
    @Override
    public void put(Buffer buffer, int task) throws InterruptedException {
        while (buffer.isFull()) {
            buffer.wait();
        }
        buffer.getTasks().add(task);
        buffer.setState(new NormalState());
    }

    @Override
    public int get(Buffer buffer) throws InterruptedException {
        int task = buffer.getTasks().removeFirst();
        buffer.setState(buffer.isEmpty() ? new EmptyState() : new NormalState());
        return task;
    }
}
