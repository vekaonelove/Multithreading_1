package com.ehu.task1;

import com.ehu.task1.state.*;
import java.util.LinkedList;

public class Buffer {
    private final int capacity;
    private final LinkedList<Integer> tasks = new LinkedList<>();
    private State currentState;

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.currentState = new EmptyState();
    }

    public synchronized void addTask(int task) throws InterruptedException {
        currentState.put(this, task);
        notifyAll();
    }

    public synchronized int removeTask() throws InterruptedException {
        int task = currentState.get(this);
        notifyAll();
        return task;
    }

    public synchronized void setState(State state) {
        this.currentState = state;
    }

    public int getCapacity() {
        return capacity;
    }

    public LinkedList<Integer> getTasks() {
        return tasks;
    }

    public boolean isFull() {
        return tasks.size() >= capacity;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
