package com.philectron.algorithms.datastructures.deques;

import static com.google.common.base.Preconditions.checkNotNull;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<E> implements Deque<E> {

    private static class Node<E> {
        private E data;
        private Node<E> previous;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            next = previous = null;
        }
    }

    private Node<E> front;
    private Node<E> rear;
    private int size;

    /**
     * Initializes an empty linked deque.
     */
    public LinkedListDeque() {
        rear = front = null;
        size = 0;
    }

    /**
     * Initializes a linked deque with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this deque
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    public LinkedListDeque(Iterable<? extends E> iterable) {
        this();
        pushRearAll(iterable);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void pushFront(E element) {
        checkNotNull(element);

        Node<E> newNode = new Node<>(element);
        newNode.next = front;

        if (front != null) {
            front.previous = newNode;
        } else {
            // If the front node is null, then this deque is currently empty,
            // so the new node also becomes the rear node.
            rear = newNode;
        }

        front = newNode;
        ++size;
    }

    @Override
    public void pushRear(E element) {
        checkNotNull(element);

        Node<E> newNode = new Node<>(element);
        newNode.previous = rear;

        if (rear != null) {
            rear.next = newNode;
        } else {
            // If the rear node is null, then this deque is currently empty,
            // so the new node also becomes the front node.
            front = newNode;
        }

        rear = newNode;
        ++size;
    }

    @Override
    public E popFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Node<E> nodeToRemove = front;
        E oldData = nodeToRemove.data;
        front = nodeToRemove.next;

        if (front != null) {
            front.previous = null;
        } else {
            // If the front node is now null, then this deque is now empty,
            // so the rear node also needs to be null.
            rear = null;
        }

        // Cleanup to help with garbage collection.
        nodeToRemove.data = null;
        nodeToRemove.next = nodeToRemove.previous = null;

        --size;
        return oldData;
    }

    @Override
    public E popRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Node<E> nodeToRemove = rear;
        E oldData = nodeToRemove.data;
        rear = nodeToRemove.previous;

        if (rear != null) {
            rear.next = null;
        } else {
            // If the rear node is now null, then this deque is now empty,
            // so the front node also needs to be null.
            front = null;
        }

        // Cleanup to help with garbage collection.
        nodeToRemove.data = null;
        nodeToRemove.next = nodeToRemove.previous = null;

        --size;
        return oldData;
    }

    @Override
    public E peekFront() {
        return !isEmpty() ? front.data : null;
    }

    @Override
    public E peekRear() {
        return !isEmpty() ? rear.data : null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> currentNode = front;

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
