package net.spookyless.util;

import java.util.ArrayList;
import java.util.List;

public class MyMap<K, V> implements Map<K, V> {
    private final ArrayList<K> keys = new ArrayList<>();
    private final ArrayList<V> values = new ArrayList<>();

    @Override
    public boolean put(K key, V value) {
        int index = keys.indexOf(key);

        if(key == null || value == null) {
            return false;
        }

        if(index == -1) {
            keys.add(key);
            values.add(value);
        } else {
            values.add(index, value);
        }

        return true;
    }

    @Override
    public boolean remove(K key) {
        int index = keys.indexOf(key);

        if(index == -1) {
            return false;
        }

        keys.remove(index);
        values.remove(index);
        return true;
    }

    @Override
    public V get(K key) {
        int index = keys.indexOf(key);

        if(index > -1) {
            return values.get(index);
        } else {
            return null;
        }
    }

    @Override
    public List<K> keys() {
        return (List<K>) keys.clone();
    }

    @Override
    public boolean contains(K key) {
        return keys.contains(key);
    }
}
