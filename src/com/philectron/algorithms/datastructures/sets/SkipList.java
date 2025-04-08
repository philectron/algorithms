package com.philectron.algorithms.datastructures.sets;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import com.philectron.algorithms.datastructures.interfaces.OrderedSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class SkipList<E extends Comparable<E>> implements OrderedSet<E> {

    private static final int MAX_LEVEL = 4;

    private final Random random;
    private final Node<E> head; // sentinel node that does not hold actual list data
    private int level; // the height of the tallest node in this list
    private int size;

    /**
     * Initializes an empty skip list.
     */
    public SkipList() {
        random = new Random();
        head = new Node<>(null, MAX_LEVEL);
        level = 0;
        size = 0;
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
        addAll(iterable);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Retrieves the element at index {@code index} of this skip list.
     *
     * @param index the index of the element to return
     *
     * @return the element at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
     *                                   {@link #size()}, or if this skip list {@link #isEmpty()}
     * @see #getFirst()
     * @see #getLast()
     */
    public E get(int index) {
        checkElementIndex(index, size);

        // Head has width of 1, which does not count, so the node at index i is always i + 1 units
        // of width away from the head.
        int remainingDistance = index + 1;

        Node<E> node = head;
        for (int i = level; i >= 0; --i) {
            // If the width of the current node is too large, then the step is too far.
            while (node.width[i] <= remainingDistance) {
                // For every step forward, update the remaining distance.
                remainingDistance -= node.width[i];
                node = node.forward.get(i);
            }
        }

        return node.data;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Skip list is empty");
        }
        return get(0);
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Skip list is empty");
        }
        return get(size - 1);
    }

    @Override
    public boolean add(E element) {
        checkNotNull(element);

        // Create a new node for the element with a random level ranging from 0 to max level.
        Node<E> newNode = new Node<>(element, randomLevel());

        List<Node<E>> previousNodes = findPreviousNodes(element);

        // At level 0, if the previous node's next node has the same value as the one to be
        // inserted, then this is a duplicate insertion, in which case we will fast-return.
        if (previousNodes.getFirst().forward.getFirst() != null && element.equals(
                previousNodes.getFirst().forward.getFirst().data)) {
            return false; // list was unmodified
        }

        // Perform reference manipulation to put the new node in place.
        for (int i = 0; i <= newNode.level; ++i) {
            newNode.forward.set(i, previousNodes.get(i).forward.get(i));
            previousNodes.get(i).forward.set(i, newNode);
        }

        // For each level, calculate the new node's width and its previous node's width.
        // Skipping level 0 since all nodes there form a normal linked list and have widths of 1.
        for (int i = 1; i < previousNodes.size(); ++i) {
            updateWidths(i, newNode, previousNodes.get(i));
        }

        // Update this list's level if needed.
        level = Math.max(level, newNode.level);

        ++size;
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
        while (random.nextBoolean() && nodeLevel < MAX_LEVEL) {
            ++nodeLevel;
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
     * (inclusive), if any, the previous node is default to {@link #head}.
     * </p>
     *
     * @param value the value to be compared against the nodes on each level of this list
     *
     * @return a list of existing nodes of this list, where the {@code i}-th element is the
     *         reference to the largest node on level {@code i} that is less than {@code value}
     */
    private List<Node<E>> findPreviousNodes(E value) {
        assertNotNull(value);

        // Store the previous node for each level from 0 to max level, default to head.
        List<Node<E>> previousNodes = new ArrayList<>(Collections.nCopies(MAX_LEVEL + 1, head));

        // Start at this list's level and the head node and traverse right then down.
        // For each level, store to the node right before the value.
        Node<E> node = head;
        for (int i = level; i >= 0; --i) {
            while (node.forward.get(i) != null && value.compareTo(node.forward.get(i).data) > 0) {
                node = node.forward.get(i);
            }
            previousNodes.set(i, node);
        }

        return previousNodes;
    }

    /**
     * After the new node has been inserted at its correct position in this list, this method
     * updates the width of the previous node and calculates the width of the new node on the
     * current level.
     *
     * @param currentLevel the current level to update the widths of the previous and new nodes
     * @param newNode      the new node already inserted at its correct position in this list
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
        while (previous != newNode) {
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

    public int indexOf(E element) {
        checkNotNull(element);

        Node<E> node = head;
        int index = -1;
        for (int i = level; i >= 0; --i) {
            while (node.forward.get(i) != null
                    && element.compareTo(node.forward.get(i).data) >= 0) {
                // For every step forward, update the distance from head.
                index += node.width[i];
                node = node.forward.get(i);
                if (element.equals(node.data)) {
                    return index;
                }
            }
        }

        return -1; // not found
    }

    public int lastIndexOf(E element) {
        return indexOf(element);
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    /**
     * Removes the element at index {@code index} of this set. Shifts any subsequent elements to the
     * left (subtracts 1 from their indices).
     *
     * @param index the index of the element to be removed
     *
     * @return the element previously at {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
     *                                   {@link #size()}
     * @see #remove(E)
     */
    public E remove(int index) {
        checkElementIndex(index, size);

        // Store the previous node for each level from 0 to max level, default to head.
        List<Node<E>> previousNodes = new ArrayList<>(Collections.nCopies(MAX_LEVEL + 1, head));

        // Head has width of 1, which does not count, so the node at index i is always i + 1 units
        // of width away from the head.
        int remainingDistance = index + 1;

        // Start at this list's level and the head node and traverse right then down.
        // For each level, store to the node right before the value.
        Node<E> node = head;
        for (int i = level; i >= 0; --i) {
            // If the width of the current node is too large, then the step is too far.
            while (node.width[i] < remainingDistance) {
                // For every step forward, update the remaining distance.
                remainingDistance -= node.width[i];
                node = node.forward.get(i);
            }
            previousNodes.set(i, node);
        }

        return removeAfter(previousNodes);
    }

    @Override
    public boolean remove(E element) {
        checkNotNull(element);

        // Store the previous node for each level from 0 to max level, default to head.
        List<Node<E>> previousNodes = findPreviousNodes(element);

        // At level 0, if the previous node's next node is null or has a different value from the
        // one to be removed, then the element does not exist in this list, in which case we will
        // fast-return.
        if (previousNodes.getFirst().forward.getFirst() == null || !element.equals(
                previousNodes.getFirst().forward.getFirst().data)) {
            return false; // list was unmodified
        }

        removeAfter(previousNodes);

        return true; // list was modified
    }

    /**
     * Given a list of nodes on each level of this list starting from zero to {@link #MAX_LEVEL},
     * identifies the node to be removed and then removes that node from this list and updates the
     * size.
     *
     * @param previousNodes the list of references to the previous node on each level
     *
     * @return the value of the removed node
     */
    private E removeAfter(List<Node<E>> previousNodes) {
        // The node to remove is always the next node after the previous node on level 0.
        Node<E> nodeToRemove = previousNodes.getFirst().forward.getFirst();
        E oldData = nodeToRemove.data;

        // Perform reference manipulation to detach the node.
        for (int i = 0; i <= nodeToRemove.level; ++i) {
            previousNodes.get(i).forward.set(i, nodeToRemove.forward.get(i));
            // Combine its widths with the previous node, minus 1 because we are removing 1 node.
            previousNodes.get(i).width[i] += nodeToRemove.width[i] - 1;
        }

        // For the remaining previous nodes on levels above the node, update their widths as well.
        for (int i = nodeToRemove.level + 1; i <= MAX_LEVEL; ++i) {
            previousNodes.get(i).width[i]--;
        }

        --size;
        return oldData;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            remove(0);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> currentNode = head.forward.getFirst();

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
                currentNode = currentNode.forward.getFirst();
                return currentData;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = MAX_LEVEL; i >= 0; --i) {
            for (Node<E> node = head; node != null; node = node.forward.get(i)) {
                sb.append(node == head ? "H" : node.data.toString());
                sb.append("(").append(node.width[i]).append(")");
                sb.append(String.join("", Collections.nCopies(node.width[i], "\t")));
            }
            sb.append("NULL\n");
        }

        return sb.toString();
    }

    private static class Node<E> {
        private final E data;
        private final int level; // how tall this node is in the list
        private final List<Node<E>> forward; // the next node on level i
        private final int[] width; // the number of bottom-level nodes being skipped on level i

        private Node(E data, int level) {
            this.data = data;
            this.level = level;

            // Since we need to reference levels [0..level], we need to init +1 for the size.
            forward = new ArrayList<>(Collections.nCopies(level + 1, null));
            width = new int[level + 1];

            // By default, the widths should be 1.
            Arrays.fill(width, 1);
        }
    }

}
