package com.mylearning.design.solid.examples.cache.factories;

import com.mylearning.design.solid.examples.cache.Cache;
import com.mylearning.design.solid.examples.cache.policies.LRUEvictionPolicy;
import com.mylearning.design.solid.examples.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(),
                new HashMapBasedStorage<Key, Value>(capacity));
    }
}
