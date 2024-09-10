package com.philectron.algorithms.datastructures.lists;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.philectron.algorithms.logic.Assertion.assertElementIndex;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import com.philectron.algorithms.datastructures.interfaces.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularDoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> previous;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            this.next = this.previous = null;
        }
    }

    private Node<E> last;
    private int size;

    /**
     * Initializes an empty circular doubly linked list.
     */
    public CircularDoublyLinkedList() {
        this.last = null;
        this.size = 0;
    }

    /**
     * Initializes a circular doubly linked list with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this list
     *
     * @throws NullPointerException if {@code iterable} is {@code null}
     */
    public CircularDoublyLinkedList(Iterable<? extends E> iterable) {
        this();
        addAll(checkNotNull(iterable));
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Traverses through this list to the node at {@code index}.
     *
     * @param index the index whose node will be returned
     *
     * @return the corresponding node at {@code index}, guaranteed to be non-null
     */
    private Node<E> nodeAt(int index) {
        assertElementIndex(index, size);
        Node<E> node = assertNotNull(last);
        if (index < size / 2) {
            for (int i = -1; i < index; i++) {
                node = assertNotNull(node.next);
            }
        } else {
            for (int i = size - 1; i > index; i--) {
                node = assertNotNull(node.previous);
            }
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

        // For all other positions, traverse this list to the node right before the insert position.
        Node<E> previousNode = nodeAt(position - 1);

        // From: N1 (previousNode) <-> N2 (nextNode)
        // To: N1 (previousNode) <-> newNode <-> N2 (nextNode)
        Node<E> nextNode = assertNotNull(previousNode.next);
        newNode.previous = previousNode;
        newNode.next = nextNode;
        nextNode.previous = previousNode.next = newNode;
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
            // From: Nx (last) <-> N1 (nextNode) <-> N2
            // To: Nx (last) <-> newNode <-> N1 (nextNode) <-> N2
            Node<E> nextNode = assertNotNull(last.next);
            newNode.previous = last;
            newNode.next = nextNode;
            nextNode.previous = last.next = newNode;
        } else {
            // If this list is empty, the new node points to itself.
            // Setting last node will be done outside of this method.
            newNode.next = newNode.previous = newNode;
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
        int lastIndex = size - 1;

        Iterator<E> rit = reverseIterator();
        while (rit.hasNext()) {
            E currentData = rit.next();
            if (element == null ? currentData == null : element.equals(currentData)) {
                return lastIndex;
            }
            lastIndex--;
        }

        return -1;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, size);

        // If remove at the front, the last node is the previous node.
        Node<E> previousNode = index == 0 ? assertNotNull(last) : nodeAt(index - 1);
        Node<E> nodeToRemove = assertNotNull(previousNode.next);
        E oldData = nodeToRemove.data;

        // From: N1 (previousNode) <-> N2 (nodeToRemove) <-> N3 (nextNode)
        // To: N1 (previousNode) <-> N3 (nextNode)
        Node<E> nextNode = assertNotNull(nodeToRemove.next);
        previousNode.next = nextNode;
        nextNode.previous = previousNode;
        if (index == size - 1) {
            last = previousNode;
        }

        // Cleanup to help with garbage collection
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

    @Override
    public void reverse() {
        if (last == null) {
            return;
        }

        Node<E> currentNode = last.next;
        boolean isLastIteration = false;
        while (!isLastIteration) {
            if (currentNode == last) {
                isLastIteration = true;
            }
            Node<E> previousNode = assertNotNull(currentNode.previous);
            Node<E> nextNode = assertNotNull(currentNode.next);
            currentNode.previous = nextNode;
            currentNode.next = previousNode;
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
                E currentData = currentNode.data;
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
        return new Iterator<>() {
            private Node<E> currentNode = last;
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
                E currentData = currentNode.data;
                if (currentNode.previous == last) {
                    isLastIteration = true;
                }
                currentNode = currentNode.previous;
                return currentData;
            }
        };
    }

}
