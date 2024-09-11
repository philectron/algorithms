package com.philectron.algorithms.datastructures.interfaces;

public interface Indexable<E> extends Iterable<E> {

    /**
     * Retrieves the size of this collection.
     *
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Retrieves the element at index {@code index} of this collection.
     *
     * @param index the index of the element to return
     *
     * @return the element at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than the size
     *         of this collection, or if this collection is empty
     *
     * @see #getFirst()
     * @see #getLast()
     */
    E get(int index);

    /**
     * Retrieves the first element of this collection.
     *
     * @return the element at the first index of this collection
     *
     * @throws IndexOutOfBoundsException if this collection is empty
     *
     * @see #get(int)
     * @see #getLast()
     */
    default E getFirst() {
        return get(0);
    }

    /**
     * Retrieves the last element of this collection.
     *
     * @return the element at the last index of this collection
     *
     * @throws IndexOutOfBoundsException if this collection is empty
     *
     * @see #get(int)
     * @see #getFirst()
     */
    default E getLast() {
        return get(size() - 1);
    }

    /**
     * Finds the first occurrence of {@code element} in this collection.
     *
     * @param element the element to be searched in this collection
     *
     * @return the index of the first occurrence of {@code element}, or {@code -1} if this
     *         collection does not contain {@code element}
     *
     * @throws NullPointerException if {@code element} is {@code null} and this collection does not
     *         permit null elements
     *
     * @see #lastIndexOf(E)
     */
    int indexOf(E element);

    /**
     * Finds the last occurrence of {@code element} in this collection.
     *
     * @param element the element to be searched in this collection
     *
     * @return the index of the last occurrence of {@code element}, or {@code -1} if this collection
     *         does not contain {@code element}
     *
     * @throws NullPointerException if {@code element} is {@code null} and this collection does not
     *         permit null elements
     *
     * @see #indexOf(E)
     */
    int lastIndexOf(E element);

}
