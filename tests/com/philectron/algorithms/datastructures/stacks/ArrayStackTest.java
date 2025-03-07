package com.philectron.algorithms.datastructures.stacks;

import static com.google.common.truth.Truth.assertThat;

import com.philectron.algorithms.datastructures.interfaces.Stack;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayStackTest extends StackTestBase {

    private static final List<Integer> ZEROES_FULL_CAPACITY = Collections.nCopies(ArrayStack.DEFAULT_CAPACITY, 0);
    private static final List<Integer> ZEROES_NEAR_CAPACITY = Collections.nCopies(ArrayStack.DEFAULT_CAPACITY - 1, 0);

    private final Stack<Integer> fullStack = new ArrayStack<>(ZEROES_FULL_CAPACITY);

    @Override
    Stack<Integer> createStack(Iterable<Integer> iterable) {
        return new ArrayStack<>(iterable);
    }

    @Test
    public void isFull_checksStackCapacity() {
        assertThat(new ArrayStack<>().isFull()).isFalse();
        assertThat(new ArrayStack<>(ZEROES_FULL_CAPACITY).isFull()).isTrue();
    }

    @Test
    public void push_fullStack_addsNothing_returnsFalse() {
        assertThat(fullStack.push(1)).isFalse();
        assertThat(fullStack).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushAll_fullStack_addsNothing_returnsFalse() {
        assertThat(fullStack.pushAll(ZEROES_FULL_CAPACITY)).isFalse();
        assertThat(fullStack).containsExactlyElementsIn(ZEROES_FULL_CAPACITY).inOrder();
    }

    @Test
    public void pushAll_nearFullStack_addsPartialElements_returnsTrue() {
        java.util.Deque<Integer> expectedStack = new java.util.ArrayDeque<>(ZEROES_NEAR_CAPACITY);
        expectedStack.push(VALUES.getFirst());

        Stack<Integer> nearFullStack = new ArrayStack<>(ZEROES_NEAR_CAPACITY);
        assertThat(nearFullStack.pushAll(VALUES)).isTrue();
        assertThat(nearFullStack).containsExactlyElementsIn(expectedStack).inOrder();
    }

}
