package com.ehu.task1.state;

import com.ehu.task1.Buffer;

public interface State {
    void put(Buffer buffer, int task) throws InterruptedException;
    int get(Buffer buffer) throws InterruptedException;
}
