package com.mylearning.parallelcomputing.threadpool.app;

import com.mylearning.parallelcomputing.threadpool.pool.ThreadPool;

public class TestThreadPool {
    public static void main(String[] args) throws InterruptedException {
    	//create queue size - 3
    	//Number of threads - 4
        ThreadPool threadPool = new ThreadPool(3,4);
        //Created 15 Tasks and submit to pool
        for(int taskNumber = 1 ; taskNumber <= 1000; taskNumber++) {
            TestTask task = new TestTask(taskNumber);
            threadPool.submitTask(task);
        }
    }
}
