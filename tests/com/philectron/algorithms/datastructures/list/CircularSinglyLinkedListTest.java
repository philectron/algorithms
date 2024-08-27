package com.philectron.algorithms.datastructures.list;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CircularSinglyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new CircularSinglyLinkedList<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new CircularSinglyLinkedList<>();
    }

    @Override
    @Test
    public void reverseIterator_returnsBackwardIterator() {
        assertThrows(UnsupportedOperationException.class,
                () -> new CircularSinglyLinkedList<>().reverseIterator());
    }

}
