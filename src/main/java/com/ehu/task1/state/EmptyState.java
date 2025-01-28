package com.ehu.task1.state;

import com.ehu.task1.Buffer;

public class EmptyState implements State {
    public void put(Buffer buffer, int task) {
        buffer.addTask(task);
        buffer.setState(new NormalState());
    }

    public int get(Buffer buffer){
        throw new IllegalStateException("Buffer is empty.");
    }
}



