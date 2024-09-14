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
     * @return {@code true} if this set has no elements (when {@link #size()} is zero), or
     *         {@code false} otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieves the first (smallest) in this set.
     *
     * @return the first (smallest) element in this set
     *
     * @throws NoSuchElementException if this set is empty
     *
     * @see #getLast()
     */
    E getFirst();

    /**
     * Retrieves the last (largest) in this set.
     *
     * @return the last (largest) element in this set
     *
     * @throws NoSuchElementException if this set is empty
     *
     * @see #getFirst()
     */
    E getLast();

    /**
     * Inserts {@code element} into its correct position in this set.
     *
     * @param element the element to be inserted
     *
     * @return {@code true} if this set is modified as a result of the insertion, or {@code false}
     *         otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    boolean add(E element);

    /**
     * Inserts all elements from {@code iterable} into their correct positions in this set.
     *
     * @param iterable the {@link Iterable} containing the elements to be inserted
     *
     * @return {@code true} if this set has been modified as a result of the insertions, or
     *         {@code false} otherwise
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
     * @return {@code true} if this set contains {@code element}, or {@code false} otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    boolean contains(E element);

    /**
     * Removes {@code element}, if it exists, from this set.
     *
     * @param element the element to be removed if exists
     *
     * @return {@code true} if this set is modified as a result of the removal, or {@code false}
     *         otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    boolean remove(E element);

    /**
     * Removes from this set all elements that are contained in {@code iterable}.
     *
     * @param iterable the {@link Iterable} containing the elements to be removed from this set
     *
     * @return {@code true} if this set is modified as a result of the removals, or {@code false}
     *         otherwise
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
                .map(element -> remove(element))
                .reduce(Boolean::logicalOr)
                .orElse(false);
    }

    /**
     * Removes all elements from this set. The set will be empty after this operation finishes.
     */
    void clear();

}
