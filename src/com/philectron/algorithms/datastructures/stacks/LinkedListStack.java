package com.philectron.algorithms.datastructures.stacks;

import static com.google.common.base.Preconditions.checkNotNull;

import com.philectron.algorithms.datastructures.interfaces.Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LinkedListStack<E> implements Stack<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            next = null;
        }
    }

    private Node<E> top;
    private int size;

    /**
     * Initializes an empty linked list stack.
     */
    public LinkedListStack() {
        top = null;
        size = 0;
    }

    /**
     * Initializes a linked list stack with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this stack
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    public LinkedListStack(Iterable<? extends E> iterable) {
        this();
        pushAll(iterable);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(E element) {
        checkNotNull(element);

        Node<E> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;

        size++;
    }

    @Override
    public Optional<E> pop() {
        if (isEmpty()) {
            return Optional.empty();
        }

        Node<E> nodeToRemove = top;
        E oldData = nodeToRemove.data;
        top = nodeToRemove.next;

        // Cleanup to help with garbage collection.
        nodeToRemove.data = null;
        nodeToRemove.next = null;

        size--;
        return Optional.of(oldData);
    }

    @Override
    public Optional<E> top() {
        if (isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(top.data);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> currentNode = top;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Iterator has no more elements");
                }
                E currentData = currentNode.data;
                currentNode = currentNode.next;
                return currentData;
            }
        };
    }

}
