package com.philectron.algorithms.datastructures.list;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;

public interface List<E> extends Iterable<E> {

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
     * Replaces the element at index {@code index} of this list with {@code element}.
     *
     * @param index the index of the element to replace
     * @param element the new element to be stored at {@code index}
     *
     * @return the element previously at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
     *         {@link #size()}
     */
    E set(int index, E element);

    /**
     * Inserts {@code element} to index {@code position} of this list. Shifts the current element at
     * that index (if any) and any subsequent elements to the right (adds 1 to their indices).
     *
     * @param position the position index at which the new element is to be inserted
     * @param element the element to be inserted at {@code position}
     *
     * @throws IndexOutOfBoundsException if {@code position} is negative or is greater than
     *         {@link #size()}
     */
    void add(int position, E element);

    /**
     * Inserts {@code element} to the front of this list. Shifts the current element at the front
     * (if any) and any subsequent elements to the right (adds 1 to their indices).
     *
     * @param element the element to be inserted
     *
     * @see #add(int, E)
     */
    default void addFront(E element) {
        add(0, element);
    }

    /**
     * Inserts {@code element} to the back of this list.
     *
     * @param element the element to be inserted
     *
     * @see #add(int, E)
     */
    default void addBack(E element) {
        add(size(), element);
    }

    /**
     * Inserts all elements from {@code iterable} to the back of this list.
     *
     * @param iterable the {@link Iterable} containing the elements to be inserted
     *
     * @throws NullPointerException if {@code iterable} is {@code null}
     */
    default void addAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        iterable.forEach(this::addBack);
    }

    /**
     * Finds the first occurrence of {@code element} in this list.
     *
     * @param element the element to be searched in this list
     *
     * @return the index of the first occurrence of {@code element}, or {@code -1} if this list does
     *         not contain {@code element}
     */
    int indexOf(E element);

    /**
     * Finds the last (final) occurrence of {@code element} in this list.
     *
     * @param element the element to be searched in this list
     *
     * @return the index of the last (final) occurrence of {@code element}, or {@code -1} if this
     *         list does not contain {@code element}
     */
    int lastIndexOf(E element);

    /**
     * Checks if this list contains {@code element}.
     *
     * @param element the element to be searched in this list
     *
     * @return {@code true} if this list contains {@code element}, or {@code false} otherwise
     *
     * @see #indexOf(E)
     * @see #lastIndexOf(E)
     */
    default boolean contains(E element) {
        return indexOf(element) >= 0;
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
     * Removes all elements from this list. The list will be empty after this operation finishes.
     */
    void clear();

    /**
     * Reverses this list's order of elements.
     */
    void reverse();

    /**
     * Copies all elements of this list to a new {@link java.util.List}.
     *
     * @return a new {@link java.util.ArrayList} containing the elements of this list
     */
    default java.util.List<E> toJavaList() {
        java.util.List<E> list = new java.util.ArrayList<E>();
        this.forEach(list::add);
        return list;
    }

    /**
     * Provides an {@link Iterator} in the reverse order of the normal {@link #iterator()}. This
     * reverse iterator starts at the last (final) element of this list, and calling its
     * {@code next()} will traverse this list backward.
     *
     * @return an iterator over the elements of this list in the reverse order
     */
    Iterator<E> reverseIterator();

}
