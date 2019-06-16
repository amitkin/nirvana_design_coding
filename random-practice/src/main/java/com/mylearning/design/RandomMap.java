package com.mylearning.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMap {

    private static class Entry {
        int key;
        int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<Integer, Integer> keyToIndex = new HashMap<>();
    private final List<Entry> entries = new ArrayList<>();

    public void put(int key, int value) {
        if (keyToIndex.containsKey(key)) {
            Entry entry = entries.get(keyToIndex.get(key));
            entry.value = value;
        } else {
            //Important to note
            keyToIndex.put(key, entries.size());
            entries.add(new Entry(key, value));
        }
    }

    public Integer get(int key) {
        return keyToIndex.getOrDefault(key, null);
    }

    public Integer getRandom() {
        if (entries.isEmpty()) return null;

        //int at = rand.nextInt(entries.size());
        int at = ThreadLocalRandom.current().nextInt(entries.size()); //For concurrent usecase in multithreaded designs
        return entries.get(at).value;
    }

    public void remove(int key) {
        if (entries.isEmpty() || !keyToIndex.containsKey(key)) return;

        int curr = keyToIndex.get(key);
        int last = entries.size() - 1;

        if (curr != last) {
            Collections.swap(entries, curr, last);
            Entry entry = entries.get(curr);
            keyToIndex.replace(entry.key, curr);
        }

        entries.remove(last); // remove last element O(1)
        keyToIndex.remove(key);
    }

}
