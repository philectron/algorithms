package com.philectron.algorithms.datastructures.list;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new SinglyLinkedList<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new SinglyLinkedList<>();
    }

    @Override
    @Test
    public void reverseIterator_returnsBackwardIterator() {
        assertThrows(UnsupportedOperationException.class,
                () -> new SinglyLinkedList<>().reverseIterator());
    }

}
