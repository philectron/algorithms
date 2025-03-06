package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class DequeTestBase {

    private static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);

    private Deque<Integer> deque;
    private Deque<Integer> emptyDeque;

    abstract Deque<Integer> createDeque(Iterable<Integer> iterable);

    @BeforeEach
    private void setUp() {
        deque = createDeque(VALUES);
        emptyDeque = createDeque(Collections.emptyList());
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void size_returnsNumberOfElements() {
        assertThat(emptyDeque.size()).isEqualTo(0);
        assertThat(deque.size()).isEqualTo(VALUES.size());
    }

    @Test
    public void isEmpty_checksDequeSize() {
        assertThat(emptyDeque.isEmpty()).isTrue();
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void push_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.push(null));
        assertThrows(NullPointerException.class, () -> deque.push(null));
    }

    @Test
    public void push_insertsElementToRear_returnsTrue() {
        final int value = 1;

        assertThat(emptyDeque.push(value)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.offer(value);
        assertThat(deque.push(value)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushFront_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushFront(null));
        assertThrows(NullPointerException.class, () -> deque.pushFront(null));
    }

    @Test
    public void pushFront_insertsElementToFront_returnsTrue() {
        final int value = 1;

        assertThat(emptyDeque.pushFront(value)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.offerFirst(value);
        assertThat(deque.pushFront(value)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushRear_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushRear(null));
        assertThrows(NullPointerException.class, () -> deque.pushRear(null));
    }

    @Test
    public void pushRear_insertsElementToRear_returnsTrue() {
        final int value = 1;

        assertThat(emptyDeque.pushRear(value)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.offerLast(value);
        assertThat(deque.pushRear(value)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushAll(null));
        assertThrows(NullPointerException.class, () -> deque.pushAll(null));

        assertThrows(NullPointerException.class,
                () -> emptyDeque.pushAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> deque.pushAll(Collections.singletonList(null)));
    }

    @Test
    public void pushAll_fromEmptyInput_addsNothing_returnsFalse() {
        assertThat(emptyDeque.pushAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.pushAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void pushAll_appendsToDeque_returnsTrue() {
        assertThat(emptyDeque.pushAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES).inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedDeque::offer);
        assertThat(deque.pushAll(VALUES)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushFrontAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushFrontAll(null));
        assertThrows(NullPointerException.class, () -> deque.pushFrontAll(null));

        assertThrows(NullPointerException.class,
                () -> emptyDeque.pushFrontAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> deque.pushFrontAll(Collections.singletonList(null)));
    }

    @Test
    public void pushFrontAll_fromEmptyInput_addsNothing_returnsFalse() {
        assertThat(emptyDeque.pushFrontAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.pushFrontAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void pushFrontAll_prependsToDequeInReversedOrder_returnsTrue() {
        assertThat(emptyDeque.pushFrontAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES.reversed()).inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedDeque::offerFirst);
        assertThat(deque.pushFrontAll(VALUES)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushRearAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushRearAll(null));
        assertThrows(NullPointerException.class, () -> deque.pushRearAll(null));

        assertThrows(NullPointerException.class,
                () -> emptyDeque.pushRearAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> deque.pushRearAll(Collections.singletonList(null)));
    }

    @Test
    public void pushRearAll_fromEmptyInput_addsNothing_returnsFalse() {
        assertThat(emptyDeque.pushRearAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.pushRearAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void pushRearAll_appendsToDeque_returnsTrue() {
        assertThat(emptyDeque.pushRearAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES).inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedDeque::offerLast);
        assertThat(deque.pushRearAll(VALUES)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pop_emptyDeque_removesNothing_returnsNull() {
        assertThat(emptyDeque.pop()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void pop_removesFrontElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyDeque.push(expectedValue)).isTrue();
        assertThat(emptyDeque.pop()).isEqualTo(expectedValue);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.pop()).isEqualTo(expectedDeque.poll());
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void popFront_emptyDeque_removesNothing_returnsNull() {
        assertThat(emptyDeque.popFront()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void popFront_removesFrontElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyDeque.push(expectedValue)).isTrue();
        assertThat(emptyDeque.popFront()).isEqualTo(expectedValue);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.popFront()).isEqualTo(expectedDeque.pollFirst());
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void popRear_emptyDeque_removesNothing_returnsNull() {
        assertThat(emptyDeque.popRear()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void popRear_removesRearElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyDeque.push(expectedValue)).isTrue();
        assertThat(emptyDeque.popRear()).isEqualTo(expectedValue);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.popRear()).isEqualTo(expectedDeque.pollLast());
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void peek_modifiesNothing_returnsFrontElementIfAny() {
        assertThat(emptyDeque.peek()).isNull();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.peek()).isEqualTo(VALUES.getFirst());
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void peekFront_modifiesNothing_returnsFrontElementIfAny() {
        assertThat(emptyDeque.peekFront()).isNull();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.peekFront()).isEqualTo(VALUES.getFirst());
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void peekRear_modifiesNothing_returnsRearElementIfAny() {
        assertThat(emptyDeque.peekRear()).isNull();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.peekRear()).isEqualTo(VALUES.getLast());
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void clear_removesAllElements() {
        emptyDeque.clear();
        assertThat(emptyDeque.isEmpty()).isTrue();
        assertThat(emptyDeque).isEmpty();

        deque.clear();
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque).isEmpty();
    }

    @Test
    public void iterator_traversesDequeFrontToRear() {
        Iterator<Integer> emptyIterator = emptyDeque.iterator();
        assertThat(emptyIterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyIterator.next());

        Iterator<Integer> iterator = deque.iterator();
        for (int i = 0; i < VALUES.size(); ++i) {
            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(VALUES.get(i));
        }
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

}
