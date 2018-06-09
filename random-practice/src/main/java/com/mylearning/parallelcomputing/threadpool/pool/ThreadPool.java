package com.mylearning.parallelcomputing.threadpool.pool;

public class ThreadPool {
	
    BlockingQueue<Runnable> queue;
    public ThreadPool(int queueSize, int nThread) {
        queue = new BlockingQueue<>(queueSize);
        String threadName;
        TaskExecutor task;
        for (int count = 0; count < nThread; count++) {
        	threadName = "Thread-"+count;
        	task = new TaskExecutor(queue);
            Thread thread = new Thread(task, threadName);
            thread.start();
        }
    }

    public void submitTask(Runnable task) throws InterruptedException {
        queue.enqueue(task);
    }
}