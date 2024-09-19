package com.philectron.algorithms.datastructures.stacks;

import com.philectron.algorithms.datastructures.interfaces.Stack;

public class LinkedStackTest extends StackTestBase {

    @Override
    Stack<Integer> createStack(Iterable<Integer> iterable) {
        return new LinkedStack<>(iterable);
    }

}
