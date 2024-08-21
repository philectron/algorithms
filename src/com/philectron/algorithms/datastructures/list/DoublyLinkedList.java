package com.philectron.algorithms.datastructures.list;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> previous;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            this.next = this.previous = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {}

    public DoublyLinkedList(java.util.List<? extends E> list) {
        this();
        addAll(list);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        Preconditions.checkElementIndex(index, size);

        Node<E> node = Verify.verifyNotNull(head);
        for (int i = 0; i < index; i++) {
            node = Verify.verifyNotNull(node.next);
        }

        return node.data;
    }

    @Override
    public E set(int index, E element) {
        Preconditions.checkElementIndex(index, size);

        Node<E> node = Verify.verifyNotNull(head);
        for (int i = 0; i < index; i++) {
            node = Verify.verifyNotNull(node.next);
        }

        E oldData = node.data;
        node.data = element;

        return oldData;
    }

    @Override
    public void add(int position, E element) {
        Preconditions.checkPositionIndex(position, size);

        Node<E> newNode = new Node<>(element);

        // If insert at the head, the new node becomes the head. This branch handles empty list.
        if (position == 0) {
            // From: N1 (head) <-> N2
            // To: newNode (head) <-> N1 <-> N2
            newNode.next = head;

            if (head != null) {
                head.previous = newNode;
            } else {
                // If head is null, this list is empty, so the new node also becomes the tail.
                tail = newNode;
            }

            head = newNode;
            size++;
            return;
        }

        // If insert at the tail, the new node becomes the tail.
        if (position == size) {
            // From: N1 <-> N2 (tail)
            // To: N1 <-> N2 <-> newNode (tail)
            // Empty list is already handled above, so tail is never null here.
            newNode.previous = Verify.verifyNotNull(tail);
            tail.next = newNode;

            tail = newNode;
            size++;
            return;
        }

        // For all other positions, traverse the list to the node right before the insert position.
        Node<E> previousNode = Verify.verifyNotNull(head);
        for (int i = 0; i < position - 1; i++) {
            previousNode = Verify.verifyNotNull(previousNode.next);
        }

        // From: N1 (previous) <-> N2 (next)
        // To: N1 (previous) <-> newNode <-> N2 (next)
        // Next node is never null because the edge cases are already handled above.
        Node<E> nextNode = Verify.verifyNotNull(previousNode.next);
        newNode.previous = previousNode;
        newNode.next = nextNode;
        previousNode.next = nextNode.previous = newNode;
        size++;
    }

    @Override
    public void addAll(java.util.List<? extends E> list) {
        Preconditions.checkNotNull(list);
        for (E element : list) {
            addBack(element);
        }
    }

    @Override
    public int indexOf(E element) {
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
    public int lastIndexOf(E element) {
        int lastIndex = size - 1;
        for (Node<E> node = tail; node != null; node = node.previous) {
            if (node.data.equals(element)) {
                return lastIndex;
            }
            lastIndex--;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        Preconditions.checkElementIndex(index, size);

        // If remove at the head, the next node becomes the head. This branch handles singleton list.
        if (index == 0) {
            // From: N1 (head) <-> N2 <-> N3
            // To: N2 (head) <-> N3
            Node<E> nodeToRemove = Verify.verifyNotNull(head);
            E oldData = nodeToRemove.data;
            head = nodeToRemove.next;

            if (head != null) {
                head.previous = null;
            } else {
                // If the new head is null, this list has only one node, so the tail becomes null.
                tail = null;
            }

            nodeToRemove.data = null;
            nodeToRemove.next = nodeToRemove.previous = null;

            size--;
            return oldData;
        }

        // If remove at the tail, the previous node becomes the tail.
        if (index == size - 1) {
            // From: N1 <-> N2 <-> N3 (tail)
            // To: N1 <-> N2 (tail)
            Node<E> nodeToRemove = Verify.verifyNotNull(tail);
            E oldData = nodeToRemove.data;
            // Singleton list is already handled above, so the new tail is never null here.
            tail = Verify.verifyNotNull(nodeToRemove.previous);
            tail.next = null;

            nodeToRemove.data = null;
            nodeToRemove.next = nodeToRemove.previous = null;

            size--;
            return oldData;
        }

        // For all other indices, traverse the list to the node right before the node to be removed.
        Node<E> previousNode = Verify.verifyNotNull(head);
        for (int i = 0; i < index - 1; i++) {
            previousNode = Verify.verifyNotNull(previousNode.next);
        }

        // From: N1 (previous) <-> N2 <-> N3
        // To: N1 (previous) <-> N3
        Node<E> nodeToRemove = Verify.verifyNotNull(previousNode.next);
        E oldData = nodeToRemove.data;
        Node<E> nextNode = nodeToRemove.next;
        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        nodeToRemove.data = null;
        nodeToRemove.next = nodeToRemove.previous = null;

        size--;
        return oldData;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFront();
        }
    }

    public void reverse() {
        Node<E> node = head;
        while (node != null) {
            Node<E> previousNode = node.previous;
            Node<E> nextNode = node.next;
            node.previous = nextNode;
            node.next = previousNode;
            node = nextNode;
        }
        node = head;
        head = tail;
        tail = node;
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
                Preconditions.checkNotNull(current);
                E currentData = current.data;
                current = current.next;
                return currentData;
            }
        };
    }

}
