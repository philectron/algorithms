package com.philectron.algorithms.datastructures.list;

public class SinglyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new SinglyLinkedList<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new SinglyLinkedList<>();
    }

}
