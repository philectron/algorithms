package com.philectron.algorithms.datastructures.list;

public class SkipListTest extends SortedListTestBase {

    @Override
    SortedList<Integer> createSortedList(Iterable<Integer> iterable) {
        return new SkipList<>(iterable);
    }

}
