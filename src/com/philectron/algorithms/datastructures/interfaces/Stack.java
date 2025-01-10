package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.EmptyStackException;

public interface Stack<E> extends Iterable<E> {

    /**
     * Retrieves the size of this stack.
     *
     * @return the number of elements in this stack
     */
    int size();

    /**
     * Checks if this stack is empty.
     *
     * @return {@code true} if this stack has no elements (when {@link #size()} is zero), or
     *         {@code false} otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts {@code element} to the top of this stack.
     *
     * @param element the element to be inserted
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    void push(E element);

    /**
     * Pushes all elements from {@code iterable} to this stack.
     *
     * @param iterable the {@link Iterable} containing the elements to be pushed
     *
     * @return {@code true} if any element of {@code iterable} was pushed to this stack, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    default boolean pushAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            push(element);
            modified = true;
        }
        return modified;
    }

    /**
     * Retrieves and removes the element at the top of this stack.
     *
     * @return the element previously at the top of this stack
     *
     * @throws EmptyStackException if this stack {@link #isEmpty()}
     *
     * @see #peek()
     */
    E pop();

    /**
     * Retrieves, but does not remove, the element at the top of this stack, if any.
     *
     * @return the element currently at the top of this stack, or {@code null} if this stack
     *         {@link #isEmpty()}
     *
     * @see #pop()
     */
    E peek();

    /**
     * Removes all elements from this stack. The stack will be empty after this call.
     */
    default void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

}
