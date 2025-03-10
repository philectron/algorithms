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
    public void isEmpty_checksSize() {
        assertThat(emptyDeque.isEmpty()).isTrue();
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void offer_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> deque.offer(null));
        assertThrows(NullPointerException.class, () -> deque.offerFront(null));
        assertThrows(NullPointerException.class, () -> deque.offerRear(null));
    }

    @Test
    public void offer_insertsElement_returnsTrue() {
        assertThat(emptyDeque.offer(1)).isTrue();
        assertThat(emptyDeque).containsExactly(1);

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.offer(1);
        expectedDeque.offerFirst(2);
        expectedDeque.offerLast(3);

        assertThat(deque.offer(1)).isTrue();
        assertThat(deque.offerFront(2)).isTrue();
        assertThat(deque.offerRear(3)).isTrue();

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushStack_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> deque.push(null));
    }

    @Test
    public void pushStack_insertsElementAtFront_returnsTrue() {
        final int value = 1;

        assertThat(emptyDeque.push(value)).isTrue();
        assertThat(emptyDeque).containsExactly(value);

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        expectedDeque.push(value);
        assertThat(deque.push(value)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void offerAll_fromNullInput_fails() {
        List<Integer> singletonNullList = Collections.singletonList(null);

        assertThrows(NullPointerException.class, () -> deque.offerAll(null));
        assertThrows(NullPointerException.class, () -> deque.offerAll(singletonNullList));

        assertThrows(NullPointerException.class, () -> deque.offerFrontAll(null));
        assertThrows(NullPointerException.class, () -> deque.offerFrontAll(singletonNullList));

        assertThrows(NullPointerException.class, () -> deque.offerRearAll(null));
        assertThrows(NullPointerException.class, () -> deque.offerRearAll(singletonNullList));
    }

    @Test
    public void offerAll_fromEmptyInput_insertsNothing_returnsFalse() {
        assertThat(emptyDeque.offerAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();
        assertThat(deque.offerAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();

        assertThat(emptyDeque.offerFrontAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();
        assertThat(deque.offerFrontAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();

        assertThat(emptyDeque.offerRearAll(Collections.emptyList())).isFalse();
        assertThat(emptyDeque).isEmpty();
        assertThat(deque.offerRearAll(Collections.emptyList())).isFalse();
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void offerAll_insertsAllElements_returnsTrue() {
        assertThat(emptyDeque.offerAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES).inOrder();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedDeque::offer);
        VALUES.forEach(expectedDeque::offerFirst);
        VALUES.forEach(expectedDeque::offerLast);

        assertThat(deque.offerAll(VALUES)).isTrue();
        assertThat(deque.offerFrontAll(VALUES)).isTrue();
        assertThat(deque.offerRearAll(VALUES)).isTrue();
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void poll_onEmpty_removesNothing_returnsNull() {
        assertThat(emptyDeque.poll()).isNull();
        assertThat(emptyDeque).isEmpty();

        assertThat(emptyDeque.pollFront()).isNull();
        assertThat(emptyDeque).isEmpty();

        assertThat(emptyDeque.pollRear()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void poll_removesElement_returnsElement() {
        assertThat(emptyDeque.offer(1)).isTrue();
        assertThat(emptyDeque.poll()).isEqualTo(1);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.poll()).isEqualTo(expectedDeque.poll());
        assertThat(deque.pollFront()).isEqualTo(expectedDeque.pollFirst());
        assertThat(deque.pollRear()).isEqualTo(expectedDeque.pollLast());
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void popStack_onEmpty_removesNothing_returnsNull() {
        assertThat(emptyDeque.pop()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void popStack_removesFrontElement_returnsElement() {
        final int value = 1;
        assertThat(emptyDeque.offer(value)).isTrue();
        assertThat(emptyDeque.pop()).isEqualTo(value);
        assertThat(emptyDeque).isEmpty();

        java.util.Deque<Integer> expectedDeque = new java.util.ArrayDeque<>(VALUES);
        assertThat(deque.pop()).isEqualTo(expectedDeque.pop());
        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void peek_modifiesNothing_returnsElementIfAny() {
        assertThat(emptyDeque.peek()).isNull();
        assertThat(emptyDeque.peekFront()).isNull();
        assertThat(emptyDeque.peekRear()).isNull();
        assertThat(emptyDeque).isEmpty();

        assertThat(deque.peek()).isEqualTo(VALUES.getFirst());
        assertThat(deque.peekFront()).isEqualTo(VALUES.getFirst());
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
    public void iterator_traversesFrontToRear() {
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
