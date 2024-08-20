package com.philectron.algorithms.datastructures.list;

import java.util.ArrayList;
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
    boolean isEmpty();

    /**
     * Retrieves an element from the specified index in this list.
     *
     * @param index the index of the element to return
     *
     * @return the element at the specified index in this list
     *
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index} < 0 or
     *         {@code index} >= {@link #size()})
     */
    E get(int index);

    /**
     * Replaces the element at the specified index in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param element the new element to be stored at the index
     *
     * @return the previous element at the specified index
     *
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index} < 0 or
     *         {@code index} >= {@link #size()})
     */
    E set(int index, E element);

    /**
     * Inserts the specified element at the specified position in this list. Shifts the current
     * element at that position (if any) and any subsequent elements to the right (adds 1 to their
     * indices).
     *
     * @param position the position index at which the new element is to be inserted
     * @param element the element to be inserted
     *
     * @throws IndexOutOfBoundsException if the position index is out of range ({@code position} < 0
     *         or {@code position} > {@link #size()})
     */
    void add(int position, E element);

    /**
     * Inserts the specified element to the front of this list. Shifts the current element at that
     * position (if any) and any subsequent elements to the right (adds 1 to their indices).
     *
     * @param element the element to be inserted
     *
     * @see #add(int, E)
     */
    void addFront(E element);

    /**
     * Inserts the specified element to the back of this list.
     *
     * @param element the element to be inserted
     *
     * @see #add(int, E)
     */
    void addBack(E element);

    /**
     * Inserts all elements in the specified {@link java.util.List} to the back of this list.
     *
     * @param list the Java list containing elements to be inserted
     *
     * @throws NullPointerException if the specified Java list is {@code null}
     */
    void addAll(java.util.List<E> list);

    /**
     * Finds the first index of the specified element in this list.
     *
     * @param element the element to be searched within this list
     *
     * @return the first index of the specified element, or {@code -1} if this list does not contain
     *         the element
     */
    int indexOf(E element);

    /**
     * Finds the last index of the specified element in this list.
     *
     * @param element the element to be searched within this list
     *
     * @return the last index of the specified element, or {@code -1} if this list does not contain
     *         the element
     */
    int lastIndexOf(E element);

    /**
     * Checks if this list contains the specified element.
     *
     * @param element the element to be searched within this list
     *
     * @return {@code true} if this list contains the specified element, or {@code false} otherwise
     *
     * @see #indexOf(E)
     * @see #lastIndexOf(E)
     */
    boolean contains(E element);

    /**
     * Removes the element at the specified index in this list. Shifts any subsequent elements to
     * the left (subtracts 1 from their indices).
     *
     * @param index the index of the element to be removed
     *
     * @return the element previously at the specified index
     *
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index} < 0 or
     *         {@code index} >= {@link #size()})
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
    E removeFront();

    /**
     * Removes the element at the back of this list.
     *
     * @return the element previously at the back of this list
     *
     * @throws IndexOutOfBoundsException if the list is empty
     *
     * @see #remove(int)
     */
    E removeBack();

    /**
     * Removes all elements from this list. The list will be empty after this method returns.
     */
    void clear();

    /**
     * Copies all elements of this list to a new {@link java.util.List}.
     *
     * @return a new {@link ArrayList} containing the elements of this list
     */
    default java.util.List<E> toJavaList() {
        java.util.List<E> list = new ArrayList<E>();
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

}
