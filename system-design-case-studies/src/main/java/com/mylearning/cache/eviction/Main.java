package com.mylearning.cache.eviction;

public class Main {
    public static void main(String[] args) {

        CacheConfig config = new CacheConfig();
        config.setEvictionPolicy(new DefaultEvictionPolicy());

        Cache cache = new LruCache(config);

        cache.set("amit", "10");
        cache.set("arun", "12");
        cache.get("amit");
        cache.startEvictor(5);
    }
}
