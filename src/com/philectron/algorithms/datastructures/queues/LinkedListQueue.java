package com.philectron.algorithms.datastructures.queues;

import static com.google.common.base.Preconditions.checkNotNull;

import com.philectron.algorithms.datastructures.interfaces.Queue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LinkedListQueue<E> implements Queue<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            next = null;
        }
    }

    private Node<E> front;
    private Node<E> rear;
    private int size;

    /**
     * Initializes an empty linked queue.
     */
    public LinkedListQueue() {
        rear = front = null;
        size = 0;
    }

    /**
     * Initializes a linked queue with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this queue
     *
     * @throws NullPointerException if {@code iterable} or any of its elements is {@code null}
     */
    public LinkedListQueue(Iterable<? extends E> iterable) {
        this();
        enqueueAll(iterable);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(E element) {
        checkNotNull(element);

        Node<E> newNode = new Node<>(element);

        if (rear != null) {
            rear.next = newNode;
        } else {
            // If the rear node is null, then this queue is currently empty,
            // so the new node also becomes the front node.
            front = newNode;
        }

        rear = newNode;
        size++;
    }

    @Override
    public Optional<E> dequeue() {
        if (isEmpty()) {
            return Optional.empty();
        }

        Node<E> nodeToRemove = front;
        E oldData = nodeToRemove.data;
        front = nodeToRemove.next;

        // If the front node is now null, then this queue is now empty,
        // so the rear node also needs to be null.
        if (front == null) {
            rear = null;
        }

        // Cleanup to help with garbage collection.
        nodeToRemove.data = null;
        nodeToRemove.next = null;

        size--;
        return Optional.of(oldData);
    }

    @Override
    public Optional<E> peekFront() {
        if (isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(front.data);
    }

    @Override
    public Optional<E> peekRear() {
        if (isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(rear.data);
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
