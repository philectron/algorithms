package com.philectron.algorithms.datastructures.list;

public class CircularSinglyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new CircularSinglyLinkedList<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new CircularSinglyLinkedList<>();
    }

}
