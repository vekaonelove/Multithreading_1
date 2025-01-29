package com.ehu.task1;

import com.ehu.task1.state.EmptyState;
import com.ehu.task1.state.FullState;
import com.ehu.task1.state.NormalState;
import com.ehu.task1.state.State;

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
        while (tasks.size() >= capacity) {
            wait(); // Wait if buffer is full
        }
        tasks.add(task);
        updateState();
        notifyAll(); // Notify consumers
    }

    public synchronized int removeTask() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait(); // Wait if buffer is empty
        }
        int task = tasks.removeFirst();
        updateState();
        notifyAll(); // Notify producers
        return task;
    }

    public synchronized void setState(State state) { // Ensure this method exists
        this.currentState = state;
    }

    private void updateState() {
        if (tasks.isEmpty()) {
            setState(new EmptyState());
        } else if (tasks.size() >= capacity) {
            setState(new FullState());
        } else {
            setState(new NormalState());
        }
    }

    public boolean isFull() {
        return tasks.size() >= capacity;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
