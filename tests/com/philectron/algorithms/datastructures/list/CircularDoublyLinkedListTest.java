package com.philectron.algorithms.datastructures.list;

public class CircularDoublyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createList(Iterable<Integer> iterable) {
        return new CircularDoublyLinkedList<>(iterable);
    }

}
