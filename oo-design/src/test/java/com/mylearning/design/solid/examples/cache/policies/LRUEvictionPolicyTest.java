package com.mylearning.design.solid.examples.cache.policies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class LRUEvictionPolicyTest {
    private LRUEvictionPolicy<Integer> lruEvictionPolicy;

    @Before
    public void setUp() {
        lruEvictionPolicy = new LRUEvictionPolicy<>();
    }

    @Test
    public void testNoKeyToEvictInitially() {
        assertNull(lruEvictionPolicy.evictKey());
    }

    @Test
    public void testKeysAreEvictedInTheOrderInWhichTheyAreAccessedAccess() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(4);
        assertEquals(1, lruEvictionPolicy.evictKey().intValue());
        assertEquals(2, lruEvictionPolicy.evictKey().intValue());
        assertEquals(3, lruEvictionPolicy.evictKey().intValue());
        assertEquals(4, lruEvictionPolicy.evictKey().intValue());
    }

    @Test
    public void testReaccesingKeyPreventsItFromEviction() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(4);
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(5);
        assertEquals(3, lruEvictionPolicy.evictKey().intValue());
        assertEquals(2, lruEvictionPolicy.evictKey().intValue());
        assertEquals(4, lruEvictionPolicy.evictKey().intValue());
        assertEquals(1, lruEvictionPolicy.evictKey().intValue());
        assertEquals(5, lruEvictionPolicy.evictKey().intValue());
    }
}