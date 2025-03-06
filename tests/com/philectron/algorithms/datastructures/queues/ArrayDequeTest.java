package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;

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
    public void isFull_checksDequeCapacity() {
        assertThat(new ArrayDeque<>().isFull()).isFalse();
        assertThat(new ArrayDeque<>(Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0)).isFull())
                .isTrue();
    }

    @Test
    public void push_fullDeque_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
        Deque<Integer> fullDeque = new ArrayDeque<>(listOfZeroes);
        assertThat(fullDeque.push(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

    @Test
    public void pushFront_fullDeque_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
        Deque<Integer> fullDeque = new ArrayDeque<>(listOfZeroes);
        assertThat(fullDeque.pushFront(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

    @Test
    public void pushRear_fullDeque_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
        Deque<Integer> fullDeque = new ArrayDeque<>(listOfZeroes);
        assertThat(fullDeque.pushRear(1)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

    @Test
    public void pushAll_fullDeque_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
        Deque<Integer> fullDeque = new ArrayDeque<>(listOfZeroes);
        assertThat(fullDeque.pushAll(listOfZeroes)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

    @Test
    public void pushFrontAll_fullDeque_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
        Deque<Integer> fullDeque = new ArrayDeque<>(listOfZeroes);
        assertThat(fullDeque.pushFrontAll(listOfZeroes)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

    @Test
    public void pushRearAll_fullDeque_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayDeque.DEFAULT_CAPACITY, 0);
        Deque<Integer> fullDeque = new ArrayDeque<>(listOfZeroes);
        assertThat(fullDeque.pushRearAll(listOfZeroes)).isFalse();
        assertThat(fullDeque).containsExactlyElementsIn(listOfZeroes).inOrder();
    }
}
