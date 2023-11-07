package net.spookyless.util;

import java.util.List;

public class MyMap<K, V> implements Map<K, V> {

    @Override
    public boolean put(K key, V value) {
        return false;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public List<K> keys() {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }
}
