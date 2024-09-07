package com.philectron.algorithms.datastructures.list;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertElementIndex;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class SkipList<E extends Comparable<E>> implements SortedList<E> {

    private static final int MAX_LEVEL = 4;

    private static class Node<E> {
        private final E data;
        private final int level; // how tall this node is in the list
        private final java.util.List<Node<E>> forward; // the next node on level i
        private final int[] width; // the number of bottom-level nodes being skipped on level i

        private Node(E data, int level) {
            this.data = data;
            this.level = level;

            // Since we need to reference levels [0..level], we need to init +1 for the size.
            this.forward = new ArrayList<>(Collections.nCopies(level + 1, null));
            this.width = new int[level + 1];

            // By default, the widths should be 1.
            Arrays.fill(this.width, 1);
        }
    }

    private final Random random;
    private final Node<E> header; // sentinel node that does not hold actual list data
    private int level; // the height of the tallest node in this list
    private int size;

    /**
     * Initializes an empty skip list.
     */
    public SkipList() {
        this.random = new Random();
        this.header = new Node<>(null, MAX_LEVEL);
        this.level = 0;
        this.size = 0;
    }

    /**
     * Initializes a skip list with all elements copied from {@code iterable}.
     *
     * @param iterable the {@link Iterable} whose elements are to be copied to this list
     *
     * @throws NullPointerException if {@code iterable} is {@code null}
     */
    public SkipList(Iterable<? extends E> iterable) {
        this();
        addAll(checkNotNull(iterable));
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index, this.size);

        // Header has width of 1, which does not count, so the node at index i is always i + 1 units
        // of width away from the header.
        int remainingDistance = index + 1;

        Node<E> node = assertNotNull(this.header);
        for (int i = this.level; i >= 0; i--) {
            // If the width of the current node is too large, then the step is too far.
            while (node.width[i] <= remainingDistance) {
                // For every step forward, update the remaining distance.
                remainingDistance -= node.width[i];
                node = assertNotNull(node.forward.get(i));
            }
        }

        return assertNotNull(node.data);
    }

    @Override
    public boolean add(E element) {
        checkNotNull(element);

        // Create a new node for the element with a random level ranging from 0 to max level.
        Node<E> newNode = new Node<>(element, randomLevel());

        java.util.List<Node<E>> previousNodes = findPreviousNodes(element);

        // At level 0, if the previous node's next node has the same value as the one to be
        // inserted, then this is a duplicate insertion, in which case we will fast-return.
        if (previousNodes.get(0).forward.get(0) != null
                && element.equals(previousNodes.get(0).forward.get(0).data)) {
            return false; // list was unmodified
        }

        // Perform reference manipulation to put the new node in place.
        for (int i = 0; i <= newNode.level; i++) {
            newNode.forward.set(i, previousNodes.get(i).forward.get(i));
            previousNodes.get(i).forward.set(i, newNode);
        }

        // For each level, calculate the new node's width and its previous node's width.
        // Skipping level 0 since all nodes there form a normal linked list and have widths of 1.
        for (int i = 1; i < previousNodes.size(); i++) {
            updateWidths(i, newNode, previousNodes.get(i));
        }

        // Update this list's level if needed.
        this.level = Math.max(this.level, newNode.level);

        this.size++;
        return true; // list was modified
    }

    /**
     * Uses coin tosses to determine how many levels a new node should have, capped at
     * {@link #MAX_LEVEL}. The new node's level may exceed the list's level, in which case the
     * list's level and the previous node references will be updated accordingly.
     *
     * @return a level number between zero (inclusive) and the maximum level (inclusive)
     */
    private int randomLevel() {
        int nodeLevel = 0;
        while (this.random.nextBoolean() && nodeLevel < MAX_LEVEL) {
            nodeLevel++;
        }
        return nodeLevel;
    }

    /**
     * Traverses through this list to build a list of nodes, each of which is the reference to the
     * largest node that is less than {@code value} (in other words, the immediate "previous node")
     * on each of this list's levels.
     *
     * <p>
     * For the remaining levels between {@link #level} (exclusive) and {@link #MAX_LEVEL}
     * (inclusive), if any, the previous node is default to {@link #header}.
     * </p>
     *
     * @param value the value to be compared against the nodes on each level of this list
     *
     * @return a list of existing nodes of this list, where the {@code i}th element is the reference
     *         to the largest node on level {@code i} that is less than {@code value}
     */
    private java.util.List<Node<E>> findPreviousNodes(E value) {
        assertNotNull(value);

        // Store the previous node for each level from 0 to max level, default to header.
        java.util.List<Node<E>> previousNodes =
                new ArrayList<>(Collections.nCopies(MAX_LEVEL + 1, this.header));

        // Start at this list's level and the header node and traverse right then down.
        // For each level, store to the node right before the value.
        Node<E> node = assertNotNull(this.header);
        for (int i = this.level; i >= 0; i--) {
            while (node.forward.get(i) != null && value.compareTo(node.forward.get(i).data) > 0) {
                node = assertNotNull(node.forward.get(i));
            }
            previousNodes.set(i, node);
        }

        return previousNodes;
    }

    /**
     * After the new node has been inserted to its correct position in this list, this method
     * updates the width of the previous node and calculates the width of the new node on the
     * current level.
     *
     * @param currentLevel the current level to update the widths of the previous and new nodes
     * @param newNode the new node already inserted into its correct position in this list
     * @param previousNode the node right before the new node on the current level
     */
    private void updateWidths(int currentLevel, Node<E> newNode, Node<E> previousNode) {
        assertNotNull(newNode);
        assertNotNull(previousNode);
        assertElementIndex(currentLevel, previousNode.width.length);
        assertElementIndex(currentLevel - 1, previousNode.width.length);
        assertElementIndex(currentLevel, previousNode.forward.size());

        // For levels above the new node's level, we only need to add 1 to the previous node's
        // current width, because we know we have added 1 new node in the levels under it.
        if (currentLevel > newNode.level) {
            previousNode.width[currentLevel]++;
            return;
        }

        // If node B exists on level L, then it must exist on all the lower levels. So, the width
        // from node A to node B on level L is the sum of the widths on level L - 1, starting from
        // node A, going through all intermediate nodes (if any), and ending at node B.
        int newPreviousWidth = 0;
        // This traversal node will never be null and will always eventually lead to the new node.
        Node<E> previous = previousNode;
        while (previous != newNode) {
            // Always use the previous level L - 1 for traversing and calculating sum of widths.
            newPreviousWidth += previous.width[currentLevel - 1];
            previous = assertNotNull(previous.forward.get(currentLevel - 1));
        }

        // Update the previous node's width, but save the old value for later.
        final int oldPreviousWidth = previousNode.width[currentLevel];
        previousNode.width[currentLevel] = newPreviousWidth;

        // The previous node's width is now the distance between it and the new node.
        // So, the new node's width will be the previous node's old width minus the distance between
        // the previous node and the new node. This is similar to 3 points A, B, and C respectively
        // on the same line where the segment length BC = AC - AB. In this case, plus 1 because we
        // are adding 1 new node to the list.
        newNode.width[currentLevel] = oldPreviousWidth - newPreviousWidth + 1;
    }

    @Override
    public int indexOf(E element) {
        checkNotNull(element);

        Node<E> node = assertNotNull(this.header);
        int index = -1;
        for (int i = this.level; i >= 0; i--) {
            while (node.forward.get(i) != null
                    && element.compareTo(node.forward.get(i).data) >= 0) {
                // For every step forward, update the distance from header.
                index += node.width[i];
                node = assertNotNull(node.forward.get(i));
                if (element.equals(node.data)) {
                    return index;
                }
            }
        }

        return -1; // not found
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, this.size);

        // Store the previous node for each level from 0 to max level, default to header.
        java.util.List<Node<E>> previousNodes =
                new ArrayList<>(Collections.nCopies(MAX_LEVEL + 1, assertNotNull(this.header)));

        // Header has width of 1, which does not count, so the node at index i is always i + 1 units
        // of width away from the header.
        int remainingDistance = index + 1;

        // Start at this list's level and the header node and traverse right then down.
        // For each level, store to the node right before the value.
        Node<E> node = this.header;
        for (int i = this.level; i >= 0; i--) {
            // If the width of the current node is too large, then the step is too far.
            while (node.width[i] < remainingDistance) {
                // For every step forward, update the remaining distance.
                remainingDistance -= node.width[i];
                node = assertNotNull(node.forward.get(i));
            }
            previousNodes.set(i, node);
        }

        return remove(previousNodes);
    }

    @Override
    public boolean remove(E element) {
        checkNotNull(element);

        // Store the previous node for each level from 0 to max level, default to header.
        java.util.List<Node<E>> previousNodes = findPreviousNodes(element);

        // At level 0, if the previous node's next node is null or has a different value from the
        // one to be removed, then the element does not exist in this list, in which case we will
        // fast-return.
        if (previousNodes.get(0).forward.get(0) == null
                || !element.equals(previousNodes.get(0).forward.get(0).data)) {
            return false; // list was unmodified
        }

        remove(previousNodes);

        return true; // list was modified
    }

    private E remove(java.util.List<Node<E>> previousNodes) {
        // The node to remove is always the next node after the previous node on level 0.
        Node<E> nodeToRemove = assertNotNull(previousNodes.get(0).forward.get(0));
        E oldData = nodeToRemove.data;

        // Perform reference manipulation to detach the node.
        for (int i = 0; i <= nodeToRemove.level; i++) {
            previousNodes.get(i).forward.set(i, nodeToRemove.forward.get(i));
            // Combine its widths with the previous node, minus 1 because we are removing 1 node.
            previousNodes.get(i).width[i] += nodeToRemove.width[i] - 1;
        }

        // For the remaining previous nodes on levels above the node, update their widths as well.
        for (int i = nodeToRemove.level + 1; i <= MAX_LEVEL; i++) {
            previousNodes.get(i).width[i]--;
        }

        this.size--;
        return oldData;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFront();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> currentNode = assertNotNull(SkipList.this.header).forward.get(0);

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Iterator has no more elements");
                }
                E currentData = assertNotNull(currentNode.data);
                currentNode = currentNode.forward.get(0);
                return currentData;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = MAX_LEVEL; i >= 0; i--) {
            for (Node<E> node = this.header; node != null; node = node.forward.get(i)) {
                sb.append(node == this.header ? "H" : node.data.toString());
                sb.append("(" + node.width[i] + ")");
                sb.append(String.join("", Collections.nCopies(node.width[i], "\t")));
            }
            sb.append("NULL\n");
        }

        return sb.toString();
    }

}
