package com.philectron.algorithms.datastructures.queues;

import com.philectron.algorithms.datastructures.interfaces.Queue;

public class LinkedQueueTest extends QueueTestBase {

    @Override
    Queue<Integer> createQueue(Iterable<Integer> iterable) {
        return new LinkedQueue<>(iterable);
    }

}
