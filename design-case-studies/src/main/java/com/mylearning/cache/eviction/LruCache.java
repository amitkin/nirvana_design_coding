package com.mylearning.cache.eviction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LruCache extends BaseCache {

    private Map<String, String> cachedObjects = new ConcurrentHashMap<>();

    public LruCache(CacheConfig config) {
        super(config);
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public boolean set(String key, String value) {
        return false;
    }
}
