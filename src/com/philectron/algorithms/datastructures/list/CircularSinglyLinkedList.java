package com.philectron.algorithms.datastructures.list;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.philectron.algorithms.logic.Assertion.assertElementIndex;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularSinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> last;
    private int size;

    /**
     * Initializes an empty circular singly linked list.
     */
    public CircularSinglyLinkedList() {
        this.last = null;
        this.size = 0;
    }

    /**
     * Initializes a circular singly linked list with all elements copied from {@code list}.
     *
     * @param list the {@link java.util.List} whose elements are to be copied to this list
     *
     * @throws NullPointerException if {@code list} is {@code null}
     */
    public CircularSinglyLinkedList(java.util.List<? extends E> list) {
        this();
        addAll(checkNotNull(list));
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
        assertNotNull(last);

        // Quickly return the last node if the index points there.
        if (index == size - 1) {
            return last;
        }

        Node<E> node = last;
        for (int i = -1; i < index; i++) {
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
    public void add(int position, E element) {
        checkPositionIndex(position, size);

        Node<E> newNode = new Node<>(element);

        // Since this list is circular, adding to the front is the same as adding to the back.
        if (position == 0 || position == size) {
            addAfterLast(newNode);
            // If adding to the back, the new node also becomes the last node.
            if (position == size) {
                last = newNode;
            }
            size++;
            return;
        }

        // For all other positions, traverse the list to the node right before the insert position.
        Node<E> previousNode = nodeAt(position - 1);

        // From: N1 (previousNode) -> N2
        // To: N1 (previousNode) -> newNode -> N2
        newNode.next = previousNode.next;
        previousNode.next = newNode;
        size++;
    }

    /**
     * Helper method for {@link #add(int, E)} that inserts {@code newNode} after the last node of
     * this list.
     *
     * @param newNode the node to be inserted after {@link #last}
     */
    private void addAfterLast(Node<E> newNode) {
        assertNotNull(newNode);
        if (last != null) {
            // From: Nx (last) -> N1 -> N2
            // To: Nx (last) -> newNode -> N1 -> N2
            newNode.next = last.next;
            last.next = newNode;
        } else {
            // If this list is empty, the new node becomes the last node and points to itself.
            newNode.next = newNode;
        }
    }

    @Override
    public void addAll(java.util.List<? extends E> list) {
        checkNotNull(list);
        for (E element : list) {
            addBack(element);
        }
    }

    @Override
    public int indexOf(E element) {
        int firstIndex = 0;

        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E currentData = it.next();
            if (element == null ? currentData == null : element.equals(currentData)) {
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

        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E currentData = it.next();
            if (element == null ? currentData == null : element.equals(currentData)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
        }

        return lastIndex;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, size);

        // If remove at the front, the last node is the previous node.
        Node<E> previousNode = index == 0 ? assertNotNull(last) : nodeAt(index - 1);
        Node<E> nodeToRemove = assertNotNull(previousNode.next);
        E oldData = nodeToRemove.data;

        // From: N1 (previousNode) -> N2 (nodeToRemove) -> N3
        // To: N1 (previousNode) -> N3
        previousNode.next = nodeToRemove.next;
        if (index == size - 1) {
            last = previousNode;
        }

        // Cleanup to help garbage collection.
        nodeToRemove.data = null;
        nodeToRemove.next = null;

        size--;
        return oldData;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFront();
        }
    }

    @Override
    public void reverse() {
        if (last == null) {
            return;
        }

        Node<E> previousNode = last;
        Node<E> currentNode = assertNotNull(previousNode.next);
        boolean isLastIteration = false;
        while (!isLastIteration) {
            if (currentNode == last) {
                isLastIteration = true;
            }
            Node<E> nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }

        last = currentNode;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> currentNode = last != null ? last.next : null;
            private boolean isLastIteration = false;

            @Override
            public boolean hasNext() {
                return currentNode != null && !isLastIteration;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Iterator has no more elements");
                }

                E currentData = assertNotNull(currentNode).data;
                if (currentNode == last) {
                    isLastIteration = true;
                }
                currentNode = currentNode.next;

                return currentData;
            }
        };
    }

    @Override
    public Iterator<E> reverseIterator() {
        throw new UnsupportedOperationException(
                this.getClass().getSimpleName() + " does not support backward traversal");
    }

}
