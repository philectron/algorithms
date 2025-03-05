package com.philectron.algorithms.datastructures.lists;

import static com.google.common.base.Preconditions.checkElementIndex;
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
        addAll(iterable);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index, size);
        return nodeAt(index).data;
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
        for (int i = 0; i < index; ++i) {
            node = assertNotNull(node.next);
        }

        return node;
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
    public void add(int position, E element) {
        checkPositionIndex(position, size);

        Node<E> newNode = new Node<>(element);

        // If insert at the head, the new node becomes the new head.
        // This branch handles empty list.
        if (position == 0) {
            addHead(newNode);
            return;
        }

        // If insert at the tail, the new node becomes the new tail.
        if (position == size) {
            addTail(newNode);
            return;
        }

        // For all other positions, traverse the list to the node right before the insert position.
        Node<E> previousNode = nodeAt(position - 1);

        // From: N1 (previousNode) -> N2
        // To: N1 (previousNode) -> newNode -> N2
        newNode.next = previousNode.next;
        previousNode.next = newNode;

        ++size;
    }

    /**
     * Inserts {@code newNode} as the new head of this list.
     *
     * @param newNode the node to be inserted and made the new head
     */
    private void addHead(Node<E> newNode) {
        assertNotNull(newNode);

        // From: N1 (head) -> N2
        // To: newNode (head) -> N1 -> N2
        newNode.next = head;

        // If head is null, list is empty. In this case, the new node also becomes the tail.
        if (head == null) {
            tail = newNode;
        }

        head = newNode;

        ++size;
    }

    /**
     * Inserts the {@code newNode} as the new tail of this list.
     *
     * @param newNode the node to be inserted and made the new tail
     */
    private void addTail(Node<E> newNode) {
        assertNotNull(newNode);

        // From: N1 -> N2 (tail)
        // To: N1 -> N2 -> newNode (tail)
        // Empty list is already handled above, so the tail is never null here.
        assertNotNull(tail);
        tail.next = newNode;
        tail = newNode;

        ++size;
    }

    @Override
    public int indexOf(E element) {
        int firstIndex = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (element == null ? node.data == null : element.equals(node.data)) {
                return firstIndex;
            }
            ++firstIndex;
        }
        return -1; // not found
    }

    @Override
    public int lastIndexOf(E element) {
        int lastIndex = -1; // not found by default
        int currentIndex = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (element == null ? node.data == null : element.equals(node.data)) {
                lastIndex = currentIndex;
            }
            ++currentIndex;
        }
        return lastIndex;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, size);
        // The head does not have a previous node.
        return removeAfterNode(index == 0 ? null : nodeAt(index - 1));
    }

    @Override
    public boolean remove(E element) {
        Node<E> currentNode = head;
        Node<E> previousNode = null;

        while (currentNode != null) {
            if (element == null ? currentNode.data == null : element.equals(currentNode.data)) {
                removeAfterNode(previousNode);
                return true; // element found, list was modified
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        return false; // element not found, list was unmodified
    }

    /**
     * Removes the node after {@code previousNode} from this list and performs reference
     * manipulation to connect its previous and next nodes.
     *
     * @param previousNode
     *
     * @return the data of the removed node
     */
    private E removeAfterNode(Node<E> previousNode) {
        // If remove at the head (without previous node), the next node becomes the new head.
        // This branch handles singleton list.
        if (previousNode == null) {
            return removeHead();
        }

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

        --size;
        return oldData;
    }

    /**
     * Removes the current head of this list and sets its next node as the new head.
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

        // Cleanup to help with garbage collection.
        nodeToRemove.data = null;
        nodeToRemove.next = null;

        --size;
        return oldData;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFirst();
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
