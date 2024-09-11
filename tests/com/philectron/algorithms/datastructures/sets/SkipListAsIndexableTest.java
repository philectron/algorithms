package com.philectron.algorithms.datastructures.sets;

import com.philectron.algorithms.datastructures.interfaces.Indexable;

public class SkipListAsIndexableTest extends IndexableTestBase {

    @Override
    Indexable<Integer> createIndexable(Iterable<Integer> iterable) {
        return new SkipList<>(iterable);
    }

}
