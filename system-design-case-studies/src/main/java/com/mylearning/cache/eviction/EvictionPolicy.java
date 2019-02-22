package com.mylearning.cache.eviction;

public interface EvictionPolicy {
    //Scheduled execution of this method examines the cache key
    //if it can be evicted return true else false
    boolean evict();
}
