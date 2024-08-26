package com.philectron.algorithms.datastructures.list;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.philectron.algorithms.logic.Assertion.assertNotNegative;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;
import static com.philectron.algorithms.logic.Assertion.assertPositionIndex;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements List<E> {

    private E[] array;
    private int size;

    /**
     * Initializes an empty dynamic array with capacity of 1.
     */
    public DynamicArray() {
        this.size = 0;
        this.array = allocateArray(1);
    }

    /**
     * Initializes a dynamic array with all elements copied from {@code list}.
     *
     * @param list the {@link java.util.List} whose elements are to be copied to this array
     *
     * @throws NullPointerException if {@code list} is {@code null}
     */
    public DynamicArray(java.util.List<? extends E> list) {
        checkNotNull(list);
        this.size = 0;
        this.array = allocateArray(list.size());
        addAll(list);
    }

    /**
     * Allocates an array of size {@code capacity}.
     *
     * @param capacity the initial capacity to allocate memory for the array
     *
     * @return the newly allocated array
     */
    @SuppressWarnings("unchecked")
    private E[] allocateArray(int capacity) {
        assertNotNegative(capacity);
        E[] array = (E[]) new Object[capacity];
        return array;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        assertPositionIndex(size, array.length);
        checkElementIndex(index, size);
        return array[index];
    }

    @Override
    public E set(int index, E element) {
        assertPositionIndex(size, array.length);
        checkElementIndex(index, size);
        E oldValue = array[index];
        array[index] = element;
        return oldValue;
    }

    @Override
    public void add(int position, E element) {
        assertPositionIndex(size, array.length);
        checkPositionIndex(position, size);

        // Grow the array if the capacity is reached.
        if (size == array.length) {
            growArray();
        }

        for (int i = size; i > position; i--) {
            array[i] = array[i - 1];
        }

        array[position] = element;
        size++;
    }

    /**
     * Allocates a new array with double the capacity of the original array and copies all the
     * elements there.
     */
    private void growArray() {
        E[] oldArray = assertNotNull(array);
        assertPositionIndex(size, oldArray.length);
        array = allocateArray(oldArray.length * 2);
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
            oldArray[i] = null; // to help with garbage collection
        }
    }

    @Override
    public void addAll(java.util.List<? extends E> list) {
        checkNotNull(list);
        for (E element : list) {
            addBack(element);
        }
    }

    @Override
    public int indexOf(E element) {
        assertPositionIndex(size, array.length);
        int firstIndex = 0;
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return firstIndex;
            }
            firstIndex++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        assertPositionIndex(size, array.length);
        int lastIndex = size - 1;
        for (int i = size - 1; i >= 0; i--) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return lastIndex;
            }
            lastIndex--;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        assertPositionIndex(size, array.length);
        checkElementIndex(index, size);

        E oldValue = array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null; // to help with garbage collection

        size--;
        if (size == array.length / 2) {
            shrinkArray();
        }
        return oldValue;
    }

    private void shrinkArray() {
        E[] oldArray = assertNotNull(array);
        assertPositionIndex(size, oldArray.length);
        array = allocateArray(oldArray.length / 2);
        for (int i = 0; i < size; i++) {
            array[i] = oldArray[i];
            oldArray[i] = null; // to help with garbage collection
        }
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeBack();
        }
    }

    @Override
    public void reverse() {
        assertPositionIndex(size, array.length);
        for (int i = 0, mid = size / 2; i < mid; i++) {
            E tmp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = tmp;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                assertPositionIndex(size, array.length);
                assertPositionIndex(currentIndex, size);
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Iterator has no more elements");
                }
                return array[currentIndex++];
            }
        };
    }

}
