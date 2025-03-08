package com.philectron.algorithms.datastructures.queues;

import static com.google.common.base.Preconditions.checkNotNull;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Deque<E> {

    static final int DEFAULT_CAPACITY = 20;

    private E[] array;
    private int front;
    private int size;

    /**
     * Initializes an empty array deque.
     */
    public ArrayDeque() {
        front = 0;
        size = 0;
        array = allocateArray(DEFAULT_CAPACITY);
    }

    /**
     * Initializes an array deque with all elements copied from {@code iterable}.
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
     * @param capacity the initial capacity to allocate memory for the array
     *
     * @return the newly allocated array
     */
    @SuppressWarnings("unchecked")
    private E[] allocateArray(int capacity) {
        return (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if this deque is full.
     *
     * @return {@code true} if this deque can no longer hold more elements, or {@code false}
     *         otherwise
     */
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public boolean offerFront(E element) {
        checkNotNull(element);

        if (isFull()) {
            return false;
        }

        front = (front - 1 + array.length) % array.length;

        array[front] = element;
        ++size;

        return true;
    }

    @Override
    public boolean offerRear(E element) {
        checkNotNull(element);

        if (isFull()) {
            return false;
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

}
