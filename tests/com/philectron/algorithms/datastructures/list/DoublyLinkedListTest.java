package com.philectron.algorithms.datastructures.list;

public class DoublyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new DoublyLinkedList<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new DoublyLinkedList<>();
    }

}
