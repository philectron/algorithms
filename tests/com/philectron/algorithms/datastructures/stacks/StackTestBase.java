package com.philectron.algorithms.datastructures.stacks;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class StackTestBase {

    private static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);
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
    public void push_emptyStack_insertElementToTop() {
        final int value = 1;
        emptyStack.push(value);
        assertThat(emptyStack).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();
    }

    @Test
    public void push_insertsElementToTop() {
        final int value = 1;
        List<Integer> expectedStack = new ArrayList<>(VALUES_REVERSED);
        expectedStack.addFirst(value);

        stack.push(value);

        assertThat(stack).containsExactlyElementsIn(expectedStack).inOrder();
    }

    @Test
    public void pushAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyStack.pushAll(null));
        assertThrows(NullPointerException.class, () -> stack.pushAll(null));
    }

    @Test
    public void pushAll_fromInputWithNull_fails() {
        assertThrows(NullPointerException.class,
                () -> emptyStack.pushAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> stack.pushAll(Collections.singletonList(null)));
    }

    @Test
    public void pushAll_fromEmptyInput_doesNothing() {
        assertThat(emptyStack.pushAll(Collections.emptyList())).isFalse();
        assertThat(stack.pushAll(Collections.emptyList())).isFalse();
    }

    @Test
    public void pushAll_intoEmptyStack_buildsSameStack() {
        assertThat(emptyStack.pushAll(VALUES)).isTrue();
        assertThat(emptyStack).containsExactlyElementsIn(VALUES_REVERSED).inOrder();
    }

    @Test
    public void pushAll_intoExistingStack_prependsToStack() {
        List<Integer> valuesToPush = VALUES.stream().map(value -> -value).toList();
        List<Integer> expectedStack = new ArrayList<>(VALUES);
        expectedStack.addAll(valuesToPush);
        expectedStack = expectedStack.reversed();

        assertThat(stack.pushAll(valuesToPush)).isTrue();

        assertThat(stack).containsExactlyElementsIn(expectedStack).inOrder();
    }

    @Test
    public void pop_emptyStack_returnsEmpty() {
        assertThat(emptyStack.pop()).isEmpty();
    }

    @Test
    public void pop_singletonStack_retrievesAndRemovesTopElement() {
        final int expectedTopValue = 1;
        Stack<Integer> singletonStack = createStack(Collections.singletonList(expectedTopValue));

        assertThat(singletonStack.pop()).hasValue(expectedTopValue);
        assertThat(singletonStack).isEmpty();
    }

    @Test
    public void pop_existingStack_retrievesAndRemovesTopElement() {
        List<Integer> expectedStack = new ArrayList<>(VALUES_REVERSED);
        expectedStack.removeFirst();

        assertThat(stack.pop()).hasValue(VALUES.getLast());

        assertThat(stack).containsExactlyElementsIn(expectedStack).inOrder();
    }

    @Test
    public void top_emptyStack_returnsEmpty() {
        assertThat(emptyStack.top()).isEmpty();
        assertThat(emptyStack).isEmpty();
    }

    @Test
    public void top_existingStack_returnsTopElementWithoutRemoval() {
        assertThat(stack.top()).hasValue(VALUES.getFirst());
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
    public void iterator_traversesStackForward() {
        Iterator<Integer> emptyIt = emptyStack.iterator();
        assertThat(emptyIt.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyIt.next());

        Iterator<Integer> it = stack.iterator();
        for (int i = 0; i < VALUES_REVERSED.size(); i++) {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(VALUES_REVERSED.get(i));
        }
        assertThat(it.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

}
