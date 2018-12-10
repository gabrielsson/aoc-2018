package com.gabrielsson.adventofcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountMap<K> extends HashMap<K, Long> {

    public long increment(K key, long invVal) {
        long value = getOrDefault(key, 0l);
        long newValue = value + invVal;
        put(key, newValue);
        return newValue;
    }
    public long increment(K key) {
        return increment(key, 1);
    }

    public long decrement(K key) {
        return increment(key, -1);
    }

    public K getMaxKey() {
        long max = 0;
        K maxKey = null;
        for (Map.Entry<K, Long> entry : entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public K getMinKey() {
        long min = Long.MAX_VALUE;
        K minKey = null;
        for (Map.Entry<K, Long> entry : entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                minKey = entry.getKey();
            }
        }
        return minKey;
    }

    public Set<K> getKeysAboveOrEquals(long limit) {
        Set<K> set = new HashSet<>();
        for (Map.Entry<K, Long> entry : entrySet()) {
            if (entry.getValue() >= limit) {
                set.add(entry.getKey());
            }
        }
        return set;
    }

    public Set<K> getKeysEqual(int value) {
        Set<K> set = new HashSet<>();
        for (Map.Entry<K, Long> entry : entrySet()) {
            if (entry.getValue() == value) {
                set.add(entry.getKey());
            }
        }
        return set;
    }

}