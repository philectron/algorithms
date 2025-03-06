package com.philectron.algorithms.datastructures.stacks;

import static com.google.common.truth.Truth.assertThat;

import com.philectron.algorithms.datastructures.interfaces.Stack;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayStackTest extends StackTestBase {

    @Override
    Stack<Integer> createStack(Iterable<Integer> iterable) {
        return new ArrayStack<>(iterable);
    }

    @Test
    public void isFull_checksStackCapacity() {
        assertThat(new ArrayStack<>().isFull()).isFalse();
        assertThat(new ArrayStack<>(Collections.nCopies(ArrayStack.DEFAULT_CAPACITY, 0)).isFull())
                .isTrue();
    }

    @Test
    public void push_fullStack_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayStack.DEFAULT_CAPACITY, 0);
        Stack<Integer> fullStack = new ArrayStack<>(listOfZeroes);
        assertThat(fullStack.push(1)).isFalse();
        assertThat(fullStack).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

    @Test
    public void pushAll_fullStack_addsNothing_returnsFalse() {
        List<Integer> listOfZeroes = Collections.nCopies(ArrayStack.DEFAULT_CAPACITY, 0);
        Stack<Integer> fullStack = new ArrayStack<>(listOfZeroes);
        assertThat(fullStack.pushAll(listOfZeroes)).isFalse();
        assertThat(fullStack).containsExactlyElementsIn(listOfZeroes).inOrder();
    }

}
