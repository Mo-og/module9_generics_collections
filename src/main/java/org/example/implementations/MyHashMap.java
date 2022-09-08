package org.example.implementations;

import java.util.*;

public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 1.1;
    private Node<K, V>[] table;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        capacity = DEFAULT_CAPACITY;
        table = new Node[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        if (capacity < 1) capacity = 1;
        this.capacity = capacity;
        table = new Node[this.capacity];
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (Node<K, V> node : table) {
            if (node == null) continue;
            while (node != null) {
                set.add(node);
                node = node.next;
            }
        }
        return set;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        int index = key == null ? 0 : key.hashCode() % capacity;
        if (table[index] == null) return null;
        Node<K, V> toRemoveNode = new Node<>((K) key, null);
        Node<K, V> n = table[index];
        Node<K, V> prev = n;
        V toReturn = null;
        while (n != null) {
            if (n.fastEquals(toRemoveNode)) {
                toReturn = n.value;
                if (prev == n) table[index] = n.next;
                else prev.next = n.next;
                size--;
                break;
            }
            prev = n;
            n = n.next;
        }

        return toReturn;
    }

    private int getIndex(K key) {
        return key == null ? 0 : key.hashCode() % capacity;
    }

    @Override
    public V put(K key, V value) {
        if (size + 1 > capacity * DEFAULT_LOAD_FACTOR) expand();
        Node<K, V> newNode = new Node<>(key, value);
        int index = getIndex(newNode.key);
        if (table[index] == null) {
            table[index] = newNode;
            size++;
            return null;
        }
        Node<K, V> n = table[index];
        V toReturn = null;
        Node<K, V> prev = n;
        boolean added = false;
        while (n != null) {
            if (n.fastEquals(newNode)) {
                toReturn = n.value;
                n.value = newNode.value;
                added = true;
                break;
            }
            prev = n;
            n = n.next;
        }
        if (!added) {
            prev.next = newNode;
            size++;
        }
        return toReturn;
    }


    private void fastAdd(Node<K, V> node) {
        int index = getIndex(node.key);
        if (table[index] == null) {
            table[index] = node;
            return;
        }
        var n = table[index];
        while (n.next != null) {
            n = n.next;
        }
        n.next = node;
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        var old = entrySet();
        capacity *= 2;
        table = new Node[capacity];
        for (Entry<K, V> node : old) {
            ((Node<K, V>) node).next = null;
            fastAdd((Node<K, V>) node);
        }
    }

    /**
     * Shrinks the table to current {@code size * DEFAULT_LOAD_FACTOR} or default if size less than 16.
     * Ignored if capacity <= DEFAULT_CAPACITY or capacity would increase after operation.
     * An application can use this operation to minimize the storage of an instance.
     */
    @SuppressWarnings("unchecked")
    public void shrink() {
        if (capacity <= DEFAULT_CAPACITY || size * DEFAULT_LOAD_FACTOR > capacity) return;
        var old = entrySet();
        if (size < 16) capacity = DEFAULT_CAPACITY;
        else capacity = (int) (size * DEFAULT_LOAD_FACTOR);
        table = new Node[capacity];
        for (Entry<K, V> node : old) {
            ((Node<K, V>) node).next = null;
            fastAdd((Node<K, V>) node);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    /**
     * Gives a {@code key=value} representation of where are nodes stored in actual array
     *
     * @return string representation of the array where nodes are stored (only first level of nodes)
     */
    public String toArrayString() {
        return Arrays.toString(table);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Node<K, V> node : table) {
            if (node == null) continue;
            while (node != null) {
                sj.add(node.toString());
                node = node.next;
            }
        }
        return sj.toString();
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.hash = key == null ? 0 : key.hashCode();
            this.key = key;
            this.value = value;
        }

        private boolean fastEquals(Node<K, V> other) {
            if (hash != other.hash) return false;
            return Objects.equals(key, other.key);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        //if brackets are like [] node has a link to next node
        //to see 'buckets' in toArrayString()
        @Override
        public String toString() {
            if (next != null)
                return "[" + key + "=" + value + "]";
            return "{" + key + "=" + value + "}";

        }
    }
}
