package com.ehu.task1.state;

import com.ehu.task1.Buffer;

public class NormalState implements State {
    public void put(Buffer buffer, int task) throws InterruptedException {
        buffer.addTask(task);
    }

    public int get(Buffer buffer) throws InterruptedException {
        return buffer.removeTask();
    }
}