package com.mylearning.cache.eviction;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class BaseCache implements Cache{

    private volatile EvictionPolicy evictionPolicy;

    private Evictor evictor = null;

    public BaseCache(CacheConfig config) {
        setConfig(config);
    }

    public void setConfig(CacheConfig config){
        setEvictionPolicy(config.getEvictionPolicy());
    }

    public void setEvictionPolicy(final EvictionPolicy evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public void evict() {
        EvictionPolicy evictionPolicy = getEvictionPolicy();
        evictionPolicy.evict();
    }

    @Override
    public EvictionPolicy getEvictionPolicy() {
        return evictionPolicy;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public boolean set(String key, String value) {
        return false;
    }

    public void startEvictor(final long delay) {
        if (delay > 0) {
            evictor = new Evictor();
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new EvictorThreadFactory());
            executor.scheduleWithFixedDelay(evictor, delay, delay, TimeUnit.MILLISECONDS);
        }
    }

    class Evictor implements Runnable {

        @Override
        public void run() {
            try {
                evict();
            } catch(final Exception e) {
                System.out.println("Swallow exception!!");
            }
        }
    }

    private static class EvictorThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(final Runnable runnable) {
            return new Thread(null, runnable, "evictor-thread");
        }
    }
}
