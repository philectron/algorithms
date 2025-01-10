package com.philectron.algorithms.datastructures.queues;

import static com.google.common.base.Preconditions.checkNotNull;

import com.philectron.algorithms.datastructures.interfaces.Queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {

    static final int DEFAULT_QUEUE_CAPACITY = 20;

    private E[] array;
    private int front;
    private int rear;
    private int size;

    /**
     * Initializes an empty array queue.
     */
    public ArrayQueue() {
        front = 0;
        rear = -1;
        size = 0;
        array = allocateArray(DEFAULT_QUEUE_CAPACITY);
    }

    /**
     * Initializes an array queue with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this stack
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    public ArrayQueue(Iterable<? extends E> iterable) {
        this();
        enqueueAll(iterable);
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
     * Checks if this queue is full.
     *
     * @return {@code true} if this queue can no longer hold more elements, or {@code false}
     *         otherwise
     */
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public void enqueue(E element) {
        checkNotNull(element);

        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        rear = (rear + 1) % array.length;
        array[rear] = element;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        E oldData = array[front];
        array[front] = null;
        front = (front + 1) % array.length;

        size--;
        return oldData;
    }

    @Override
    public E peek() {
        return !isEmpty() ? array[front] : null;
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
