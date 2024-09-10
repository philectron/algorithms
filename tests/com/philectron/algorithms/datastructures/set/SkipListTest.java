package com.philectron.algorithms.datastructures.set;

public class SkipListTest extends SortedListTestBase {

    @Override
    SortedList<Integer> createSortedList(Iterable<Integer> iterable) {
        return new SkipList<>(iterable);
    }

}
