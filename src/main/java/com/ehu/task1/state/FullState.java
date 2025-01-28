package com.ehu.task1.state;

import com.ehu.task1.Buffer;

public class FullState implements State {
    public void put(Buffer buffer, int task) {
        throw new IllegalStateException("Buffer is full.");
    }

    public int get(Buffer buffer){
        int task = buffer.removeTask();
        buffer.setState(new NormalState());
        return task;
    }
}
