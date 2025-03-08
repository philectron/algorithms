package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Collections;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class ArrayDequeTest extends DequeTestBase {

    @Override
    Deque<Integer> createDeque(Iterable<Integer> iterable) {
        return new ArrayDeque<>(iterable);
    }

    @Test
    public void init_nonPositiveCapacity_fails() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayDeque<>(0));
        assertThrows(IllegalArgumentException.class, () -> new ArrayDeque<>(-1));
    }

    @Test
    public void offer_exceedsCapacity_growsArray_addsElements_returnsTrue() {
        java.util.List<Integer> frontSequence = IntStream.range(0, ArrayDeque.DEFAULT_CAPACITY / 2)
                .limit(ArrayDeque.DEFAULT_CAPACITY / 2).boxed().toList();
        java.util.List<Integer> rearSequence =
                IntStream.range(ArrayDeque.DEFAULT_CAPACITY / 2, ArrayDeque.DEFAULT_CAPACITY)
                        .limit(ArrayDeque.DEFAULT_CAPACITY / 2).boxed().toList();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>();
        frontSequence.forEach(expectedDeque::offerFirst);
        rearSequence.forEach(expectedDeque::offerLast);
        expectedDeque.offer(-1);
        expectedDeque.offerFirst(-2);
        expectedDeque.offerLast(-3);
        expectedDeque.push(-4);

        Deque<Integer> deque = new ArrayDeque<>();
        assertThat(deque.offerFrontAll(frontSequence)).isTrue();
        assertThat(deque.offerRearAll(rearSequence)).isTrue();
        assertThat(deque.offer(-1)).isTrue();
        assertThat(deque.offerFront(-2)).isTrue();
        assertThat(deque.offerRear(-3)).isTrue();
        assertThat(deque.push(-4)).isTrue();

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offer_fullDeque_addsNothing_returnsFalse() {
        Deque<Integer> fullDeque = new ArrayDeque<>(1);
        fullDeque.offer(1);

        assertThat(fullDeque.offer(2)).isFalse();
        assertThat(fullDeque.offerFront(2)).isFalse();
        assertThat(fullDeque.offerRear(2)).isFalse();
        assertThat(fullDeque.push(2)).isFalse();

        assertThat(fullDeque.offerAll(VALUES)).isFalse();
        assertThat(fullDeque.offerFrontAll(VALUES)).isFalse();
        assertThat(fullDeque.offerRearAll(VALUES)).isFalse();

        assertThat(fullDeque.size()).isEqualTo(1);
        assertThat(fullDeque).containsExactlyElementsIn(Collections.singletonList(1)).inOrder();
    }

    @Test
    public void offerAll_nearFullDeque_addsPartialElements_returnsTrue() {
        final int maximumCapacity = 3;

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>();
        for (int i = 0; i < maximumCapacity; ++i) {
            expectedDeque.offer(VALUES.get(i));
        }

        Deque<Integer> nearFullDeque = new ArrayDeque<>(maximumCapacity);
        assertThat(nearFullDeque.offerAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerFrontAll_nearFullDeque_addsPartialElements_returnsTrue() {
        final int maximumCapacity = 3;

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>();
        for (int i = 0; i < maximumCapacity; ++i) {
            expectedDeque.offerFirst(VALUES.get(i));
        }

        Deque<Integer> nearFullDeque = new ArrayDeque<>(maximumCapacity);
        assertThat(nearFullDeque.offerFrontAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerRearAll_nearFullDeque_addsPartialElements_returnsTrue() {
        final int maximumCapacity = 3;

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>();
        for (int i = 0; i < maximumCapacity; ++i) {
            expectedDeque.offerLast(VALUES.get(i));
        }

        Deque<Integer> nearFullDeque = new ArrayDeque<>(maximumCapacity);
        assertThat(nearFullDeque.offerRearAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

}
