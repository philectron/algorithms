package com.philectron.algorithms.datastructures.linkedlist;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;

public class SinglyLinkedList {

    static class Node {
        private int data;
        private Node next; // will only be null to terminate the list.

        private Node(final int data) {
            this.data = data;
        }
    }

    private Node head; // will only be null if the list is empty.
    private int size;

    public static SinglyLinkedList fromArray(final int[] array) {
        Preconditions.checkNotNull(array);
        final SinglyLinkedList list = new SinglyLinkedList();
        for (int i = array.length - 1; i >= 0; i--) {
            list.addFront(array[i]);
        }
        return list;
    }

    public int get(final int index) {
        // Index should always be in the list's element range [0, n - 1]
        // because we are fetching an existing element from the list.
        Preconditions.checkElementIndex(index, size);

        Node node = head;
        for (int i = 0; i < index; i++) {
            node = Verify.verifyNotNull(node).next;
        }

        return Verify.verifyNotNull(node).data;
    }

    public boolean contains(final int target) {
        for (Node node = head; node != null; node = node.next) {
            if (node.data == target) {
                return true;
            }
        }
        return false;
    }

    public void add(final int position, final int value) {
        // Index should always be in the list's position range [0, n]
        // because we are inserting a new element into the list.
        Preconditions.checkPositionIndex(position, size);

        final Node newNode = new Node(value);

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
        Node previous = head;
        for (int i = 0; i < position - 1; i++) {
            previous = Verify.verifyNotNull(previous).next;
        }

        // From: N1 (previous) -> N2
        // To: N1 (previous) -> newNode -> N2
        newNode.next = Verify.verifyNotNull(previous).next;
        previous.next = newNode;
        size++;
    }

    public void addFront(final int value) {
        add(0, value);
    }

    public void addBack(final int value) {
        add(size, value);
    }

    public void remove(final int index) {
        // Index should always be in the list's element range [0, n - 1]
        // because we are removing an existing element from the list.
        Preconditions.checkElementIndex(index, size);

        // If remove the head, the next node becomes the head.
        if (index == 0) {
            // From: N1 (head) -> N2 -> N3
            // To: N2 (head) -> N3
            final Node newHead = Verify.verifyNotNull(head).next;
            head.data = 0;
            head.next = null;
            head = newHead;
            size--;
            return;
        }

        // Traverse the list to the node right before the node to be removed.
        Node previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = Verify.verifyNotNull(previous).next;
        }

        // From: N1 (previous) -> N2 -> N3
        // To: N1 (previous) -> N3
        final Node nodeToRemove = Verify.verifyNotNull(previous).next;
        previous.next = Verify.verifyNotNull(nodeToRemove).next;
        nodeToRemove.data = 0;
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

    public int[] toArray() {
        final int[] array = new int[size];

        Node node = head;
        for (int i = 0; i < size; i++) {
            array[i] = Verify.verifyNotNull(node).data;
            node = node.next;
        }

        return array;
    }

    public void reverse() {
        Node previous = null;
        Node node = head;
        while (node != null) {
            final Node next = node.next;
            node.next = previous;
            previous = node;
            node = next;
        }
        head = previous;
    }

}
