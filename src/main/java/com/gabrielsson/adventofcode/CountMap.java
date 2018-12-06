package com.gabrielsson.adventofcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountMap<K> extends HashMap<K, Integer> {

    public int increment(K key, int invVal) {
        int value = getOrDefault(key, 0);
        int newValue = value + invVal;
        put(key, newValue);
        return newValue;
    }
    public int increment(K key) {
        return increment(key, 1);
    }

    public int decrement(K key) {
        return increment(key, -1);
    }

    public K getMaxKey() {
        int max = 0;
        K maxKey = null;
        for (Map.Entry<K, Integer> entry : entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public K getMinKey() {
        int min = Integer.MAX_VALUE;
        K minKey = null;
        for (Map.Entry<K, Integer> entry : entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                minKey = entry.getKey();
            }
        }
        return minKey;
    }

    public Set<K> getKeysAboveOrEquals(int limit) {
        Set<K> set = new HashSet<>();
        for (Map.Entry<K, Integer> entry : entrySet()) {
            if (entry.getValue() >= limit) {
                set.add(entry.getKey());
            }
        }
        return set;
    }

    public Set<K> getKeysEqual(int value) {
        Set<K> set = new HashSet<>();
        for (Map.Entry<K, Integer> entry : entrySet()) {
            if (entry.getValue() == value) {
                set.add(entry.getKey());
            }
        }
        return set;
    }

}