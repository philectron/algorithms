package com.philectron.algorithms.datastructures.list;

public class DynamicArrayTest extends ListTestBase {

    @Override
    List<Integer> createList(Iterable<Integer> iterable) {
        return new DynamicArray<>(iterable);
    }

}
