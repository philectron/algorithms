package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;

import com.philectron.algorithms.datastructures.interfaces.Queue;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayDequeQueueTest extends QueueTestBase {

    @Override
    Queue<Integer> createQueue(Iterable<Integer> iterable) {
        return new ArrayDeque<>(iterable);
    }

    @Test
    public void isFull_checksQueueCapacity() {
        assertThat(new ArrayDeque<>().isFull()).isFalse();
        assertThat(new ArrayDeque<>(Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0)).isFull())
                .isTrue();
    }

    @Test
    public void push_fullQueue_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
        Queue<Integer> fullQueue = new ArrayDeque<>(listOfZeroes);
        assertThat(fullQueue.push(1)).isFalse();
        assertThat(fullQueue).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

    @Test
    public void pushAll_fullDeque_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
        Queue<Integer> fullQueue = new ArrayDeque<>(listOfZeroes);
        assertThat(fullQueue.pushAll(listOfZeroes)).isFalse();
        assertThat(fullQueue).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

}
