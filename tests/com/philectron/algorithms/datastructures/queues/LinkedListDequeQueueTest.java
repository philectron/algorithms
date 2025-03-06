package com.philectron.algorithms.datastructures.queues;

import com.philectron.algorithms.datastructures.interfaces.Queue;

public class LinkedListDequeQueueTest extends QueueTestBase {

    @Override
    Queue<Integer> createQueue(Iterable<Integer> iterable) {
        return new LinkedListDeque<>(iterable);
    }

}
