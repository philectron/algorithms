package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.NoSuchElementException;

public interface Deque<E> extends Iterable<E> {

    /**
     * Retrieves the size of this deque.
     *
     * @return the number of elements in this deque
     */
    int size();

    /**
     * Checks if this deque is empty.
     *
     * @return {@code true} if this deque has no elements (when {@link #size()} is zero), or
     *         {@code false} otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts {@code element} to the front of this deque.
     *
     * @param element the element to be inserted
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    void pushFront(E element);

    /**
     * Inserts {@code element} to the rear of this deque.
     *
     * @param element the element to be inserted
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    void pushRear(E element);

    /**
     * Inserts all elements from {@code iterable} to the front of this deque.
     *
     * @param iterable the {@link Iterable} containing the elements to be inserted
     *
     * @return {@code true} if any element of {@code iterable} was inserted to this deque, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    default boolean pushFrontAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            pushFront(element);
            modified = true;
        }
        return modified;
    }

    /**
     * Inserts all elements from {@code iterable} to the rear of this deque.
     *
     * @param iterable the {@link Iterable} containing the elements to be inserted
     *
     * @return {@code true} if any element of {@code iterable} was inserted to this deque, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    default boolean pushRearAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            pushRear(element);
            modified = true;
        }
        return modified;
    }

    /**
     * Retrieves and removes the element at the front of this deque.
     *
     * @return the element previously at the front of this deque
     *
     * @throws NoSuchElementException if this deque {@link #isEmpty()}
     */
    E popFront();

    /**
     * Retrieves and removes the element at the rear of this deque.
     *
     * @return the element previously at the rear of this deque
     *
     * @throws NoSuchElementException if this deque {@link #isEmpty()}
     */
    E popRear();

    /**
     * Retrieves, but does not remove, the element at the front of this deque.
     *
     * @return the element currently at the front of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     */
    E peekFront();

    /**
     * Retrieves, but does not remove, the element at the rear of this deque.
     *
     * @return the element currently at the rear of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     */
    E peekRear();

    /**
     * Removes all elements from this deque. The deque will be empty after this call.
     */
    default void clear() {
        while (!isEmpty()) {
            popFront();
        }
    }

}
