package com.mylearning.design.solid.examples.cache;

import static org.junit.Assert.assertEquals;

import com.mylearning.design.solid.examples.cache.factories.CacheFactory;
import org.junit.Before;
import org.junit.Test;

public class CacheTest {

    Cache<Integer, Integer> cache;

    @Before
    public void setup() {
        cache = new CacheFactory<Integer, Integer>().defaultCache(3);
    }

    @Test
    public void itShouldBeAbleToGetAndAddItemsInTheCache() {
        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1).intValue()); // Accessing 1 after 2 got inserted which makes 2 the least recently used till now.
        cache.put(3, 3);
        assertEquals(3, cache.get(3).intValue());

        // Now if i try to add any element, the eviction should happen
        // Also eviction should happen based on LeastRecentlyUsedItem
        // which is 2 in this case.
        cache.put(4, 4);

        cache.get(2); // This should throw exception "Tried to access non-existing key."
    }
}
