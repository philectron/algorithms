package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

public interface Deque<E> extends Queue<E> {

    /**
     * Inserts {@code element} at the rear of this deque.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if {@code element} was inserted into this deque, or {@code false}
     *         otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #offerFront(E)
     * @see #offerRear(E)
     */
    @Override
    default boolean offer(E element) {
        return offerRear(element);
    }

    /**
     * Inserts {@code element} at the front of this deque.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if {@code element} was inserted into this deque, or {@code false}
     *         otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #offer(E)
     * @see #offerRear(E)
     */
    boolean offerFront(E element);

    /**
     * Inserts {@code element} at the rear of this deque.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if {@code element} was inserted into this deque, or {@code false}
     *         otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #offer(E)
     * @see #offerFront(E)
     */
    boolean offerRear(E element);

    /**
     * Pushes {@code element} onto the stack represented by this deque. This operation inserts
     * {@code element} at the front of this deque (treated as the top of the represented stack).
     *
     * @param element the element to be pushed
     *
     * @return {@code true} if {@code element} was pushed onto the stack represented by this deque,
     *         or {@code false} otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #offerFront(E)
     */
    default boolean push(E element) {
        return offerFront(element);
    }

    /**
     * Inserts all elements from {@code iterable} at the front of this deque. This results in
     * reversing the order of {@code iterable}'s elements in this deque.
     *
     * @param iterable the {@link Iterable} containing the elements to be inserted
     *
     * @return {@code true} if any element of {@code iterable} was inserted into this deque, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     *
     * @see Queue#offerAll(Iterable)
     * @see #offerRearAll(Iterable)
     */
    default boolean offerFrontAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            modified = offerFront(element) || modified;
        }
        return modified;
    }

    /**
     * Inserts all elements from {@code iterable} at the rear of this deque.
     *
     * @param iterable the {@link Iterable} containing the elements to be inserted
     *
     * @return {@code true} if any element of {@code iterable} was inserted into this deque, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     *
     * @see Queue#offerAll(Iterable)
     * @see #offerFrontAll(Iterable)
     */
    default boolean offerRearAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            modified = offerRear(element) || modified;
        }
        return modified;
    }

    /**
     * Retrieves and removes the element at the front of this deque, if any.
     *
     * @return the element previously at the front of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #pollFront()
     * @see #pollRear()
     */
    @Override
    default E poll() {
        return pollFront();
    }

    /**
     * Retrieves and removes the element at the front of this deque, if any.
     *
     * @return the element previously at the front of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #poll()
     * @see #pollRear()
     */
    E pollFront();

    /**
     * Retrieves and removes the element at the rear of this deque.
     *
     * @return the element previously at the rear of this deque, or {@code null} if this deque
     *         {@link #isEmpty()}
     *
     * @see #poll()
     * @see #pollFront()
     */
    E pollRear();

    /**
     * Pops the top element from the stack represented by this deque. This operation retrieves and
     * removes the element at the front of this deque (treated as the top of the represented stack).
     *
     * @return the element previously at the front of this deque (top of the stack), or {@code null}
     *         if this deque {@link #isEmpty()}
     */
    default E pop() {
        return pollFront();
    }

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
