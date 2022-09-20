package org.example.interfaces;

public interface Stack<E> extends DemoCollection<E> {

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param item the item to be pushed onto this stack.
     */
    void push(E item);

    /**
     * Removes the object at the top of this stack and returns it.
     *
     * @return The object at the top of this stack.
     */
    E pop();

    /**
     * Returns the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack.
     */
    E peek();

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in stack
     */
    int size();

    /**
     * Removes all the elements from this stack.
     * The collection will be empty after this method returns.
     */
    void clear();

    @Override
    default boolean add(E e) {
        push(e);
        return true;
    }
}
