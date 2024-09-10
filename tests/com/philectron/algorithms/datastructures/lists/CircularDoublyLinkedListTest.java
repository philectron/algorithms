package com.philectron.algorithms.datastructures.lists;

import com.philectron.algorithms.datastructures.interfaces.List;

public class CircularDoublyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createList(Iterable<Integer> iterable) {
        return new CircularDoublyLinkedList<>(iterable);
    }

}
