package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

public interface Queue<E> extends Iterable<E> {

    /**
     * Retrieves the size of this queue.
     *
     * @return the number of elements in this queue
     */
    int size();

    /**
     * Checks if this queue is empty.
     *
     * @return {@code true} if this queue has no elements (when {@link #size()} is zero), or
     *         {@code false} otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts {@code element} to the rear of this queue.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if {@code element} was pushed to this queue, or {@code false} otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    boolean push(E element);

    /**
     * Pushes all elements from {@code iterable} to this queue.
     *
     * @param iterable the {@link Iterable} containing the elements to be pushed
     *
     * @return {@code true} if any element of {@code iterable} was pushed to this queue, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    default boolean pushAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            modified = push(element) || modified;
        }
        return modified;
    }

    /**
     * Retrieves and removes the element at the front of this queue, if any.
     *
     * @return the element previously at the front of this queue, or {@code null} if this queue
     *         {@link #isEmpty()}
     */
    E pop();

    /**
     * Retrieves, but does not remove, the element at the front of this queue, if any.
     *
     * @return the element currently at the front of this queue, or {@code null} if this queue
     *         {@link #isEmpty()}
     */
    E peek();

    /**
     * Removes all elements from this queue. The queue will be empty after this call.
     */
    default void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

}
