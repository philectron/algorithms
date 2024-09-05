package com.philectron.algorithms.datastructures.list;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
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
        private E data;
        private int level; // how tall this node is in the list
        private java.util.List<Node<E>> forward; // the next node on level i
        private int[] width; // the number of bottom-level nodes being skipped on level i

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

    private Node<E> header; // sentinel node that does not hold actual list data
    private int level; // the height of the tallest node in this list
    private int size;

    /**
     * Initializes an empty skip list.
     */
    public SkipList() {
        this.random = new Random();
        this.header = new Node<>(null, MAX_LEVEL);
        this.level = 0;
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
        return size;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index, this.size);

        int distanceFromHeader = 0;
        int currentIndex = -1;
        Node<E> node = assertNotNull(this.header);
        for (int i = this.level; i >= 0 && currentIndex != index; i--) {
            while (distanceFromHeader + node.width[i] - 1 <= index) {
                currentIndex = distanceFromHeader + node.width[i] - 1;
                distanceFromHeader += node.width[i];
                node = assertNotNull(node.forward.get(i));
            }
        }

        return assertNotNull(node.data);
    }

    @Override
    public void add(E element) {
        checkNotNull(element);

        // Create a new node for the element with a random level ranging from 0 to max level.
        Node<E> newNode = new Node<>(element, randomLevel());

        // Find previous nodes and perform reference manipulation to put the new node in place.
        java.util.List<Node<E>> previousNodes = findPreviousNodes(newNode);
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
        while (random.nextBoolean() && nodeLevel < MAX_LEVEL) {
            nodeLevel++;
        }
        return nodeLevel;
    }

    /**
     * Traverses through this list to retrieve the node right before the insert position of the new
     * node. In cases where the previous node does not exist (i.e. on the levels above the new
     * node's level), the previous node is default to the header node.
     *
     * @param newNode the new node to be inserted into its correct position in this list.
     *
     * @return a list of existing nodes in this list that are right before the new node, for
     *         reference manipulation and width update later
     */
    private java.util.List<Node<E>> findPreviousNodes(Node<E> newNode) {
        E newElement = assertNotNull(assertNotNull(newNode).data);

        // Store the previous node for each level, default to header.
        java.util.List<Node<E>> previousNodes =
                new ArrayList<>(Collections.nCopies(MAX_LEVEL + 1, this.header));

        // Start at this list's level and the header node and traverse right then down.
        // For each level, store to the node right before the insert position.
        Node<E> node = assertNotNull(this.header);
        for (int i = this.level; i >= 0; i--) {
            // Use element >= previousNode to ensure duplicates are added toward the back.
            while (node.forward.get(i) != null
                    && newElement.compareTo(node.forward.get(i).data) >= 0) {
                node = node.forward.get(i);
            }
            previousNodes.set(i, node);
        }

        // Any remaining references in the previous nodes list are default to the header node,
        // so we don't need to set them to anything else (even when the new node's level is higher
        // than this list's level, in which case the previous node for the extra levels will be the
        // header node).
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
        while (assertNotNull(previous) != newNode) {
            // Always use the previous level L - 1 for traversing and calculating sum of widths.
            newPreviousWidth += previous.width[currentLevel - 1];
            previous = previous.forward.get(currentLevel - 1);
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

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public int lastIndexOf(E element) {
        checkNotNull(element);

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastIndexOf'");
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, this.size);

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
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
            private Node<E> currentNode = assertNotNull(header).forward.get(0);

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
