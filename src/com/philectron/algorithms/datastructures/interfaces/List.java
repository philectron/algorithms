package com.philectron.algorithms.datastructures.interfaces;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.stream.StreamSupport;

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
     * @return {@code true} if this list has no elements (when {@link #size()} is zero), else
     *         {@code false}
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieves the element at index {@code index} of this list.
     *
     * @param index the index of the element to return
     *
     * @return the element at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
     *         {@link #size()}, or if this list {@link #isEmpty()}
     *
     * @see #getFirst()
     * @see #getLast()
     */
    E get(int index);

    /**
     * Retrieves the first element of this list.
     *
     * @return the element at the first index of this list
     *
     * @throws IndexOutOfBoundsException if this list is empty
     *
     * @see #get(int)
     * @see #getLast()
     */
    default E getFirst() {
        return get(0);
    }

    /**
     * Retrieves the last element of this list.
     *
     * @return the element at the last index of this list
     *
     * @throws IndexOutOfBoundsException if this list is empty
     *
     * @see #get(int)
     * @see #getFirst()
     */
    default E getLast() {
        return get(size() - 1);
    }

    /**
     * Replaces the element at index {@code index} of this list with {@code element}.
     *
     * @param index the index of the element to replace
     * @param element the new element to be stored at {@code index}
     *
     * @return the element previously at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
     *         {@link #size()}, or if this list {@link #isEmpty()}
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #setFirst(E)
     * @see #setLast(E)
     */
    E set(int index, E element);

    /**
     * Replaces the first element of this list with {@code element}.
     *
     * @param element the new element to be stored as the first element of this list
     *
     * @return the previous first element
     *
     * @throws IndexOutOfBoundsException if this list {@link #isEmpty()}
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #set(int, E)
     * @see #setLast(E)
     */
    default E setFirst(E element) {
        return set(0, element);
    }

    /**
     * Replaces the last element of this list with {@code element}.
     *
     * @param element the new element to be stored as the last element of this list
     *
     * @return the previous last element
     *
     * @throws IndexOutOfBoundsException if this list {@link #isEmpty()}
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #set(int, E)
     * @see #setFirst(E)
     */
    default E setLast(E element) {
        return set(size() - 1, element);
    }

    /**
     * Inserts {@code element} into this list at index {@code position}. Shifts the current element
     * at that index (if any) and any subsequent elements to the right (adds 1 to their indices).
     *
     * @param position the position index at which the new element is to be inserted
     * @param element the element to be inserted at {@code position}
     *
     * @throws IndexOutOfBoundsException if {@code position} is negative or is greater than
     *         {@link #size()}
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #add(E)
     * @see #addFirst(E)
     * @see #addLast(E)
     */
    void add(int position, E element);

    /**
     * Appends {@code element} to the end of this list.
     *
     * @param element the element to be appended
     *
     * @return always {@code true} to indicate {@code element} was appended to this list
     *
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #add(int, E)
     * @see #addFirst(E)
     * @see #addLast(E)
     */
    default boolean add(E element) {
        add(size(), element);
        return true;
    }

    /**
     * Inserts {@code element} as the first element of this list. Shifts the current first element
     * (if any) and any subsequent elements to the right (adds 1 to their indices).
     *
     * @param element the element to be inserted as the first element
     *
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #add(int, E)
     * @see #add(E)
     * @see #addLast(E)
     */
    default void addFirst(E element) {
        add(0, element);
    }

    /**
     * Inserts {@code element} as the last element of this list.
     *
     * @param element the element to be inserted as the last element
     *
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #add(int, E)
     * @see #add(E)
     * @see #addFirst(E)
     */
    default void addLast(E element) {
        add(size(), element);
    }

    /**
     * Appends all elements from {@code iterable} to the end of this list.
     *
     * @param iterable the {@link Iterable} containing the elements to be appended
     *
     * @return always {@code true} to indicate all elements of {@code iterable} was appended to this
     *         list
     *
     * @throws NullPointerException if {@code iterable} is {@code null}, or if any of the elements
     *         in {@code iterable} is {@code null} and this list does not permit null elements
     */
    default boolean addAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        boolean modified = false;
        for (E element : iterable) {
            add(element);
            modified = true;
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
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #lastIndexOf(E)
     * @see #contains(E)
     */
    int indexOf(E element);

    /**
     * Finds the last occurrence of {@code element} in this list.
     *
     * @param element the element to be searched in this list
     *
     * @return the index of the last occurrence of {@code element}, or {@code -1} if this list does
     *         not contain {@code element}
     *
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #indexOf(E)
     * @see #contains(E)
     */
    int lastIndexOf(E element);

    /**
     * Checks if this list contains {@code element}.
     *
     * @param element the element to be searched in this list
     *
     * @return {@code true} if this list contains {@code element}, else {@code false}
     *
     * @throws NullPointerException if {@code element} is {@code null} and this list does not permit
     *         null elements
     *
     * @see #indexOf(E)
     * @see #lastIndexOf(E)
     */
    default boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    /**
     * Removes the element at index {@code index} from this list. Shifts any subsequent elements to
     * the left (subtracts 1 from their indices).
     *
     * @param index the index of the element to be removed
     *
     * @return the element previously at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
     *         {@link #size()}
     *
     * @see #removeFirst()
     * @see #removeLast()
     */
    E remove(int index);

    /**
     * Removes the first occurrence of {@code element}, if it exists, from this list. Shifts any
     * subsequent elements to the left (subtracts 1 from their indices).
     *
     * @param element the element to be removed if exists
     *
     * @return {@code true} if {@code element} was removed from this list, else {@code false}
     */
    boolean remove(E element);

    /**
     * Removes the first element from this list. Shifts any subsequent elements to the left
     * (subtracts 1 from their indices).
     *
     * @return the previous first element of this list
     *
     * @throws IndexOutOfBoundsException if the list is empty
     *
     * @see #remove(int)
     * @see #removeLast()
     */
    default E removeFirst() {
        return remove(0);
    }

    /**
     * Removes the last element from this list.
     *
     * @return the previous last element of this list
     *
     * @throws IndexOutOfBoundsException if the list is empty
     *
     * @see #remove(int)
     * @see #removeFirst()
     */
    default E removeLast() {
        return remove(size() - 1);
    }

    /**
     * Removes from this list all elements that are contained in {@code iterable}.
     *
     * @param iterable the {@link Iterable} containing the elements to be removed from this list
     *
     * @return {@code true} if this list changed as a result of this call, else {@code false}
     *
     * @throws NullPointerException if {@code iterable} is {@code null}, or if any of the elements
     *         in {@code iterable} is {@code null} and this list does not permit null elements
     */
    default boolean removeAll(Iterable<? extends E> iterable) {
        checkNotNull(iterable);
        // For each distinct element of the iterable, remove it from this list until it no longer
        // exists, then return the final result as true if any of the removals modified the list.
        return StreamSupport.stream(iterable.spliterator(), false).distinct().map(element -> {
            var wrapper = new Object() {
                boolean modified = false;
            };
            while (remove(element)) {
                wrapper.modified = true;
            }
            return wrapper.modified;
        }).reduce(Boolean::logicalOr).orElse(false);
    }

    /**
     * Removes all elements from this list. The list will be empty after this call.
     */
    void clear();

    /**
     * Reverses this list's order of elements.
     */
    void reverse();

}
