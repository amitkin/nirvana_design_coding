package com.mylearning.cache.eviction;

public interface Cache {

    String get(String key);

    boolean set(String key, String value);

    void evict();

    EvictionPolicy getEvictionPolicy();

    void startEvictor(long delay);
}
