package com.philectron.algorithms.datastructures.lists;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.philectron.algorithms.logic.Assertion.assertElementIndex;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import com.philectron.algorithms.datastructures.interfaces.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Initializes an empty singly linked list.
     */
    public SinglyLinkedList() {
        tail = head = null;
        size = 0;
    }

    /**
     * Initializes a singly linked list with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this list
     *
     * @throws NullPointerException if {@code iterable} is {@code null}
     */
    public SinglyLinkedList(Iterable<? extends E> iterable) {
        this();
        addAll(checkNotNull(iterable));
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Traverses through this list to the node at index {@code index}.
     *
     * @param index the index whose node will be returned
     *
     * @return the corresponding node at {@code index}, guaranteed to be non-null
     */
    private Node<E> nodeAt(int index) {
        assertElementIndex(index, size);
        if (index == size - 1) {
            return assertNotNull(tail);
        }
        Node<E> node = assertNotNull(head);
        for (int i = 0; i < index; i++) {
            node = assertNotNull(node.next);
        }
        return node;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index, size);
        return nodeAt(index).data;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index, size);
        Node<E> node = nodeAt(index);
        E oldData = node.data;
        node.data = element;
        return oldData;
    }

    @Override
    public boolean add(int position, E element) {
        checkPositionIndex(position, size);

        Node<E> newNode = new Node<>(element);

        // If insert at the head, the new node becomes the new head.
        // This branch handles empty list.
        if (position == 0) {
            return addHead(newNode);
        }

        // If insert at the tail, the new node becomes the new tail.
        if (position == size) {
            return addTail(newNode);
        }

        // For all other positions, traverse the list to the node right before the insert position.
        Node<E> previousNode = nodeAt(position - 1);

        // From: N1 (previousNode) -> N2
        // To: N1 (previousNode) -> newNode -> N2
        newNode.next = previousNode.next;
        previousNode.next = newNode;

        size++;
        return true;
    }

    /**
     * Helper method for {@link #add(int, E)} that inserts {@code newNode} as the new head of this
     * list.
     *
     * @param newNode the node to be inserted and made the new head
     *
     * @return always {@code true}
     */
    private boolean addHead(Node<E> newNode) {
        assertNotNull(newNode);

        // From: N1 (head) -> N2
        // To: newNode (head) -> N1 -> N2
        newNode.next = head;

        // If head is null, list is empty. In this case, the new node also becomes the tail.
        if (head == null) {
            tail = newNode;
        }

        head = newNode;

        size++;
        return true;
    }

    /**
     * Helper method for {@link #add(int, E)} that inserts the {@code newNode} as the new tail of
     * this list.
     *
     * @param newNode the node to be inserted and made the new tail
     *
     * @return always {@code true}
     */
    private boolean addTail(Node<E> newNode) {
        assertNotNull(newNode);

        // From: N1 -> N2 (tail)
        // To: N1 -> N2 -> newNode (tail)
        // Empty list is already handled above, so the tail is never null here.
        assertNotNull(tail);
        tail.next = newNode;
        tail = newNode;

        size++;
        return true;
    }

    @Override
    public int indexOf(E element) {
        int firstIndex = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (element == null ? node.data == null : element.equals(node.data)) {
                return firstIndex;
            }
            firstIndex++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        int lastIndex = -1;
        int currentIndex = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (element == null ? node.data == null : element.equals(node.data)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
        }
        return lastIndex;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, size);

        // If remove at the head, the next node becomes the new head.
        // This branch handles singleton list.
        if (index == 0) {
            return removeHead();
        }

        // For all other indices, traverse the list to the node right before the node to be removed.
        Node<E> previousNode = nodeAt(index - 1);

        // From: N1 (previousNode) -> N2 (nodeToRemove) -> N3
        // To: N1 (previousNode) -> N3
        Node<E> nodeToRemove = assertNotNull(previousNode.next);
        E oldData = nodeToRemove.data;
        previousNode.next = nodeToRemove.next;
        if (nodeToRemove == tail) {
            tail = previousNode;
        }

        // Cleanup to help garbage collection.
        nodeToRemove.data = null;
        nodeToRemove.next = null;

        size--;
        return oldData;
    }

    /**
     * Helper method for {@link #remove(int)} that removes the current head of this list and sets
     * its next node as the new head.
     *
     * @return the data of the removed head
     */
    private E removeHead() {
        // From: N1 (head) -> N2 -> N3
        // To: N2 (head) -> N3
        Node<E> nodeToRemove = assertNotNull(head);
        E oldData = nodeToRemove.data;

        // If the new head is null, list has only one node. In this case, the tail becomes null.
        head = nodeToRemove.next;
        if (head == null) {
            tail = null;
        }

        nodeToRemove.data = null;
        nodeToRemove.next = null;

        size--;
        return oldData;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFirst(); // more efficient than default
        }
    }

    @Override
    public void reverse() {
        Node<E> previousNode = null;
        Node<E> node = head;
        while (node != null) {
            Node<E> nextNode = node.next;
            node.next = previousNode;
            previousNode = node;
            node = nextNode;
        }
        head = previousNode;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> currentNode = head;

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
