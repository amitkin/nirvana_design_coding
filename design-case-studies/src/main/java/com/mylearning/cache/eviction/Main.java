package com.mylearning.cache.eviction;

public class Main {
    public static void main(String[] args) {

        CacheConfig config = new CacheConfig();
        config.setEvictionPolicy(new DefaultEvictionPolicy());

        Cache cache = new LruCache(config);

        cache.set("key1", "10");
        cache.set("key2", "12");
        cache.get("key1");
        cache.startEvictor(5);
    }
}
