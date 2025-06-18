package com.coderunner;

import java.util.concurrent.*;

//Cache with expire time Java data structure
public class ExpiringCache<K, V> {
    private final ConcurrentHashMap<K, CacheEntry<V>> cache;
    private final ScheduledExecutorService scheduler;

    public ExpiringCache(int initialCapacity, int concurrencyLevel, long expireTime, TimeUnit timeUnit) {
        cache = new ConcurrentHashMap<>(initialCapacity, 0.75f, concurrencyLevel);
        scheduler = Executors.newScheduledThreadPool(1);

        // Schedule a periodic cleanup task
        scheduler.scheduleAtFixedRate(this::cleanup, expireTime, expireTime, timeUnit);
    }

    public void put(K key, V value, long expireTime, TimeUnit timeUnit) {
        CacheEntry<V> entry = new CacheEntry<>(value, System.currentTimeMillis() + timeUnit.toMillis(expireTime));
        cache.put(key, entry);
    }

    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            return null;
        }
        return entry.value;
    }

    public void remove(K key) {
        cache.remove(key);
    }

    private void cleanup() {
        long now = System.currentTimeMillis();
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired(now));
    }

    private static class CacheEntry<V> {
        final V value;
        final long expireTime;

        CacheEntry(V value, long expireTime) {
            this.value = value;
            this.expireTime = expireTime;
        }

        boolean isExpired() {
            return isExpired(System.currentTimeMillis());
        }

        boolean isExpired(long now) {
            return expireTime <= now;
        }
    }

    public static void main(String[] args) {
        ExpiringCache<String, String> cache = new ExpiringCache<>(10, 1, 1, TimeUnit.MINUTES);

        cache.put("key1", "value1", 30, TimeUnit.SECONDS);
        cache.put("key2", "value2", 1, TimeUnit.MINUTES);

        System.out.println(cache.get("key1")); // prints "value1"
        System.out.println(cache.get("key2")); // prints "value2"

        try {
            Thread.sleep(31000); // wait for 31 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(cache.get("key1")); // prints null (expired)
        System.out.println(cache.get("key2")); // prints "value2" (still valid)
    }
}

