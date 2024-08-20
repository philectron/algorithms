package com.philectron.algorithms.datastructures.list;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next; // will only be null to terminate the list

        private Node(final E data, final Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head; // will only be null if the list is empty
    private int size;

    public SinglyLinkedList() {}

    public SinglyLinkedList(final java.util.List<E> list) {
        this();
        addAll(list);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(final int index) {
        // Index should always be in the list's element range [0, n - 1]
        // because we are fetching an existing element from the list.
        Preconditions.checkElementIndex(index, size);

        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = Verify.verifyNotNull(node).next;
        }

        return Verify.verifyNotNull(node).data;
    }

    @Override
    public E set(final int index, final E element) {
        // Index should always be in the list's element range [0, n - 1]
        // because we are replacing an existing element in the list.
        Preconditions.checkElementIndex(index, size);

        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = Verify.verifyNotNull(node).next;
        }

        final E oldData = Verify.verifyNotNull(node).data;
        node.data = element;
        return oldData;
    }

    @Override
    public void add(final int position, final E element) {
        // Index should always be in the list's position range [0, n]
        // because we are inserting a new element into the list.
        Preconditions.checkPositionIndex(position, size);

        final Node<E> newNode = new Node<>(element, null);

        // If insert at the head, the new node becomes the head.
        if (position == 0) {
            // From: N1 (head) -> N2
            // To: newNode (head) -> N1 -> N2
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        // Traverse the list to the node right before the insert position.
        Node<E> previous = head;
        for (int i = 0; i < position - 1; i++) {
            previous = Verify.verifyNotNull(previous).next;
        }

        // From: N1 (previous) -> N2
        // To: N1 (previous) -> newNode -> N2
        newNode.next = Verify.verifyNotNull(previous).next;
        previous.next = newNode;
        size++;
    }

    @Override
    public void addFront(final E element) {
        add(0, element);
    }

    @Override
    public void addBack(final E element) {
        add(size, element);
    }

    @Override
    public void addAll(final E[] array) {
        Preconditions.checkNotNull(array);
        for (int i = array.length - 1; i >= 0; i--) {
            addFront(array[i]);
        }
    }

    @Override
    public void addAll(final java.util.List<E> list) {
        Preconditions.checkNotNull(list);
        @SuppressWarnings("unchecked")
        final E[] array = list.toArray((E[]) new Object[0]);
        addAll(array);
    }

    @Override
    public int indexOf(final E element) {
        int firstIndex = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (node.data.equals(element)) {
                return firstIndex;
            }
            firstIndex++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(final E element) {
        int lastIndex = -1;
        int currentIndex = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (node.data.equals(element)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
        }
        return lastIndex;
    }

    @Override
    public boolean contains(final E element) {
        return indexOf(element) > -1;
    }

    @Override
    public E remove(final int index) {
        // Index should always be in the list's element range [0, n - 1]
        // because we are removing an existing element from the list.
        Preconditions.checkElementIndex(index, size);

        // If remove the head, the next node becomes the head.
        if (index == 0) {
            // From: N1 (head) -> N2 -> N3
            // To: N2 (head) -> N3
            final Node<E> newHead = Verify.verifyNotNull(head).next;
            final E oldData = head.data;
            head.data = null;
            head.next = null;
            head = newHead;
            size--;
            return oldData;
        }

        // Traverse the list to the node right before the node to be removed.
        Node<E> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = Verify.verifyNotNull(previous).next;
        }

        // From: N1 (previous) -> N2 -> N3
        // To: N1 (previous) -> N3
        final Node<E> nodeToRemove = Verify.verifyNotNull(previous).next;
        final E oldData = nodeToRemove.data;
        previous.next = Verify.verifyNotNull(nodeToRemove).next;
        nodeToRemove.data = null;
        nodeToRemove.next = null;
        size--;
        return oldData;
    }

    @Override
    public E removeFront() {
        return remove(0);
    }

    @Override
    public E removeBack() {
        return remove(size - 1);
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFront();
        }
    }

    public void reverse() {
        Node<E> previous = null;
        Node<E> node = head;
        while (node != null) {
            final Node<E> next = node.next;
            node.next = previous;
            previous = node;
            node = next;
        }
        head = previous;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                final E currentData = current.data;
                current = current.next;
                return currentData;
            }
        };
    }

}
