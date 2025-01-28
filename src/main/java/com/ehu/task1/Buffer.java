package com.ehu.task1;
import com.ehu.task1.state.EmptyState;
import com.ehu.task1.state.State;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final int capacity;
    private final LinkedList<Integer> tasks = new LinkedList<>();
    private State currentState;
    private final Lock lock = new ReentrantLock();

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.currentState = new EmptyState();
    }

    public void addTask(int task){
        lock.lock();
        try {
            tasks.add(task);
        } finally {
            lock.unlock();
        }
    }

    public int removeTask() {
        lock.lock();
        try {
            return tasks.removeFirst();
        } finally {
            lock.unlock();
        }
    }

    public State getState() {
        return currentState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isFull() {
        return tasks.size() >= capacity;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
