package com.philectron.algorithms.datastructures.sets;

import com.philectron.algorithms.datastructures.interfaces.OrderedSet;

public class SkipListTest extends SortedListTestBase {

    @Override
    OrderedSet<Integer> createSortedList(Iterable<Integer> iterable) {
        return new SkipList<>(iterable);
    }

}
