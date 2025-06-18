package com.mylearning.lld.cache.factories;

import com.mylearning.lld.cache.Cache;
import com.mylearning.lld.cache.policies.LRUEvictionPolicy;
import com.mylearning.lld.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(), new HashMapBasedStorage<Key, Value>(capacity));
    }
}
