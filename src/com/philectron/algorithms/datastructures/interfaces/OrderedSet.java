package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

public interface OrderedSet<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Retrieves the size of this set.
     *
     * @return the number of elements in this set
     */
    int size();

    /**
     * Checks if this set is empty.
     *
     * @return {@code true} if this set has no elements (when {@link #size()} is zero), else
     *         {@code false}
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieves the first (smallest) element in this set.
     *
     * @return the first (smallest) element in this set
     *
     * @throws NoSuchElementException if this set {@link #isEmpty()}
     *
     * @see #getLast()
     */
    E getFirst();

    /**
     * Retrieves the last (largest) element in this set.
     *
     * @return the last (largest) element in this set
     *
     * @throws NoSuchElementException if this set {@link #isEmpty()}
     *
     * @see #getFirst()
     */
    E getLast();

    /**
     * Inserts {@code element} at its correct position in this set.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if {@code element} was inserted into this set, else {@code false}
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    boolean add(E element);

    /**
     * Inserts all elements from {@code iterable} at their correct positions in this set.
     *
     * @param iterable the {@link Iterable} containing the elements to be inserted
     *
     * @return {@code true} if this set changed as a result of this call, else {@code false}
     *
     * @throws NullPointerException if {@code iterable} is {@code null} or any of its elements is
     *         {@code null}
     */
    default boolean addAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Checks if this set contains {@code element}.
     *
     * @param element the element to be searched in this set
     *
     * @return {@code true} if this set contains {@code element}, else {@code false}
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    boolean contains(E element);

    /**
     * Removes {@code element}, if it exists, from this set.
     *
     * @param element the element to be removed if exists
     *
     * @return {@code true} if {@code element} was removed from this set, else {@code false}
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    boolean remove(E element);

    /**
     * Removes from this set all elements that are contained in {@code iterable}.
     *
     * @param iterable the {@link Iterable} containing the elements to be removed from this set
     *
     * @return {@code true} if this set changed as a result of this call, else {@code false}
     *
     * @throws NullPointerException if {@code iterable} is {@code null} or any of its elements is
     *         {@code null}
     */
    default boolean removeAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        // For each distinct element of the iterable, remove it from this set, then return the final
        // result as true if any of the removals modified the list.
        return StreamSupport.stream(iterable.spliterator(), false)
                .distinct()
                .map(this::remove)
                .reduce(Boolean::logicalOr)
                .orElse(false);
    }

    /**
     * Removes all elements from this set. The set will be empty after this call.
     */
    void clear();

}
