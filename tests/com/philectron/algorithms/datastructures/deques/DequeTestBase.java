package com.philectron.algorithms.datastructures.deques;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Deque;
import java.util.ArrayList;
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
    public void pushFront_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushFront(null));
        assertThrows(NullPointerException.class, () -> deque.pushFront(null));
    }

    @Test
    public void pushFront_emptyDeque_insertElementToFront() {
        final int value = 1;
        emptyDeque.pushFront(value);
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();
    }

    @Test
    public void pushFront_insertsElementToFront() {
        final int value = 1;
        List<Integer> expectedDeque = new ArrayList<>(VALUES);
        expectedDeque.addFirst(value);

        deque.pushFront(value);

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushRear_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushRear(null));
        assertThrows(NullPointerException.class, () -> deque.pushRear(null));
    }

    @Test
    public void pushRear_emptyDeque_insertElementToRear() {
        final int value = 1;
        emptyDeque.pushRear(value);
        assertThat(emptyDeque).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();
    }

    @Test
    public void pushRear_insertsElementToRear() {
        final int value = 1;
        List<Integer> expectedDeque = new ArrayList<>(VALUES);
        expectedDeque.add(value);

        deque.pushRear(value);

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushFrontAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushFrontAll(null));
        assertThrows(NullPointerException.class, () -> deque.pushFrontAll(null));
    }

    @Test
    public void pushFrontAll_fromInputWithNull_fails() {
        assertThrows(NullPointerException.class,
                () -> emptyDeque.pushFrontAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> deque.pushFrontAll(Collections.singletonList(null)));
    }

    @Test
    public void pushFrontAll_fromEmptyInput_doesNothing() {
        assertThat(emptyDeque.pushFrontAll(Collections.emptyList())).isFalse();
        assertThat(deque.pushFrontAll(Collections.emptyList())).isFalse();
    }

    @Test
    public void pushFrontAll_intoEmptyDeque_buildsReversedDeque() {
        assertThat(emptyDeque.pushFrontAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES.reversed()).inOrder();
    }

    @Test
    public void pushFrontAll_intoExistingDeque_prependsToDeque() {
        List<Integer> expectedDeque = new ArrayList<>(VALUES);
        VALUES.forEach(value -> expectedDeque.addFirst(value));

        assertThat(deque.pushFrontAll(VALUES)).isTrue();

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void pushRearAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyDeque.pushRearAll(null));
        assertThrows(NullPointerException.class, () -> deque.pushRearAll(null));
    }

    @Test
    public void pushRearAll_fromInputWithNull_fails() {
        assertThrows(NullPointerException.class,
                () -> emptyDeque.pushRearAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> deque.pushRearAll(Collections.singletonList(null)));
    }

    @Test
    public void pushRearAll_fromEmptyInput_doesNothing() {
        assertThat(emptyDeque.pushRearAll(Collections.emptyList())).isFalse();
        assertThat(deque.pushRearAll(Collections.emptyList())).isFalse();
    }

    @Test
    public void pushRearAll_intoEmptyDeque_buildsSameDeque() {
        assertThat(emptyDeque.pushRearAll(VALUES)).isTrue();
        assertThat(emptyDeque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void pushRearAll_intoExistingDeque_appendsToDeque() {
        List<Integer> expectedDeque = new ArrayList<>(VALUES);
        expectedDeque.addAll(VALUES);

        assertThat(deque.pushRearAll(VALUES)).isTrue();

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void popFront_emptyDeque_fails() {
        assertThrows(NoSuchElementException.class, () -> emptyDeque.popFront());
    }

    @Test
    public void popFront_singletonDeque_retrievesAndRemovesFrontElement() {
        final int expectedFrontValue = 1;
        Deque<Integer> singletonDeque = createDeque(Collections.singletonList(expectedFrontValue));

        assertThat(singletonDeque.popFront()).isEqualTo(expectedFrontValue);
        assertThat(singletonDeque).isEmpty();
    }

    @Test
    public void popFront_existingDeque_retrievesAndRemovesFrontElement() {
        List<Integer> expectedDeque = new ArrayList<>(VALUES);

        assertThat(deque.popFront()).isEqualTo(expectedDeque.removeFirst());

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void popRear_emptyDeque_fails() {
        assertThrows(NoSuchElementException.class, () -> emptyDeque.popRear());
    }

    @Test
    public void popRear_singletonDeque_retrievesAndRemovesRearElement() {
        final int expectedRearValue = 1;
        Deque<Integer> singletonDeque = createDeque(Collections.singletonList(expectedRearValue));

        assertThat(singletonDeque.popRear()).isEqualTo(expectedRearValue);
        assertThat(singletonDeque).isEmpty();
    }

    @Test
    public void popRear_existingDeque_retrievesAndRemovesRearElement() {
        List<Integer> expectedDeque = new ArrayList<>(VALUES);

        assertThat(deque.popRear()).isEqualTo(expectedDeque.removeLast());

        assertThat(deque).containsExactlyElementsIn(expectedDeque).inOrder();
    }

    @Test
    public void peekFront_emptyDeque_returnsNull() {
        assertThat(emptyDeque.peekFront()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void peekFront_existingDeque_returnsFrontElementWithoutRemoval() {
        assertThat(deque.peekFront()).isEqualTo(VALUES.getFirst());
        assertThat(deque).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void peekRear_emptyDeque_returnsNull() {
        assertThat(emptyDeque.peekRear()).isNull();
        assertThat(emptyDeque).isEmpty();
    }

    @Test
    public void peekRear_existingDeque_returnsRearElementWithoutRemoval() {
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
        Iterator<Integer> emptyIt = emptyDeque.iterator();
        assertThat(emptyIt.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyIt.next());

        Iterator<Integer> it = deque.iterator();
        for (int i = 0; i < VALUES.size(); ++i) {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(VALUES.get(i));
        }
        assertThat(it.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

}
