package org.example.implementations;

import org.example.interfaces.DemoCollection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Contrary to LinkedList, collections that are derived from this class are singly linked lists
 *
 * @param <E> Object type to store in the collection
 */
public abstract class AbstractLinkedCollection<E> implements DemoCollection<E> {
    Node<E> start;
    Node<E> end;
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        for (Node<E> n = start; n != null; n = n.next) {
            if (Objects.equals(n.item, o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> node = start;
            Node<E> prev = start;
            Node<E> prevPrev = start;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (node == null) throw new NoSuchElementException();
                prevPrev = prev;
                prev = node;
                node = node.next;
                return prev.item;
            }

            @Override
            public void remove() {
                if (node == prev) return;
                if (prev == start) removeFirst();
                else prevPrev.next = prev.next;
                prev = prevPrev;
            }
        };
    }

    @Override
    public boolean add(E e) {
        if (start == null) {
            end = start = new Node<>(null, e);
        } else {
            end.next = new Node<>(null, e);
            end = end.next;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> prev = null;
        for (Node<E> n = start; n != null; n = n.next) {
            if (Objects.equals(n.item, o)) {
                if (prev != null) prev.next = n.next;
                else start = n.next;
                size--;
                return true;
            }
            prev = n;
        }
        return false;
    }

    /**
     * Removes the first element of collection.
     *
     * @return item that was removed (head of the list)
     */
    protected E removeFirst() {
        if (start == null) return null;
        if (end == start) end = null;
        E item = start.item;
        start = start.next;
        size--;
        return item;
    }

    @Override
    public void clear() {
        start = null;
        size = 0;
    }

    @Override
    public Object[] toArray() {
        if (size == 0) return new Object[0];
        var result = new Object[size];
        Node<E> n = start;
        for (int i = 0; i < result.length; i++) {
            result[i] = n.item;
            n = n.next;
        }
        return result;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (var n = start; n != null; n = n.next)
            sj.add(n.item.toString());
        return sj.toString();
    }


    /**
     * Node class that allows doubly-linked connection for the list.
     * Contains the element itself and links to previous and next nodes.
     *
     * @param <E> the type of elements held in the list
     */
    protected static class Node<E> {
        protected final E item;
        protected Node<E> next;

        public Node(Node<E> next, E item) {
            this.next = next;
            this.item = item;
        }
    }
}
