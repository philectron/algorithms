package com.philectron.algorithms.datastructures.deques;

import com.philectron.algorithms.datastructures.interfaces.Deque;

public class LinkedDequeTest extends DequeTestBase {

    @Override
    Deque<Integer> createDeque(Iterable<Integer> iterable) {
        return new LinkedDeque<>(iterable);
    }

}
