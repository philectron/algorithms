package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

public interface OrderedSet<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Retrieves the size of this list.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Checks if this list is empty.
     *
     * @return {@code true} if there are no elements in this list (when {@link #size()} is zero), or
     *         {@code false} otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieves an element at index {@code index} of this list.
     *
     * @param index the index of the element to return
     *
     * @return the element at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
     *         {@link #size()}
     */
    E get(int index);

    /**
     * Inserts {@code element} to its correct position in this list. Shifts the current element at
     * that index (if any) and any subsequent elements to the right (adds 1 to their indices).
     *
     * @param element the element to be inserted to this list
     *
     * @return {@code true} if this list has been modified as a result of the insertion, or
     *         {@code false} if {@code element} already exists in this list
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    boolean add(E element);

    /**
     * Inserts all elements from {@code iterable} to their correct positions in this list.
     *
     * @param iterable the {@link Iterable} containing the elements to be inserted
     *
     * @return {@code true} if this list has been modified as a result of the insertions, or
     *         {@code false} if all elements of {@code iterable} already exist in this list
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
     * Finds the first occurrence of {@code element} in this list.
     *
     * @param element the element to be searched in this list
     *
     * @return the index of the first occurrence of {@code element}, or {@code -1} if this list does
     *         not contain {@code element}
     *
     * @throws NullPointerException if {@code element} is {@code null}
     */
    int indexOf(E element);

    /**
     * Checks if this list contains {@code element}.
     *
     * @param element the element to be searched in this list
     *
     * @return {@code true} if this list contains {@code element}, or {@code false} otherwise
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #indexOf(E)
     */
    default boolean contains(E element) {
        return indexOf(checkNotNull(element)) >= 0;
    }

    /**
     * Removes the element at index {@code index} of this list. Shifts any subsequent elements to
     * the left (subtracts 1 from their indices).
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
     * Removes the element at the front of this list. Shifts any subsequent elements to the left
     * (subtracts 1 from their indices).
     *
     * @return the element previously at the front of this list
     *
     * @throws IndexOutOfBoundsException if the list is empty
     *
     * @see #remove(int)
     */
    default E removeFront() {
        return remove(0);
    }

    /**
     * Removes the element at the back of this list.
     *
     * @return the element previously at the back of this list
     *
     * @throws IndexOutOfBoundsException if the list is empty
     *
     * @see #remove(int)
     */
    default E removeBack() {
        return remove(size() - 1);
    }

    /**
     * Removes {@code element}, if it exists, from this list. Shifts any subsequent elements to the
     * left (subtracts 1 from their indices).
     *
     * @param element the element to be removed from this list, if it exists
     *
     * @return {@code true} if this list has been modified as a result of the removal, or
     *         {@code false} if {@code element} does not exist in this list
     *
     * @throws NullPointerException if {@code element} is {@code null}
     *
     * @see #remove(int)
     */
    boolean remove(E element);

    /**
     * Removes all elements from this list. The list will be empty after this operation finishes.
     */
    void clear();

}
