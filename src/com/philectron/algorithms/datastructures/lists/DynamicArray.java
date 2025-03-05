package com.philectron.algorithms.datastructures.lists;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.philectron.algorithms.logic.Assertion.assertElementIndex;
import static com.philectron.algorithms.logic.Assertion.assertNotNegative;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;
import static com.philectron.algorithms.logic.Assertion.assertPositionIndex;

import com.philectron.algorithms.datastructures.interfaces.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements List<E> {

    private E[] array;
    private int size;

    /**
     * Initializes an empty dynamic array with capacity of 1.
     */
    public DynamicArray() {
        size = 0;
        array = allocateArray(1);
    }

    /**
     * Initializes a dynamic array with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this list
     *
     * @throws NullPointerException if {@code iterable} is {@code null}
     */
    public DynamicArray(Iterable<? extends E> iterable) {
        this();
        addAll(iterable);
    }

    /**
     * Allocates a new primitive array of length {@code capacity}.
     *
     * @param capacity the initial capacity to allocate memory for the array
     *
     * @return the newly allocated array
     */
    @SuppressWarnings("unchecked")
    private E[] allocateArray(int capacity) {
        return (E[]) new Object[assertNotNegative(capacity)];
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

        for (int i = size; i > position; --i) {
            array[i] = array[i - 1];
        }

        array[position] = element;

        ++size;
    }

    /**
     * Allocates a new array with double the capacity of the original array and copies all the
     * elements there.
     */
    private void growArray() {
        E[] oldArray = assertNotNull(array);
        assertPositionIndex(size, oldArray.length);
        array = allocateArray(oldArray.length * 2);
        for (int i = 0; i < oldArray.length; ++i) {
            array[i] = oldArray[i];
            oldArray[i] = null; // to help with garbage collection
        }
    }

    @Override
    public int indexOf(E element) {
        assertPositionIndex(size, array.length);
        int firstIndex = 0;
        for (int i = 0; i < size; ++i) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return firstIndex;
            }
            ++firstIndex;
        }
        return -1; // not found
    }

    @Override
    public int lastIndexOf(E element) {
        assertPositionIndex(size, array.length);
        int lastIndex = size - 1;
        for (int i = size - 1; i >= 0; --i) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return lastIndex;
            }
            --lastIndex;
        }
        return -1; // not found
    }

    @Override
    public E remove(int index) {
        assertPositionIndex(size, array.length);
        checkElementIndex(index, size);

        E oldValue = array[index];

        for (int i = index; i < size - 1; ++i) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null; // to help with garbage collection

        --size;
        if (size == array.length / 2) {
            shrinkArray();
        }

        return oldValue;
    }

    @Override
    public boolean remove(E element) {
        assertPositionIndex(size, array.length);

        final int index = indexOf(element);
        if (index == -1) {
            return false; // element not found, array was unmodified
        }

        remove(index);

        return true; // element found, array was modified
    }

    /**
     * Allocates a new array with half the capacity of the original array and copies all the
     * elements there.
     */
    private void shrinkArray() {
        E[] oldArray = assertNotNull(array);
        assertPositionIndex(size, oldArray.length);
        array = allocateArray(oldArray.length / 2);
        for (int i = 0; i < size; ++i) {
            array[i] = oldArray[i];
            oldArray[i] = null; // to help with garbage collection
        }
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeLast();
        }
    }

    @Override
    public void reverse() {
        assertPositionIndex(size, array.length);
        for (int i = 0, mid = size / 2; i < mid; ++i) {
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
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Iterator has no more elements");
                }
                assertPositionIndex(size, array.length);
                assertElementIndex(currentIndex, size);
                return array[currentIndex++];
            }
        };
    }

}
