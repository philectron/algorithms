package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

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
     * @return {@code true} if there are no elements in this set (when {@link #size()} is zero), or
     *         {@code false} otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts {@code element} into its correct position in this set.
     *
     * @param element the element to be inserted into this set
     *
     * @return {@code true} if this set has been modified as a result of the insertion, or
     *         {@code false} if {@code element} already exists in this set
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
     *         {@code false} if all elements of {@code iterable} already exist in this set
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
     * Removes the element at index {@code index} of this set. Shifts any subsequent elements to the
     * left (subtracts 1 from their indices).
     *
     * @param index the index of the element to be removed
     *
     * @return the element previously at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
     *         {@link #size()}
     *
     * @see #remove(E)
     */
    E remove(int index);

    /**
     * Removes the element at the front of this set. Shifts any subsequent elements to the left
     * (subtracts 1 from their indices).
     *
     * @return the element previously at the front of this set
     *
     * @throws IndexOutOfBoundsException if the set is empty
     *
     * @see #remove(int)
     */
    default E removeFront() {
        return remove(0);
    }

    /**
     * Removes the element at the back of this set.
     *
     * @return the element previously at the back of this set
     *
     * @throws IndexOutOfBoundsException if the set is empty
     *
     * @see #remove(int)
     */
    default E removeBack() {
        return remove(size() - 1);
    }

    /**
     * Removes {@code element}, if it exists, from this set. Shifts any subsequent elements to the
     * left (subtracts 1 from their indices).
     *
     * @param element the element to be removed from this set, if it exists
     *
     * @return {@code true} if this set has been modified as a result of the removal, or
     *         {@code false} if {@code element} does not exist in this set
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #remove(int)
     */
    boolean remove(E element);

    /**
     * Removes all elements from this set. The set will be empty after this operation finishes.
     */
    void clear();

}
