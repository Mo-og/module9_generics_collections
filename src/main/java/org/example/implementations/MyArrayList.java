package org.example.implementations;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

public class MyArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess {
    private static final int DEFAULT_SIZE = 16;
    private Object[] items;
    private int size;
    private int endIndex = -1;

    public MyArrayList() {
        items = new Object[DEFAULT_SIZE];
    }

    public MyArrayList(int capacity) {
        items = new Object[capacity];
    }

    @Override
    public boolean add(E e) {
        ensureCapacity();
        items[++endIndex] = e;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        E temp = (E) items[index];
        moveLeft(index);
        return temp;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);
        endIndex = -1;
    }

    @Override
    public int size() {
        return endIndex + 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) items[index];
    }

    /**
     * Trims the capacity of this list to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an instance.
     */
    public void trimToSize() {
        if (endIndex < items.length) {
            if (endIndex == -1) items = new Object[DEFAULT_SIZE];
            else items = Arrays.copyOf(items, endIndex);
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size());
    }

    private void moveLeft(int towardsIndex) {
        if (endIndex - towardsIndex >= 0) {
            System.arraycopy(items, towardsIndex + 1, items, towardsIndex, endIndex - towardsIndex);
            --endIndex;
        }
    }

    private void ensureCapacity() {
        ensureCapacity(1);
    }

    private void ensureCapacity(int ext) {
        while (size <= endIndex + ext + 1) {
            size *= 1.5;
            size++;
        }
        items = Arrays.copyOf(items, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}