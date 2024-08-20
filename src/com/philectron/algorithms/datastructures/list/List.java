package com.philectron.algorithms.datastructures.list;

import java.util.ArrayList;
import java.util.Iterator;

public interface List<E> extends Iterable<E> {

    int size();

    boolean isEmpty();

    /**
     * Retrieves an element from the specified index in this list.
     *
     * @param index the index of the element to return
     *
     * @return the element at the specified index in this list
     *
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index} < 0 or
     *         {@code index} >= {@link List#size()})
     */
    E get(int index);

    /**
     * Replaces the element at the specified index in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param element the element to be stored at the specified index
     *
     * @return the previous element at the specified index
     *
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index} < 0 or
     *         {@code index} >= {@link List#size()})
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
     *         or {@code position} > {@link List#size()})
     *
     * @see List#addFront(E)
     * @see List#addBack(E)
     */
    void add(int position, E element);

    /**
     * Inserts the specified element to the front of this list. Shifts the current element at that
     * position (if any) and any subsequent elements to the right (adds 1 to their indices).
     *
     * @param element the element to be inserted
     *
     * @see List#add(int, E)
     */
    void addFront(E element);

    /**
     * Inserts the specified element to the back of this list.
     *
     * @param element the element to be inserted
     *
     * @see List#add(int, E)
     */
    void addBack(E element);

    /**
     * Inserts all elements in the specified array to the back of this list.
     *
     * @param array the array containing elements to be inserted
     *
     * @throws NullPointerException if the specified array is {@code null}
     */
    void addAll(E[] array);

    /**
     * Inserts all elements in the specified Java {@link java.util.List} to the back of this list.
     *
     * @param list the list containing elements to be inserted
     *
     * @throws NullPointerException if the specified list is {@code null}
     */
    void addAll(java.util.List<E> list);

    /**
     * Finds the first index of the specified element in this list.
     *
     * @param element the element to be searched within this list
     *
     * @return the first index of the specified element, or {@code -1} if this list does not contain
     *         the element
     *
     * @see List#lastIndexOf(E)
     */
    int indexOf(E element);

    /**
     * Finds the last index of the specified element in this list.
     *
     * @param element the element to be searched within this list
     *
     * @return the last index of the specified element, or {@code -1} if this list does not contain
     *         the element
     *
     * @see List#indexOf(E)
     */
    int lastIndexOf(E element);

    /**
     * Checks if this list contains the specified element.
     *
     * @param element the element to be searched within this list
     *
     * @return {@code true} if this list contains the specified element, or {@code false} otherwise
     *
     * @see List#indexOf(E)
     * @see List#lastIndexOf(E)
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
     *         {@code index} >= {@link List#size()})
     *
     * @see List#removeFront()
     * @see List#removeBack()
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
     * @see List#remove(int)
     */
    E removeFront();

    /**
     * Removes the element at the back of this list.
     *
     * @return the element previously at the back of this list
     *
     * @throws IndexOutOfBoundsException if the list is empty
     *
     * @see List#remove(int)
     */
    E removeBack();

    /**
     * Removes all elements from this list. The list will be empty after this method returns.
     */
    void clear();

    /**
     * Copies all elements of this list to a new Java {@link java.util.List}.
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
