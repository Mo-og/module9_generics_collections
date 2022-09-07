package org.example.implementations;

import org.example.interfaces.Stack;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class MyLinkedStack<E> extends MyLinkedList<E> implements Stack<E> {
    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * <blockquote><pre>
     * add(item)</pre></blockquote>
     *
     * @param item the item to be pushed onto this stack.
     */
    @Override
    public void push(E item) {
        add(item);
    }

    public E pop() {
        return removeLast();
    }

    public E peek() {
        return end == null ? null : end.item;
    }

    /**
     * Retrieves and removes the top element of this stack.  This method differs
     * from {@link #pop() pop()} only in that it throws an exception if
     * this stack is empty.
     *
     * @return the top element of this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public E remove() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        return removeLast();
    }


    /**
     * Removes the top item from this stack.
     *
     * @return item that was removed
     */
    protected E removeLast() {
        if (end == null) return null;
        Node<E> n = end;
        end = n.prev;
        if (end != null) end.next = null;
        size--;
        return n.item;
    }

    @Override
    public Object[] toArray() {
        if (size == 0) return new Object[0];
        var result = new Object[size];
        Node<E> n = end;
        for (int i = 0; i < result.length; i++) {
            result[i] = n.item;
            n = n.prev;
        }
        return result;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (var n = end; n != null; n = n.prev)
            sj.add(n.item.toString());
        return sj.toString();
    }
}
