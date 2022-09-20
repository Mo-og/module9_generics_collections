package org.example.interfaces;

public interface Map<K, V> {
    /**
     * Adds a pair of key and value to the map
     *
     * @param key   Object by which the value will be retrieved later
     * @param value Value to store
     * @return Previous value that was stored by given key or null if the key is new for this map
     */
    V put(K key, V value);

    /**
     * Removes the pair of key and its value from the map
     *
     * @param key Object by which the value is accessed
     * @return Value that was stored by given key or null if there is nothing to remove
     */
    V remove(K key);

    /**
     * Removes all entries from the map
     */
    void clear();

    /**
     * @return Count of entries stored
     */
    int size();

    /**
     * Gives the value stored behind provided key without removing it
     *
     * @param key Object to which the value is bound
     * @return Value that was stored by given key or null if key is not present
     */
    V get(K key);
}
