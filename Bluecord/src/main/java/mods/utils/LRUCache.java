package mods.utils;

import java.util.LinkedHashMap;
import java.util.Map;
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int i) {
        super(i + 1, 1.0f, true);
        this.capacity = i;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Entry<K, V> entry) {
        return size() >= this.capacity;
    }
}
