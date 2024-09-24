package com.philectron.algorithms.datastructures.deques;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class ArrayDequeTest extends DequeTestBase {

    @Override
    Deque<Integer> createDeque(Iterable<Integer> iterable) {
        return new ArrayDeque<>(iterable);
    }

    @Test
    public void isFull_checksQueueCapacity() {
        assertThat(new ArrayDeque<>().isFull()).isFalse();
        assertThat(new ArrayDeque<>(Collections.nCopies(ArrayDeque.DEFAULT_QUEUE_CAPACITY, 0))
                .isFull()).isTrue();
    }

    @Test
    public void pushFront_fullQueue_fails() {
        ArrayDeque<Integer> fullDeque =
                new ArrayDeque<>(Collections.nCopies(ArrayDeque.DEFAULT_QUEUE_CAPACITY, 0));
        assertThrows(IllegalStateException.class, () -> fullDeque.pushFront(0));
    }

    @Test
    public void pushRear_fullQueue_fails() {
        ArrayDeque<Integer> fullDeque =
                new ArrayDeque<>(Collections.nCopies(ArrayDeque.DEFAULT_QUEUE_CAPACITY, 0));
        assertThrows(IllegalStateException.class, () -> fullDeque.pushRear(0));
    }

}
