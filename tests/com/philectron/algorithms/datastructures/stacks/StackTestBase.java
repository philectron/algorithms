package com.philectron.algorithms.datastructures.stacks;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Stack;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class StackTestBase {

    static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);
    private static final List<Integer> VALUES_REVERSED = VALUES.reversed();

    private Stack<Integer> stack;
    private Stack<Integer> emptyStack;

    abstract Stack<Integer> createStack(Iterable<Integer> iterable);

    @BeforeEach
    private void setUp() {
        stack = createStack(VALUES);
        emptyStack = createStack(Collections.emptyList());
        assertThat(stack).containsExactlyElementsIn(VALUES_REVERSED).inOrder();
        assertThat(emptyStack).isEmpty();
    }

    @Test
    public void size_returnsNumberOfElements() {
        assertThat(emptyStack.size()).isEqualTo(0);
        assertThat(stack.size()).isEqualTo(VALUES.size());
    }

    @Test
    public void isEmpty_checksStackSize() {
        assertThat(emptyStack.isEmpty()).isTrue();
        assertThat(stack.isEmpty()).isFalse();
    }

    @Test
    public void push_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyStack.push(null));
        assertThrows(NullPointerException.class, () -> stack.push(null));
    }

    @Test
    public void push_insertsElementToTop_returnsTrue() {
        final int value = 1;

        assertThat(emptyStack.push(value)).isTrue();
        assertThat(emptyStack).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Deque<Integer> expectedStack = new java.util.ArrayDeque<>(VALUES_REVERSED);
        expectedStack.push(value);
        assertThat(stack.push(value)).isTrue();
        assertThat(stack).containsExactlyElementsIn(expectedStack).inOrder();
    }

    @Test
    public void pushAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyStack.pushAll(null));
        assertThrows(NullPointerException.class, () -> stack.pushAll(null));

        assertThrows(NullPointerException.class,
                () -> emptyStack.pushAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> stack.pushAll(Collections.singletonList(null)));
    }

    @Test
    public void pushAll_fromEmptyInput_addsNothing_returnsFalse() {
        assertThat(emptyStack.pushAll(Collections.emptyList())).isFalse();
        assertThat(emptyStack).isEmpty();

        assertThat(stack.pushAll(Collections.emptyList())).isFalse();
        assertThat(stack).containsExactlyElementsIn(VALUES_REVERSED).inOrder();
    }

    @Test
    public void pushAll_prependsToStackInReversedOrder_returnsTrue() {
        assertThat(emptyStack.pushAll(VALUES)).isTrue();
        assertThat(emptyStack).containsExactlyElementsIn(VALUES_REVERSED).inOrder();

        java.util.Deque<Integer> expectedStack = new java.util.ArrayDeque<>(VALUES_REVERSED);
        VALUES.forEach(expectedStack::push);
        assertThat(stack.pushAll(VALUES)).isTrue();
        assertThat(stack).containsExactlyElementsIn(expectedStack).inOrder();
    }

    @Test
    public void pop_emptyStack_removesNothing_returnsNull() {
        assertThat(emptyStack.pop()).isNull();
        assertThat(emptyStack).isEmpty();
    }

    @Test
    public void pop_removesTopElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyStack.push(expectedValue)).isTrue();
        assertThat(emptyStack.pop()).isEqualTo(expectedValue);
        assertThat(emptyStack).isEmpty();

        java.util.Deque<Integer> expectedStack = new java.util.ArrayDeque<>(VALUES_REVERSED);
        assertThat(stack.pop()).isEqualTo(expectedStack.pop());
        assertThat(stack).containsExactlyElementsIn(expectedStack).inOrder();
    }

    @Test
    public void peek_modifiesNothing_returnsTopElementIfAny() {
        assertThat(emptyStack.peek()).isNull();
        assertThat(emptyStack).isEmpty();

        assertThat(stack.peek()).isEqualTo(VALUES.getLast());
        assertThat(stack).containsExactlyElementsIn(VALUES_REVERSED).inOrder();
    }

    @Test
    public void clear_removesAllElements() {
        emptyStack.clear();
        assertThat(emptyStack.isEmpty()).isTrue();
        assertThat(emptyStack).isEmpty();

        stack.clear();
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack).isEmpty();
    }

    @Test
    public void iterator_traversesStackBottomToTop() {
        Iterator<Integer> emptyIterator = emptyStack.iterator();
        assertThat(emptyIterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyIterator.next());

        Iterator<Integer> iterator = stack.iterator();
        for (int i = 0; i < VALUES_REVERSED.size(); ++i) {
            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(VALUES_REVERSED.get(i));
        }
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

}
