package com.philectron.algorithms.datastructures.list;

public class DoublyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createList(Iterable<Integer> iterable) {
        return new DoublyLinkedList<>(iterable);
    }

}
