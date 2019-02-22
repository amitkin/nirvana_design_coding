package com.mylearning.cache.eviction;

public class DefaultEvictionPolicy implements EvictionPolicy {

    @Override
    public boolean evict() {
        return true;
    }
}
