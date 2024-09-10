package com.philectron.algorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.List;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createList(Iterable<Integer> iterable) {
        return new SinglyLinkedList<>(iterable);
    }

    @Override
    @Test
    public void reverseIterator_traversesListBackward() {
        assertThrows(UnsupportedOperationException.class,
                () -> new SinglyLinkedList<>().reverseIterator());
    }

}
