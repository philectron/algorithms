package com.philectron.algorithms.datastructures.stacks;

import static com.google.common.base.Preconditions.checkNotNull;

import com.philectron.algorithms.datastructures.interfaces.Stack;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {

    static final int DEFAULT_STACK_CAPACITY = 20;

    private E[] array;
    private int size;

    /**
     * Initializes an empty array stack.
     */
    public ArrayStack() {
        size = 0;
        array = allocateArray(DEFAULT_STACK_CAPACITY);
    }

    /**
     * Initializes an array stack with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this stack
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    public ArrayStack(Iterable<? extends E> iterable) {
        this();
        pushAll(iterable);
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
     * Checks if this stack is full.
     *
     * @return {@code true} if this stack can no longer hold more elements, or {@code false}
     *         otherwise
     */
    public boolean isFull() {
        return size == array.length;
    }

    /**
     * @throws IllegalStateException if this stack is full
     */
    @Override
    public void push(E element) {
        checkNotNull(element);

        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }

        array[size++] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        E oldData = array[size - 1];
        array[size - 1] = null;

        --size;
        return oldData;
    }

    @Override
    public E peek() {
        return !isEmpty() ? array[size - 1] : null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = size - 1;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Iterator has no more elements");
                }
                return array[currentIndex--];
            }
        };
    }

}
