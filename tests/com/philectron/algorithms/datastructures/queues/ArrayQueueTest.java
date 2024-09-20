package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Queue;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class ArrayQueueTest extends QueueTestBase {

    @Override
    Queue<Integer> createQueue(Iterable<Integer> iterable) {
        return new ArrayQueue<>(iterable);
    }

    @Test
    public void isFull_checksQueueCapacity() {
        assertThat(new ArrayQueue<>().isFull()).isFalse();
        assertThat(new ArrayQueue<>(Collections.nCopies(ArrayQueue.DEFAULT_QUEUE_CAPACITY, 0))
                .isFull()).isTrue();
    }

    @Test
    public void enqueue_fullQueue_fails() {
        ArrayQueue<Integer> fullQueue =
                new ArrayQueue<>(Collections.nCopies(ArrayQueue.DEFAULT_QUEUE_CAPACITY, 0));
        assertThrows(IllegalStateException.class, () -> fullQueue.enqueue(0));
    }

}
