package net.spookyless.util;

import java.util.List;

public interface Map<K, V> {
    /**
     * Assigns a value to a specified key. If the key already had any assignments, the old value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return {@code true} if the assignment was successful, {@code false} otherwise
     */
    boolean put(K key, V value);

    /**
     * Removes the key and the associated value from the map.
     *
     * @param key key to remove alongside its value
     * @return {@code true} if the key got deleted, {@code false} otherwise
     */
    boolean remove(K key);

    /**
     * Returns the value associated with given key or {@code null} if no such mapping exists.
     *
     * @param key key to query
     * @return value associated with the key or {@code null}
     */

    V get(K key);
    /**
     * Returns a list of keys which exist in the map.
     *
     * @return {@code java.util.List} list of keys
     */
    List<K> keys();

    /**
     * Checks whether the map contains a given key.
     *
     * @param key key to query
     * @return {@code true} if the key exists, {@code false} otherwise
     */
    boolean contains(K key);
}
