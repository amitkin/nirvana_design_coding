package com.mylearning.design.caching;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {

    /*
    Algorithm
    1. the least recently+frequently used value to be removed is the first element in LinkedHashSet with the lowest count/frequency.
    2. min is used to track the group of elements with least frequency
    3. frequency to groups(LinkedHashSet) map, each element in same group has the same count.
    */
    private int min;

    private final int capacity;
    private final HashMap<Integer, Integer> keyToVal; //value map
    private final HashMap<Integer, Integer> keyToCount; //frequency map
    private final HashMap<Integer, LinkedHashSet<Integer>> countToLRUKeys; //LinkedHasSet maintains the insertion order

    public LFUCache(int capacity) {
        this.min = -1;
        this.capacity = capacity;
        this.keyToVal = new HashMap<>();
        this.keyToCount = new HashMap<>();
        this.countToLRUKeys = new HashMap<>();
    }

    public int get(int key) {
        //Steps:
        //1. This triggers update of keyToCount and countToLRUKeys map

        if (!keyToVal.containsKey(key)) return -1;

        int count = keyToCount.get(key);
        countToLRUKeys.get(count).remove(key); // remove key from current count (since we will inc count and add it at end)
        if (count == min && countToLRUKeys.get(count).size() == 0) min++; // nothing in the current min bucket

        updateCount(key, count + 1); //Updating frequency map and frequency to lru keys map
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        //Steps:
        //1. If key already exist then update the value in keyToVal and update the keyToCount and countToLRUKeys map
        //2. Check for eviction, if needed get lru key i.e. first in LinkedHashSet and remove this key in all three maps
        //3. Update the maps with new key with frequency/count as 1

        if (capacity <= 0) return;

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value); // update key's value
            get(key); // update key's count
            return;
        }

        //check before inserting new key, if we need to evict
        if (keyToVal.size() >= capacity) {
            int lruKey = countToLRUKeys.get(min).iterator().next();
            evict(lruKey); // evict LRU key from min count bucket
        }
        min = 1;
        updateCount(key, min); // Updating frequency map and frequency to lru keys map with new key
        keyToVal.put(key, value); // adding new key and value
    }

    private void evict(int key) {
        countToLRUKeys.get(min).remove(key);
        keyToVal.remove(key);
        keyToCount.remove(key);
    }

    private void updateCount(int key, int count) {
        keyToCount.put(key, count);
        countToLRUKeys.putIfAbsent(count, new LinkedHashSet<>());
        countToLRUKeys.get(count).add(key);
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(3);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.put(3, 3);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(2));
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(4));
    }
}
