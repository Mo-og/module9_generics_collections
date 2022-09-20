package org.example.implementations;

import org.example.interfaces.Stack;

public class MyLinkedStack<E> extends AbstractLinkedCollection<E> implements Stack<E> {

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

    @Override
    public boolean add(E item) {
        if (start == null) {
            end = start = new Node<>(null, item);
        } else {
            start = new Node<>(start, item);
        }
        size++;
        return true;
    }

    public E pop() {
        return removeFirst();
    }

    public E peek() {
        return start == null ? null : start.item;
    }
}
