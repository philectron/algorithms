package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Queue;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class QueueTestBase {

    private static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);

    private Queue<Integer> queue;
    private Queue<Integer> emptyQueue;

    abstract Queue<Integer> createQueue(Iterable<Integer> iterable);

    @BeforeEach
    private void setUp() {
        queue = createQueue(VALUES);
        emptyQueue = createQueue(Collections.emptyList());
        assertThat(queue).containsExactlyElementsIn(VALUES).inOrder();
        assertThat(emptyQueue).isEmpty();
    }

    @Test
    public void size_returnsNumberOfElements() {
        assertThat(emptyQueue.size()).isEqualTo(0);
        assertThat(queue.size()).isEqualTo(VALUES.size());
    }

    @Test
    public void isEmpty_checksQueueSize() {
        assertThat(emptyQueue.isEmpty()).isTrue();
        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    public void push_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyQueue.push(null));
        assertThrows(NullPointerException.class, () -> queue.push(null));
    }

    @Test
    public void push_insertsElementToRear_returnsTrue() {
        final int value = 1;

        assertThat(emptyQueue.push(value)).isTrue();
        assertThat(emptyQueue).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();

        java.util.Queue<Integer> expectedQueue = new java.util.ArrayDeque<>(VALUES);
        expectedQueue.offer(value);
        assertThat(queue.push(value)).isTrue();
        assertThat(queue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

    @Test
    public void pushAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyQueue.pushAll(null));
        assertThrows(NullPointerException.class, () -> queue.pushAll(null));

        assertThrows(NullPointerException.class,
                () -> emptyQueue.pushAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> queue.pushAll(Collections.singletonList(null)));
    }

    @Test
    public void pushAll_fromEmptyInput_addsNothing_returnsFalse() {
        assertThat(emptyQueue.pushAll(Collections.emptyList())).isFalse();
        assertThat(emptyQueue).isEmpty();

        assertThat(queue.pushAll(Collections.emptyList())).isFalse();
        assertThat(queue).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void pushAll_appendsToQueue_returnsTrue() {
        assertThat(emptyQueue.pushAll(VALUES)).isTrue();
        assertThat(emptyQueue).containsExactlyElementsIn(VALUES).inOrder();

        java.util.Queue<Integer> expectedQueue = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedQueue::offer);
        assertThat(queue.pushAll(VALUES)).isTrue();
        assertThat(queue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

    @Test
    public void pop_emptyQueue_removesNothing_returnsNull() {
        assertThat(emptyQueue.pop()).isNull();
        assertThat(emptyQueue).isEmpty();
    }

    @Test
    public void pop_removesFrontElement_returnsElement() {
        final int expectedValue = 1;
        assertThat(emptyQueue.push(expectedValue)).isTrue();
        assertThat(emptyQueue.pop()).isEqualTo(expectedValue);
        assertThat(emptyQueue).isEmpty();

        java.util.Queue<Integer> expectedQueue = new java.util.ArrayDeque<>(VALUES);
        assertThat(queue.pop()).isEqualTo(expectedQueue.poll());
        assertThat(queue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

    @Test
    public void peek_modifiesNothing_returnsFrontElementIfAny() {
        assertThat(emptyQueue.peek()).isNull();
        assertThat(emptyQueue).isEmpty();

        assertThat(queue.peek()).isEqualTo(VALUES.getFirst());
        assertThat(queue).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void clear_removesAllElements() {
        emptyQueue.clear();
        assertThat(emptyQueue.isEmpty()).isTrue();
        assertThat(emptyQueue).isEmpty();

        queue.clear();
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue).isEmpty();
    }

    @Test
    public void iterator_traversesQueueFrontToRear() {
        Iterator<Integer> emptyIterator = emptyQueue.iterator();
        assertThat(emptyIterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyIterator.next());

        Iterator<Integer> iterator = queue.iterator();
        for (int i = 0; i < VALUES.size(); ++i) {
            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(VALUES.get(i));
        }
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

}
