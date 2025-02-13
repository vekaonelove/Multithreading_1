package com.ehu.task1.state;

import com.ehu.task1.Buffer;

public class NormalState implements State {
    @Override
    public void put(Buffer buffer, int task) throws InterruptedException {
        buffer.getTasks().add(task);
        buffer.setState(buffer.isFull() ? new FullState() : this);
    }

    @Override
    public int get(Buffer buffer) throws InterruptedException {
        int task = buffer.getTasks().removeFirst();
        buffer.setState(buffer.isEmpty() ? new EmptyState() : this);
        return task;
    }
}
