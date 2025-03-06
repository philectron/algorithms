package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

public interface Deque<E> extends Queue<E> {

    /**
     * Inserts {@code element} to the rear of this deque.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if {@code element} was pushed to this deque, or {@code false} otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #pushFront(E)
     * @see #pushRear(E)
     */
    @Override
    default boolean push(E element) {
        return pushRear(element);
    }

    /**
     * Inserts {@code element} to the front of this deque.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if {@code element} was pushed to this deque, or {@code false} otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #push(E)
     * @see #pushRear(E)
     */
    boolean pushFront(E element);

    /**
     * Inserts {@code element} to the rear of this deque.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if {@code element} was pushed to this deque, or {@code false} otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #push(E)
     * @see #pushFront(E)
     */
    boolean pushRear(E element);

    /**
     * Pushes all elements from {@code iterable} to the front of this deque. This results in
     * reversing the order of {@code iterable}'s elements in this deque.
     *
     * @param iterable the {@link Iterable} containing the elements to be pushed
     *
     * @return {@code true} if any element of {@code iterable} was pushed to this deque, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     *
     * @see Queue#pushAll(Iterable)
     * @see #pushRearAll(Iterable)
     */
    default boolean pushFrontAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            modified = pushFront(element) || modified;
        }
        return modified;
    }

    /**
     * Pushes all elements from {@code iterable} to the rear of this deque.
     *
     * @param iterable the {@link Iterable} containing the elements to be pushed
     *
     * @return {@code true} if any element of {@code iterable} was pushed to this deque, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     *
     * @see #pushAll(Iterable)
     * @see #pushFrontAll(Iterable)
     */
    default boolean pushRearAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            modified = pushRear(element) || modified;
        }
        return modified;
    }

    /**
     * Retrieves and removes the element at the front of this deque, if any.
     *
     * @return the element previously at the front of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #popFront()
     * @see #popRear()
     */
    @Override
    default E pop() {
        return popFront();
    }

    /**
     * Retrieves and removes the element at the front of this deque, if any.
     *
     * @return the element previously at the front of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #pop()
     * @see #popRear()
     */
    E popFront();

    /**
     * Retrieves and removes the element at the rear of this deque.
     *
     * @return the element previously at the rear of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #pop()
     * @see #popFront()
     */
    E popRear();

    /**
     * Retrieves, but does not remove, the element at the front of this deque, if any.
     *
     * @return the element currently at the front of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #peekFront()
     * @see #peekRear()
     */
    @Override
    default E peek() {
        return peekFront();
    }

    /**
     * Retrieves, but does not remove, the element at the front of this deque.
     *
     * @return the element currently at the front of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #peek()
     * @see #peekRear()
     */
    E peekFront();

    /**
     * Retrieves, but does not remove, the element at the rear of this deque.
     *
     * @return the element currently at the rear of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #peek()
     * @see #peekFront()
     */
    E peekRear();

}
