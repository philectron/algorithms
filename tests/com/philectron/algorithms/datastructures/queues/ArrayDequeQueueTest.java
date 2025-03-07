package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;

import com.philectron.algorithms.datastructures.interfaces.Queue;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayDequeQueueTest extends QueueTestBase {

    private static final List<Integer> ZEROES_FULL_CAPACITY = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
    private static final List<Integer> ZEROES_NEAR_CAPACITY = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY - 1, 0);

    private final Queue<Integer> fullQueue = new ArrayDeque<>(ZEROES_FULL_CAPACITY);

    @Override
    Queue<Integer> createQueue(Iterable<Integer> iterable) {
        return new ArrayDeque<>(iterable);
    }

    @Test
    public void isFull_checksQueueCapacity() {
        assertThat(new ArrayDeque<>().isFull()).isFalse();
        assertThat(new ArrayDeque<>(ZEROES_FULL_CAPACITY).isFull()).isTrue();
    }

    @Test
    public void push_fullQueue_addsNothing_returnsFalse() {
        assertThat(fullQueue.push(1)).isFalse();
        assertThat(fullQueue).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushAll_fullQueue_addsNothing_returnsFalse() {
        assertThat(fullQueue.pushAll(VALUES)).isFalse();
        assertThat(fullQueue).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushAll_nearFullQueue_addsPartialElements_returnsTrue() {
        java.util.Queue<Integer> expectedQueue = new java.util.ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        expectedQueue.offer(VALUES.getFirst());

        Queue<Integer> nearFullQueue = new ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        assertThat(nearFullQueue.pushAll(VALUES)).isTrue();
        assertThat(nearFullQueue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

}
