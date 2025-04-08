package com.philectron.algorithms.datastructures.queues;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Deque<E> {

    static final int DEFAULT_CAPACITY = 20;

    private final int capacity;
    private E[] array;
    private int front;
    private int size;

    /**
     * Initializes an empty array deque with default capacity and no capacity restrictions.
     */
    public ArrayDeque() {
        this(DEFAULT_CAPACITY, -1);
    }

    /**
     * Initializes an empty array deque with the given maximum capacity, where a value of {@code -1}
     * for maximum capacity means no restrictions.
     *
     * @param initialCapacity the initial amount of memory to be allocated for the array
     * @param maximumCapacity the maximum capacity restriction the array can hold
     */
    public ArrayDeque(int initialCapacity, int maximumCapacity) {
        checkArgument(maximumCapacity == -1 || initialCapacity <= maximumCapacity,
                "Initial capacity must be at or below maximum capacity");
        front = 0;
        size = 0;
        capacity = maximumCapacity;
        array = allocateArray(initialCapacity);
    }

    /**
     * Initializes a default array deque with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this stack
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    public ArrayDeque(Iterable<? extends E> iterable) {
        this();
        offerAll(iterable);
    }

    /**
     * Allocates a new primitive array of length {@code capacity}.
     *
     * @param capacity the capacity to allocate memory for the array
     *
     * @return the newly allocated array
     *
     * @throws IllegalArgumentException if {@code capacity} is not positive.
     */
    @SuppressWarnings("unchecked")
    private E[] allocateArray(int capacity) {
        checkArgument(capacity > 0, "Capacity must be positive");
        return (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offerFront(E element) {
        checkNotNull(element);

        if (size == capacity) {
            return false;
        }

        if (size == array.length) {
            growArray();
        }

        front = (front - 1 + array.length) % array.length;
        array[front] = element;
        ++size;

        return true;
    }

    @Override
    public boolean offerRear(E element) {
        checkNotNull(element);

        if (size == capacity) {
            return false;
        }

        if (size == array.length) {
            growArray();
        }

        array[(front + size) % array.length] = element;
        ++size;

        return true;
    }

    @Override
    public E pollFront() {
        if (isEmpty()) {
            return null;
        }
        E oldData = array[front];
        front = (front + 1) % array.length;
        --size;
        return oldData;
    }

    @Override
    public E pollRear() {
        if (isEmpty()) {
            return null;
        }
        E oldData = array[(front + size - 1) % array.length];
        --size;
        return oldData;
    }

    @Override
    public E peekFront() {
        return !isEmpty() ? array[front] : null;
    }

    @Override
    public E peekRear() {
        return !isEmpty() ? array[(front + size - 1) % array.length] : null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = front;
            private int numElements = 0;

            @Override
            public boolean hasNext() {
                return numElements < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Iterator has no more elements");
                }
                E currentData = array[currentIndex];
                currentIndex = (currentIndex + 1) % array.length;
                ++numElements;
                return currentData;
            }
        };
    }

    /**
     * Allocates a new array with double the capacity of the original array and copies all the
     * elements there.
     */
    private void growArray() {
        E[] oldArray = assertNotNull(array);
        final int oldFront = front;

        array = allocateArray(oldArray.length * 2);

        // Copy the rear elements, ranging from 0 (inclusive) to the old front (exclusive).
        for (int i = 0; i < oldFront; ++i) {
            array[i] = oldArray[i];
            oldArray[i] = null; // cleanup
        }

        // Copy the front elements, ranging from the old front (inclusive) to end of the old array.
        for (int i = oldFront; i < oldArray.length; ++i) {
            array[i + oldArray.length] = oldArray[i];
            oldArray[i] = null; // cleanup
        }

        // Set the new front using the old front's offset relative to the end of the old array.
        front = oldFront + oldArray.length;
    }

}
