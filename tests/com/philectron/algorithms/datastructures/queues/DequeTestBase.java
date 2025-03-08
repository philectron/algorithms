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

    static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);

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
    public void offer_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.offer(null));
        assertThrows(NullPointerException.class, () -> deque.offer(null));
    }

    @Test
    public void offer_insertsElementAtRear_returnsTrue() {
        final int value = 1;

        assertThat(emptyDeque.offer(value)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.offer(value);
        assertThat(deque.offer(value)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerFront_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.offerFront(null));
        assertThrows(NullPointerException.class, () -> deque.offerFront(null));
    }

    @Test
    public void offerFront_insertsElementAtFront_returnsTrue() {
        final int value = 1;

        assertThat(emptyDeque.offerFront(value)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.offerFirst(value);
        assertThat(deque.offerFront(value)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerRear_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.offerRear(null));
        assertThrows(NullPointerException.class, () -> deque.offerRear(null));
    }

    @Test
    public void offerRear_insertsElementAtRear_returnsTrue() {
        final int value = 1;

        assertThat(emptyDeque.offerRear(value)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.offerLast(value);
        assertThat(deque.offerRear(value)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void push_asStack_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.push(null));
        assertThrows(NullPointerException.class, () -> deque.push(null));
    }

    @Test
    public void push_asStack_insertsElementAtFront_returnsTrue() {
        final int value = 1;

        assertThat(emptyDeque.push(value)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.push(value);
        assertThat(deque.push(value)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.offerAll(null));
        assertThrows(NullPointerException.class, () -> deque.offerAll(null));

        assertThrows(NullPointerException.class,
                () -> emptyDeque.offerAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> deque.offerAll(Collections.singletonList(null)));
    }

    @Test
    public void offerAll_fromEmptyInput_addsNothing_returnsFalse() {
        assertThat(emptyDeque.offerAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.offerAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void offerAll_appendsToDeque_returnsTrue() {
        assertThat(emptyDeque.offerAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES).inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedDeque::offer);
        assertThat(deque.offerAll(VALUES)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerFrontAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.offerFrontAll(null));
        assertThrows(NullPointerException.class, () -> deque.offerFrontAll(null));

        assertThrows(NullPointerException.class,
                () -> emptyDeque.offerFrontAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> deque.offerFrontAll(Collections.singletonList(null)));
    }

    @Test
    public void offerFrontAll_fromEmptyInput_addsNothing_returnsFalse() {
        assertThat(emptyDeque.offerFrontAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.offerFrontAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void offerFrontAll_prependsToDequeInReversedOrder_returnsTrue() {
        assertThat(emptyDeque.offerFrontAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES.reversed()).inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedDeque::offerFirst);
        assertThat(deque.offerFrontAll(VALUES)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerRearAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.offerRearAll(null));
        assertThrows(NullPointerException.class, () -> deque.offerRearAll(null));

        assertThrows(NullPointerException.class,
                () -> emptyDeque.offerRearAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> deque.offerRearAll(Collections.singletonList(null)));
    }

    @Test
    public void offerRearAll_fromEmptyInput_addsNothing_returnsFalse() {
        assertThat(emptyDeque.offerRearAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.offerRearAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void offerRearAll_appendsToDeque_returnsTrue() {
        assertThat(emptyDeque.offerRearAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES).inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedDeque::offerLast);
        assertThat(deque.offerRearAll(VALUES)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void poll_emptyDeque_removesNothing_returnsNull() {
        assertThat(emptyDeque.poll()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void poll_removesFrontElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyDeque.offer(expectedValue)).isTrue();
        assertThat(emptyDeque.poll()).isEqualTo(expectedValue);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.poll()).isEqualTo(expectedDeque.poll());
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pollFront_emptyDeque_removesNothing_returnsNull() {
        assertThat(emptyDeque.pollFront()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void pollFront_removesFrontElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyDeque.offer(expectedValue)).isTrue();
        assertThat(emptyDeque.pollFront()).isEqualTo(expectedValue);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.pollFront()).isEqualTo(expectedDeque.pollFirst());
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pollRear_emptyDeque_removesNothing_returnsNull() {
        assertThat(emptyDeque.pollRear()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void pollRear_removesRearElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyDeque.offer(expectedValue)).isTrue();
        assertThat(emptyDeque.pollRear()).isEqualTo(expectedValue);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.pollRear()).isEqualTo(expectedDeque.pollLast());
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pop_asStack_emptyDeque_removesNothing_returnsNull() {
        assertThat(emptyDeque.pop()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void pop_asStack_removesFrontElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyDeque.offer(expectedValue)).isTrue();
        assertThat(emptyDeque.pop()).isEqualTo(expectedValue);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.pop()).isEqualTo(expectedDeque.pop());
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
