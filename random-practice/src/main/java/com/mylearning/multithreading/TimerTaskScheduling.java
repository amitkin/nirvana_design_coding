package com.mylearning.multithreading;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/*
Asked in Confluent - Implement a Timer class
Develop a timer class that manages the execution of deferred tasks. The timer constructor takes as its argument an object
which includes a run method and a string¬valued name field. The class must support—
(1) starting a thread, identified by name, at a given time in the future; and
(2) canceling a thread, identified by name (the cancel request is to be ignored if the thread has already started).
*/

/*
We use two data structures. The first is a min-heap in which we insert key-value pairs: the keys are run times and the
values are the thread to run at that time. A dispatch thread runs these threads; it sleeps from call to call and may be
woken up if a thread is added to or deleted from the pool. If woken up, it advances or retards its remaining sleep time
based on the top of the min-heap. On waking up, it looks for the thread at the top of the min-heap—if its launch time
is the current time, the dispatch thread deletes it from the min-heap and executes it. It then sleeps till the launch
time for the next thread in the min-heap. (Because of deletions, it may happen that the dispatch thread wakes up and
finds nothing to do.)
The second data structure is a hash table with thread ids as keys and entries in the min-heap as values. If we need to
cancel a thread, we go to the min-heap and delete it. Each time a thread is added, we add it to the min-heap; if the
insertion is to the top of the min-heap, we interrupt the dispatch thread so that it can adjust its wake up time. Since
the min-heap is shared by the update methods and the dispatch thread, we need to lock it. The simplest solution is to
have a single lock that is used for all read and writes into the min-heap and the hash table.
*/
public class TimerTaskScheduling {

    //key is thread id and value is entry in the min-heap
    private Map<String, Entry<Long, Thread>> map = new HashMap<>();

    //Each entry is key-value pair: the key is run time and the value is the thread to run at that time.
    private PriorityQueue<Entry<Long, Thread>> queue = new PriorityQueue<>((a,b) -> Long.compare(a.getKey(),b.getKey()));

    private final Object lock = new Object();
    private Thread dispatcherThread;

    //Creates a new Timer with dispatcherThread started using name specified
    public TimerTaskScheduling(String name) {
        dispatcherThread = new DispatchThread(queue, lock);
        dispatcherThread.setName(name);
        dispatcherThread.start();
    }

    public void startThread(String threadName, long time) {
        //This should interrupt the dispatcher thread
        synchronized(lock) {
            Thread thread = new Thread(threadName) {
                public void run() {
                    System.out.println("Running thread " + this.getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Entry<Long, Thread> entry = new AbstractMap.SimpleEntry<>(time, thread);
            queue.offer(entry);
            map.put(thread.getName(), entry);
            lock.notifyAll();
        }
    }

    public boolean cancelThread(String threadName) {
        synchronized(lock) {
            Entry<Long, Thread> entry = map.get(threadName);
            return queue.remove(entry);
        }
    }

    public static class DispatchThread extends Thread {
        private final Object lock;
        private final PriorityQueue<Entry<Long, Thread>> queue;

        public DispatchThread(PriorityQueue<Entry<Long, Thread>> queue, Object lock) {
            this.lock = lock;
            this.queue = queue;
        }
        public void run() {
            System.out.println(Thread.currentThread().getName() + " executing...");
            //On waking up, it looks for the thread at the top of the min-heap — if its launch time is the current time,
            //the dispatch thread deletes it from the min-heap and executes it. It then sleeps till the launch time
            //for the next thread in the min-heap. It sleeps from call to call and may be woken up if a thread is added
            //to or deleted from the pool. Because of deletions, it may happen that the dispatch thread wakes up and
            //finds nothing to do.
            try {
                while (true) {
                    synchronized(lock) {
                        //Thread.sleep() pauses the current thread and does not release any locks.
                        //Acquire the lock and check the queue and schedule
                        while (!queue.isEmpty()) {
                            Entry<Long, Thread> entry = queue.peek();
                            //check with current apoch time if it matches then run the task else wait for the time
                            Long taskTime = entry.getKey();
                            Long currentTime = System.currentTimeMillis();
                            Thread task = entry.getValue();
                            if (currentTime >= taskTime) {
                                task.run();
                                queue.poll();
                            } else {
                                lock.wait(taskTime - currentTime);
                                break;
                            }
                        }
                        if (queue.isEmpty()) {
                            lock.wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimerTaskScheduling timerTaskScheduling = new TimerTaskScheduling("Dispatcher thread");
        //Populate all the tasks here in map and queue
        long epochTime = System.currentTimeMillis();
        timerTaskScheduling.startThread("Thread1", epochTime+10);
        timerTaskScheduling.startThread("Thread2", epochTime+50);
        timerTaskScheduling.startThread("Thread3", epochTime+30);

        timerTaskScheduling.dispatcherThread.join();
    }
}
