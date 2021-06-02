package com.mylearning.design;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

//Changing two arrays - hits, times to AtomicIntegerArray does not make it thread safe.

//How to design counter not just in last x time but till now, see below comments
//Our goal is to remove write contention so we want to parallelize writes.
//In real scenarios where timestamps are not unique, especially with a lot of users. Keep appending stuff until it becomes unique.
//For example - time stamp + user ID + user comment ID
//Reads are cheap so we replace having a single easily read counter with having to make multiple reads to recover the actual count.
//Read Sharded Counters - http://highscalability.com/numbers-everyone-should-know

//basic idea is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300 seconds.
//hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which record current time.
//If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.

/*
Distribute the counter

When a single machine gets too many traffic and performance becomes an issue, it’s the perfect time to think of distributed solution.
Distributed system significantly reduces the burden of a single machine by scaling the system to multiple nodes, but at the same time
adding complexity. Let’s say we distribute visit requests to multiple machines equally. I’d like to emphasize the importance of equal
distribution first. If particular machines get much more traffic than the rest machines, the system doesn’t get to its full usage and
it’s very important to take this into consideration when designing the system. In our case, we can get a hash of users email and distribute
by the hash (it’s not a good idea to use email directly as some letter may appear much more frequent than the others).
To count the number, each machine works independently to count its own users from the past minute. When we request the global number,
we just need to add all counters together.
 */
class HitCounter {
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    // store each last get hit timestamp with that bucket
    int[] times;
    // store the number of hit for that bucket
    int[] hits;

    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new int[300];
        times = new int[300];
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        w.lock();
        try {
            int idx = timestamp % 300;
            //0-299, 300-599 - For next window timestamp will not match so reset it
            if (times[idx] != timestamp) {
                // not in the same 5 minute window
                times[idx] = timestamp;
                hits[idx] = 1;
            } else {
                hits[idx]++;
            }

        } finally {
            w.unlock();
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int sum = 0;
        r.lock();
        try {
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    sum += hits[i];
                }
            }
        } finally {
            r.unlock();
        }
        return sum;
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();
        // hit at timestamp 1.
        counter.hit(1);
        // hit at timestamp 2.
        counter.hit(2);
        // hit at timestamp 3.
        counter.hit(3);
        // get hits at timestamp 4, should return 3.
        System.out.println(counter.getHits(4));
        // hit at timestamp 300.
        counter.hit(300);
        // get hits at timestamp 300, should return 4.
        System.out.println(counter.getHits(300));
        // get hits at timestamp 301, should return 3.
        System.out.println(counter.getHits(301));
    }
}

