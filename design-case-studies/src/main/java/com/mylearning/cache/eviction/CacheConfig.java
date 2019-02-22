package com.mylearning.cache.eviction;

public class CacheConfig {

    private EvictionPolicy evictionPolicy;

    public EvictionPolicy getEvictionPolicy() {
        return evictionPolicy;
    }

    public void setEvictionPolicy(EvictionPolicy evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }
}
