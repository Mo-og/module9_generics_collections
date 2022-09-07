package org.example.implementations;

import java.util.AbstractList;
import java.util.List;
import java.util.StringJoiner;

public class MyLinkedList<E> extends AbstractList<E> implements List<E> {
    protected Node<E> start;
    protected Node<E> end;
    protected int size = 0;

    @Override
    public boolean add(E e) {
        if (start == null) {
            start = new Node<>(null, null, e);
            end = start;
        } else {
            end = new Node<>(end, null, e);
            end.prev.next = end;
        }
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        Node<E> node = getNode(index);
        if (node == null) return null;
        if (node == start) return removeFirst();
        node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
        size--;
        return node.item;
    }

    /**
     * Finds the Node of given index in the list.
     *
     * @param index actual index of Node starting from 0
     * @return Node of given index
     * @throws IndexOutOfBoundsException if index is negative or greater than nodes count - 1
     */
    protected Node<E> getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) return start;
        if (index == size - 1) return end;
        var n = start;
        while (index != 0) {
            n = n.next;
            index--;
        }
        return n;
    }

    @Override
    public void clear() {
        start = null;
        end = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        return getNode(index).item;
    }


    @Override
    public boolean remove(Object o) {
        var n = start;

        for (int i = 0; i < size && n != null; i++, n = n.next) {
            if (n.item.equals(o)) {
                if (n.prev != null) n.prev.next = n.next;
                if (n.next != null) n.next.prev = n.prev;
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the first element of actual list.
     *
     * @return item that was removed (head of the list)
     */
    protected E removeFirst() {
        if (start == null) return null;
        Node<E> n = start;
        start = n.next;
        if (start != null) start.prev = null;
        size--;
        return n.item;
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
        protected Node<E> prev;
        protected Node<E> next;

        public Node(Node<E> prev, Node<E> next, E item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }


}