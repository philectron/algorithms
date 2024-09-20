package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Queue;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class CircularArrayQueueTest extends QueueTestBase {

    @Override
    Queue<Integer> createQueue(Iterable<Integer> iterable) {
        return new CircularArrayQueue<>(iterable);
    }

    @Test
    public void isFull_checksQueueCapacity() {
        assertThat(new CircularArrayQueue<>().isFull()).isFalse();
        assertThat(new CircularArrayQueue<>(
                Collections.nCopies(CircularArrayQueue.DEFAULT_QUEUE_CAPACITY, 0)).isFull())
                        .isTrue();
    }

    @Test
    public void enqueue_fullQueue_fails() {
        CircularArrayQueue<Integer> fullQueue = new CircularArrayQueue<>(
                Collections.nCopies(CircularArrayQueue.DEFAULT_QUEUE_CAPACITY, 0));
        assertThrows(IllegalStateException.class, () -> fullQueue.enqueue(0));
    }

}
