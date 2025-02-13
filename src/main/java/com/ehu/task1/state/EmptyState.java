package com.ehu.task1.state;

import com.ehu.task1.Buffer;

public class EmptyState implements State {
    @Override
    public void put(Buffer buffer, int task) throws InterruptedException {
        buffer.getTasks().add(task);
        buffer.setState(new NormalState());
    }

    @Override
    public int get(Buffer buffer) throws InterruptedException {
        while (buffer.isEmpty()) {
            buffer.wait();
        }
        int task = buffer.getTasks().removeFirst();
        buffer.setState(buffer.isEmpty() ? new EmptyState() : new NormalState());
        return task;
    }
}
