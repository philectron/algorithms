package com.philectron.algorithms.datastructures.deques;

import static com.google.common.base.Preconditions.checkNotNull;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Deque<E> {

    static final int DEFAULT_QUEUE_CAPACITY = 20;

    private E[] array;
    private int front;
    private int rear;
    private int size;

    /**
     * Initializes an empty array deque.
     */
    public ArrayDeque() {
        front = -1;
        rear = 0;
        size = 0;
        array = allocateArray(DEFAULT_QUEUE_CAPACITY);
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
        pushRearAll(iterable);
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
    public void pushFront(E element) {
        checkNotNull(element);

        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }

        if (front == -1) {
            rear = front = 0;
        } else if (front == 0) {
            front = array.length - 1;
        } else {
            front--;
        }

        array[front] = element;
        size++;
    }

    @Override
    public void pushRear(E element) {
        checkNotNull(element);

        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }

        if (front == -1) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % array.length;
        }

        array[rear] = element;
        size++;
    }

    @Override
    public E popFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        E oldData = array[front];
        front = (front + 1) % array.length;
        size--;
        return oldData;
    }

    @Override
    public E popRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        E oldData = array[rear];
        rear--;
        if (rear < 0) {
            rear = size - 1;
        }
        size--;
        return oldData;
    }

    @Override
    public E peekFront() {
        return !isEmpty() ? array[front] : null;
    }

    @Override
    public E peekRear() {
        return !isEmpty() ? array[rear] : null;
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
                numElements++;
                return currentData;
            }
        };
    }

}
