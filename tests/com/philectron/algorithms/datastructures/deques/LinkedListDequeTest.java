package com.philectron.algorithms.datastructures.deques;

import com.philectron.algorithms.datastructures.interfaces.Deque;

public class LinkedListDequeTest extends DequeTestBase {

    @Override
    Deque<Integer> createDeque(Iterable<Integer> iterable) {
        return new LinkedListDeque<>(iterable);
    }

}
