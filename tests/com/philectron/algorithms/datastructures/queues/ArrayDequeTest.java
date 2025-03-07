package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayDequeTest extends DequeTestBase {

    private static final List<Integer> ZEROES_FULL_CAPACITY = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
    private static final List<Integer> ZEROES_NEAR_CAPACITY = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY - 1, 0);

    private final Deque<Integer> fullDeque = new ArrayDeque<>(ZEROES_FULL_CAPACITY);

    @Override
    Deque<Integer> createDeque(Iterable<Integer> iterable) {
        return new ArrayDeque<>(iterable);
    }

    @Test
    public void isFull_checksDequeCapacity() {
        assertThat(new ArrayDeque<>().isFull()).isFalse();
        assertThat(new ArrayDeque<>(ZEROES_FULL_CAPACITY).isFull()).isTrue();
    }

    @Test
    public void push_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.push(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushFront_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.pushFront(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushRear_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.pushRear(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushAll_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.pushAll(VALUES)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushAll_nearFullDeque_addsPartialElements_returnsTrue() {
        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        expectedDeque.offer(VALUES.getFirst());

        Deque<Integer> nearFullDeque = new ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        assertThat(nearFullDeque.pushAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushFrontAll_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.pushFrontAll(ZEROES_FULL_CAPACITY)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushFrontAll_nearFullDeque_addsPartialElements_returnsTrue() {
        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        expectedDeque.offerFirst(VALUES.getFirst());

        Deque<Integer> nearFullDeque = new ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        assertThat(nearFullDeque.pushFrontAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushRearAll_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.pushRearAll(ZEROES_FULL_CAPACITY)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushRearAll_nearFullDeque_addsPartialElements_returnsTrue() {
        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        expectedDeque.offerLast(VALUES.getFirst());

        Deque<Integer> nearFullDeque = new ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        assertThat(nearFullDeque.pushRearAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

}
