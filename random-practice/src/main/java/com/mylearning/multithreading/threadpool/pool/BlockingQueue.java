package com.mylearning.multithreading.threadpool.pool;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue <T> {
    private Queue<T> queue = new LinkedList<>();
    private int EMPTY = 0;
    private int MAX_TASK_IN_QUEUE = -1;

    public BlockingQueue(int size){
        this.MAX_TASK_IN_QUEUE = size;
    }

    public synchronized void enqueue(T task) throws InterruptedException  {
        while(this.queue.size() == this.MAX_TASK_IN_QUEUE) {
            wait(); //causes the current thread to wait until another thread invokes the notify method
        }
        if(this.queue.size() == EMPTY) {
            notifyAll(); //wakes up all the threads that are waiting
        }
        this.queue.offer(task);
    }

    public synchronized T dequeue() throws InterruptedException{
        while(this.queue.size() == EMPTY){
            wait();
        }
        if(this.queue.size() == this.MAX_TASK_IN_QUEUE){
            notifyAll();
        }
        return this.queue.poll();
    }
}