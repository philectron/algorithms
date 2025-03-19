package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayDequeTest extends DequeTestBase {

    @Override
    Deque<Integer> createDeque(Iterable<Integer> iterable) {
        return new ArrayDeque<>(iterable);
    }

    @Test
    public void init_invalidCapacity_fails() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayDeque<>(2, 1));
        assertThrows(IllegalArgumentException.class, () -> new ArrayDeque<>(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new ArrayDeque<>(0, 0));
        assertThrows(IllegalArgumentException.class, () -> new ArrayDeque<>(0, -1));
    }

    @Test
    public void offer_exceedsCapacity_growsArray_addsElements_returnsTrue() {
        List<Integer> fullCapacityList = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(fullCapacityList);
        expectedDeque.offer(-1);
        expectedDeque.offerFirst(-2);
        expectedDeque.offerLast(-3);
        expectedDeque.push(-4);
        VALUES.forEach(expectedDeque::offerFirst);
        VALUES.forEach(expectedDeque::offerLast);

        Deque<Integer> deque = new ArrayDeque<>(fullCapacityList);
        assertThat(deque.offer(-1)).isTrue();
        assertThat(deque.offerFront(-2)).isTrue();
        assertThat(deque.offerRear(-3)).isTrue();
        assertThat(deque.push(-4)).isTrue();
        assertThat(deque.offerFrontAll(VALUES)).isTrue();
        assertThat(deque.offerRearAll(VALUES)).isTrue();

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offer_fullDeque_addsNothing_returnsFalse() {
        Deque<Integer> fullDeque = new ArrayDeque<>(1, 1);
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

        Deque<Integer> nearFullDeque = new ArrayDeque<>(maximumCapacity, maximumCapacity);
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

        Deque<Integer> nearFullDeque = new ArrayDeque<>(maximumCapacity, maximumCapacity);
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

        Deque<Integer> nearFullDeque = new ArrayDeque<>(maximumCapacity, maximumCapacity);
        assertThat(nearFullDeque.offerRearAll(VALUES)).isTrue();
        assertThat(nearFullDeque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

}
