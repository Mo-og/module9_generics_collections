package org.example.interfaces;

import java.util.Collection;

/**
 * Intermediate interface that allows avoiding of 'extra' methods implementation
 *
 * @param <E> Object type to store in the collection
 */
public interface DemoCollection<E> extends Collection<E> {
    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object o : c) if (!contains(o)) return false;
        return true;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        boolean successful = true;
        for (E e : c) if (!add(e)) successful = false;
        return successful;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        for (Object o : c) remove(o);
        return true;
    }

    @Override
    default <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
}
