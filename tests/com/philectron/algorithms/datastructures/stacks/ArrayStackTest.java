package com.philectron.algorithms.datastructures.stacks;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Stack;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class ArrayStackTest extends StackTestBase {

    @Override
    Stack<Integer> createStack(Iterable<Integer> iterable) {
        return new ArrayStack<>(iterable);
    }

    @Test
    public void isFull_checksStackCapacity() {
        assertThat(new ArrayStack<>().isFull()).isFalse();
        assertThat(new ArrayStack<>(Collections.nCopies(ArrayStack.DEFAULT_STACK_CAPACITY, 0))
                .isFull()).isTrue();
    }

    @Test
    public void push_fullStack_fails() {
        ArrayStack<Integer> fullStack =
                new ArrayStack<>(Collections.nCopies(ArrayStack.DEFAULT_STACK_CAPACITY, 0));
        assertThrows(IllegalStateException.class, () -> fullStack.push(0));
    }

}
