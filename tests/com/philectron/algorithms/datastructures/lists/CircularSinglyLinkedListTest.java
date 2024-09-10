package com.philectron.algorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.List;
import org.junit.jupiter.api.Test;

public class CircularSinglyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createList(Iterable<Integer> iterable) {
        return new CircularSinglyLinkedList<>(iterable);
    }

    @Override
    @Test
    public void reverseIterator_traversesListBackward() {
        assertThrows(UnsupportedOperationException.class,
                () -> new CircularSinglyLinkedList<>().reverseIterator());
    }

}
