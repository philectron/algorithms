package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayDequeTest extends DequeTestBase {

    private static final List<Integer> ZEROES_FULL_CAPACITY =
            Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
    private static final List<Integer> ZEROES_NEAR_CAPACITY =
            Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY - 1, 0);

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
    public void offer_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.offer(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void offerFront_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.offerFront(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void offerRear_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.offerRear(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void push_asStack_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.push(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void offerAll_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.offerAll(VALUES)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void offerAll_nearFullDeque_addsPartialElements_returnsTrue() {
        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        expectedDeque.offer(VALUES.getFirst());

        Deque<Integer> nearFullDeque = new ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        assertThat(nearFullDeque.offerAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerFrontAll_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.offerFrontAll(ZEROES_FULL_CAPACITY)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void offerFrontAll_nearFullDeque_addsPartialElements_returnsTrue() {
        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        expectedDeque.offerFirst(VALUES.getFirst());

        Deque<Integer> nearFullDeque = new ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        assertThat(nearFullDeque.offerFrontAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerRearAll_fullDeque_addsNothing_returnsFalse() {
        assertThat(fullDeque.offerRearAll(ZEROES_FULL_CAPACITY)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void offerRearAll_nearFullDeque_addsPartialElements_returnsTrue() {
        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        expectedDeque.offerLast(VALUES.getFirst());

        Deque<Integer> nearFullDeque = new ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        assertThat(nearFullDeque.offerRearAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

}
