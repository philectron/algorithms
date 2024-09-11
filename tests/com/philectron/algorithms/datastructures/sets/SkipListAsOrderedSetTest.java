package com.philectron.algorithms.datastructures.sets;

import com.philectron.algorithms.datastructures.interfaces.OrderedSet;

public class SkipListAsOrderedSetTest extends OrderedSetTestBase {

    @Override
    OrderedSet<Integer> createOrderedSet(Iterable<Integer> iterable) {
        return new SkipList<>(iterable);
    }

}
