package org.example.implementations;

import java.util.NoSuchElementException;
import java.util.Queue;

public class MyLinkedQueue<E> extends AbstractLinkedCollection<E> implements Queue<E> {

    @Override
    public E peek() {
        return start == null ? null : start.item;
    }

    @Override
    public E poll() {
        return removeFirst();
    }


    @Override
    public E remove() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        return removeFirst();
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E element() {
        if (start == null) throw new NoSuchElementException();
        return start.item;
    }
}
