package com.philectron.algorithms.datastructures.linkedlist;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SinglyLinkedList<E> implements Iterable<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next; // Will only be null to terminate the list.

        private Node(final E data) {
            this.data = data;
        }
    }

    private Node<E> head; // Will only be null if the list is empty.
    private int size;

    public SinglyLinkedList() {}

    public SinglyLinkedList(final E[] array) {
        this();
        addAll(array);
    }

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

    public boolean contains(final E target) {
        for (Node<E> node = head; node != null; node = node.next) {
            if (node.data.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public void add(final int position, final E value) {
        // Index should always be in the list's position range [0, n]
        // because we are inserting a new element into the list.
        Preconditions.checkPositionIndex(position, size);

        final Node<E> newNode = new Node<>(value);

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

    public void addFront(final E value) {
        add(0, value);
    }

    public void addBack(final E value) {
        add(size, value);
    }

    public void addAll(final E[] array) {
        Preconditions.checkNotNull(array);
        for (int i = array.length - 1; i >= 0; i--) {
            addFront(array[i]);
        }
    }

    public void remove(final int index) {
        // Index should always be in the list's element range [0, n - 1]
        // because we are removing an existing element from the list.
        Preconditions.checkElementIndex(index, size);

        // If remove the head, the next node becomes the head.
        if (index == 0) {
            // From: N1 (head) -> N2 -> N3
            // To: N2 (head) -> N3
            final Node<E> newHead = Verify.verifyNotNull(head).next;
            head.data = null;
            head.next = null;
            head = newHead;
            size--;
            return;
        }

        // Traverse the list to the node right before the node to be removed.
        Node<E> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = Verify.verifyNotNull(previous).next;
        }

        // From: N1 (previous) -> N2 -> N3
        // To: N1 (previous) -> N3
        final Node<E> nodeToRemove = Verify.verifyNotNull(previous).next;
        previous.next = Verify.verifyNotNull(nodeToRemove).next;
        nodeToRemove.data = null;
        nodeToRemove.next = null;
        size--;
    }

    public void removeFront() {
        remove(0);
    }

    public void removeBack() {
        remove(size - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public List<E> toList() {
        final List<E> list = new ArrayList<>();

        final Iterator<E> it = iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }

        return list;
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
        return new SinglyLinkedListIterator<>(this);
    }

    private static class SinglyLinkedListIterator<E> implements Iterator<E> {

        private Node<E> current;

        private SinglyLinkedListIterator(final SinglyLinkedList<E> list) {
            current = list.head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            final E data = current.data;
            current = current.next;
            return data;
        }

    }

}
