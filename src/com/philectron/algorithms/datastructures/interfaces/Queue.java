package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;

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
     * @throws NullPointerException if {@code element} is {@code null}
     */
    void enqueue(E element);

    /**
     * Enqueues all elements from {@code iterable} to this queue.
     *
     * @param iterable the {@link Iterable} containing the elements to be enqueued
     *
     * @return {@code true} if any element of {@code iterable} was enqueued to this queue, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    default boolean enqueueAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            enqueue(element);
            modified = true;
        }
        return modified;
    }

    /**
     * Retrieves and removes the element at the front of this queue.
     *
     * @return the element previously at the front of this queue, or {@link Optional#empty()} if
     *         this queue {@link #isEmpty()}
     *
     * @see #front()
     */
    Optional<E> dequeue();

    /**
     * Retrieves, but does not remove, the element at the front of this queue, if any.
     *
     * @return the element currently at the front of this queue, or {@link Optional#empty()} if this
     *         queue {@link #isEmpty()}
     *
     * @see #dequeue()
     * @see #rear()
     */
    Optional<E> front();

    /**
     * Retrieves, but does not remove, the element at the rear of this queue, if any.
     *
     * @return the element currently at the rear of this queue, or {@link Optional#empty()} if this
     *         queue {@link #isEmpty()}
     *
     * @see #front()
     */
    Optional<E> rear();

    /**
     * Removes all elements from this queue. The queue will be empty after this call.
     */
    default void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

}
