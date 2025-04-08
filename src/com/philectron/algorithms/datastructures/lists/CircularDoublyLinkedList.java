package com.philectron.algorithms.datastructures.lists;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.philectron.algorithms.logic.Assertion.assertElementIndex;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import com.philectron.algorithms.datastructures.interfaces.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CircularDoublyLinkedList<E> implements List<E> {

    private Node<E> last;
    private int size;

    /**
     * Initializes an empty circular doubly linked list.
     */
    public CircularDoublyLinkedList() {
        last = null;
        size = 0;
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
            for (int i = -1; i < index; ++i) {
                node = assertNotNull(node.next);
            }
        } else {
            for (int i = size - 1; i > index; --i) {
                node = assertNotNull(node.previous);
            }
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
    public boolean add(int position, E element) {
        checkPositionIndex(position, size);

        Node<E> newNode = new Node<>(element);

        // Since this list is circular, adding to the start is the same as adding to the end.
        if (position == 0 || position == size) {
            addAfterLast(newNode, position == size);
            return true;
        }

        // For all other positions, traverse this list to the node right before the insert position.
        Node<E> previousNode = nodeAt(position - 1);

        // From: N1 (previousNode) <-> N2 (nextNode)
        // To: N1 (previousNode) <-> newNode <-> N2 (nextNode)
        Node<E> nextNode = assertNotNull(previousNode.next);
        newNode.previous = previousNode;
        newNode.next = nextNode;
        nextNode.previous = previousNode.next = newNode;

        ++size;
        return true;
    }

    /**
     * Inserts {@code newNode} after the last node of this list.
     *
     * @param newNode   the node to be inserted after {@link #last}
     * @param isAddLast whether this insertion adds the new node as the first or the last node
     */
    private void addAfterLast(Node<E> newNode, boolean isAddLast) {
        assertNotNull(newNode);

        if (last != null) {
            // From: Nx (last) <-> N1 (nextNode) <-> N2
            // To: Nx (last) <-> newNode <-> N1 (nextNode) <-> N2
            Node<E> nextNode = assertNotNull(last.next);
            newNode.previous = last;
            newNode.next = nextNode;
            nextNode.previous = last.next = newNode;
        } else {
            // If this list is empty, the new node points next and previous to itself.
            newNode.next = newNode.previous = newNode;
        }

        // If adding to the end, the new node also becomes the last node.
        if (isAddLast) {
            last = newNode;
        }

        ++size;
    }

    @Override
    public int indexOf(E element) {
        int firstIndex = 0;

        for (E currentData : this) {
            if (Objects.equals(element, currentData)) {
                return firstIndex;
            }
            ++firstIndex;
        }

        return -1; // not found
    }

    @Override
    public int lastIndexOf(E element) {
        int lastIndex = size - 1;

        boolean isLastIteration = false;
        Node<E> currentNode = last;
        while (currentNode != null && !isLastIteration) {
            if (Objects.equals(element, currentNode.data)) {
                return lastIndex;
            }
            --lastIndex;
            if (currentNode == last.next) {
                isLastIteration = true;
            }
            currentNode = currentNode.previous;
        }

        return -1; // not found
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, size);
        return removeNode(nodeAt(index));
    }

    @Override
    public boolean remove(E element) {
        Node<E> currentNode = last != null ? last.next : null;
        boolean isLastIteration = false;

        while (currentNode != null && !isLastIteration) {
            if (Objects.equals(element, currentNode.data)) {
                removeNode(currentNode);
                return true; // element found, list was modified
            }

            if (currentNode == last) {
                isLastIteration = true;
            }
            currentNode = currentNode.next;
        }

        return false; // element not found, list was unmodified
    }

    /**
     * Removes {@code nodeToRemove} from this list and performs reference manipulation to connect
     * its previous and next nodes.
     *
     * @param nodeToRemove the node to be removed from this list
     *
     * @return the data of the removed node
     */
    private E removeNode(Node<E> nodeToRemove) {
        assertNotNull(nodeToRemove);

        E oldData = nodeToRemove.data;

        // From: N1 (previousNode) <-> N2 (nodeToRemove) <-> N3 (nextNode)
        // To: N1 (previousNode) <-> N3 (nextNode)
        Node<E> previousNode = assertNotNull(nodeToRemove.previous);
        Node<E> nextNode = assertNotNull(nodeToRemove.next);
        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        // Update the last node reference if needed.
        if (nodeToRemove == last) {
            last = previousNode;
        }

        // Cleanup to help with garbage collection
        nodeToRemove.data = null;
        nodeToRemove.next = nodeToRemove.previous = null;

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

    private static class Node<E> {
        private E data;
        private Node<E> previous;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            next = previous = null;
        }
    }

}
