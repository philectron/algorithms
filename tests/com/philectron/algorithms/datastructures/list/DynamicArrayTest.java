package com.philectron.algorithms.datastructures.list;

public class DynamicArrayTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new DynamicArray<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new DynamicArray<>();
    }

}
