package com.philectron.algorithms.datastructures.stacks;

import com.philectron.algorithms.datastructures.interfaces.Stack;

public class LinkedListStackTest extends StackTestBase {

    @Override
    Stack<Integer> createStack(Iterable<Integer> iterable) {
        return new LinkedListStack<>(iterable);
    }

}
