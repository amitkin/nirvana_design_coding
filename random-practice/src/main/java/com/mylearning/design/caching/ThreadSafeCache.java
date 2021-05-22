package com.mylearning.design.caching;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.locks.*;

public class ThreadSafeCache {
    private final Map<String, Object> map;
    private final ReadWriteLock lock;
    private final Lock readLock;
    private final Lock writeLock;
    private Map<Node, String> sortedMap = new TreeMap<>((a, b)-> Long.compare(a.expiryInmillis, b.expiryInmillis));

    public static class Node {
        private String key;
        private Object value;
        private Long expiryInmillis;

        public Node(String key, Object value, long l) {
            this.key = key;
            this.value = value;
            this.expiryInmillis = l;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Long getExpiryInmillis() {
            return expiryInmillis;
        }

        public void setExpiryInmillis(Long expiryInmillis) {
            this.expiryInmillis = expiryInmillis;
        }
    }

    public ThreadSafeCache() {
        map = new HashMap<>();
        lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public Optional<Object> get(String key) {
        try {
            readLock.lock();
            if (map.containsKey(key)) {
                return Optional.of(map.get(key));
            }
            return Optional.empty();
        } finally {
            readLock.unlock();
        }
    }

    public void set(String key, Object value) {
        try {
            writeLock.lock();
            map.put(key, value);

        } finally {
            writeLock.unlock();
        }
    }

    public void set(String key, Object value, long ttlInMillis) {
        try {
            writeLock.lock();
            // TODO
            Node n = new Node(key, value, System.currentTimeMillis() + ttlInMillis);
            sortedMap.put(n, key);
            map.put(key, n);
        } finally {
            writeLock.unlock();
        }
    }

    public void del(String key) {
        try {
            writeLock.lock();
            map.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void evict() {
        // TODO
        List<Entry<Node, String>> entriesToDelete = new ArrayList<>();
        for(Entry<Node, String> entry : sortedMap.entrySet()) {
            if(entry.getKey().getExpiryInmillis() <= System.currentTimeMillis()) {
                entriesToDelete.add(entry);
            }
        }

        try {
            writeLock.lock();
            if (entriesToDelete.size() > 0) {
                for(Entry<Node, String> entry : entriesToDelete) {
                    map.remove(entry.getValue());
                }
            }
        } finally {
            writeLock.unlock();
        }
        System.out.println("No key to evict!!");
    }

    public static void main(String[] args) {
        ThreadSafeCache cache = new ThreadSafeCache();
        System.out.println(cache.get("a"));
        cache.set("a", "val_a");
        System.out.println(cache.get("a"));
        cache.del("a");
        System.out.println(cache.get("a"));
    }
}
