package com.philectron.algorithms.datastructures.queues;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Queue;
import java.util.ArrayList;
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
    public void enqueue_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyQueue.enqueue(null));
        assertThrows(NullPointerException.class, () -> queue.enqueue(null));
    }

    @Test
    public void enqueue_emptyQueue_insertElementToRear() {
        final int value = 1;
        emptyQueue.enqueue(value);
        assertThat(emptyQueue).containsExactlyElementsIn(Collections.singletonList(value))
                .inOrder();
    }

    @Test
    public void enqueue_insertsElementToRear() {
        final int value = 1;
        List<Integer> expectedQueue = new ArrayList<>(VALUES);
        expectedQueue.add(value);

        queue.enqueue(value);

        assertThat(queue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

    @Test
    public void enqueueAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptyQueue.enqueueAll(null));
        assertThrows(NullPointerException.class, () -> queue.enqueueAll(null));
    }

    @Test
    public void enqueueAll_fromInputWithNull_fails() {
        assertThrows(NullPointerException.class,
                () -> emptyQueue.enqueueAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> queue.enqueueAll(Collections.singletonList(null)));
    }

    @Test
    public void enqueueAll_fromEmptyInput_doesNothing() {
        assertThat(emptyQueue.enqueueAll(Collections.emptyList())).isFalse();
        assertThat(queue.enqueueAll(Collections.emptyList())).isFalse();
    }

    @Test
    public void enqueueAll_intoEmptyQueue_buildsSameQueue() {
        assertThat(emptyQueue.enqueueAll(VALUES)).isTrue();
        assertThat(emptyQueue).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void enqueueAll_intoExistingQueue_appendsToQueue() {
        List<Integer> expectedQueue = new ArrayList<>(VALUES);
        expectedQueue.addAll(VALUES);

        assertThat(queue.enqueueAll(VALUES)).isTrue();

        assertThat(queue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

    @Test
    public void dequeue_emptyQueue_returnsEmpty() {
        assertThat(emptyQueue.dequeue()).isEmpty();
    }

    @Test
    public void dequeue_singletonQueue_retrievesAndRemovesFrontElement() {
        final int expectedFrontValue = 1;
        Queue<Integer> singletonQueue = createQueue(Collections.singletonList(expectedFrontValue));

        assertThat(singletonQueue.dequeue()).hasValue(expectedFrontValue);
        assertThat(singletonQueue).isEmpty();
    }

    @Test
    public void dequeue_existingQueue_retrievesAndRemovesFrontElement() {
        List<Integer> expectedQueue = new ArrayList<>(VALUES);

        assertThat(queue.dequeue()).hasValue(expectedQueue.removeFirst());

        assertThat(queue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

    @Test
    public void front_emptyQueue_returnsEmpty() {
        assertThat(emptyQueue.front()).isEmpty();
        assertThat(emptyQueue).isEmpty();
    }

    @Test
    public void front_existingQueue_returnsFrontElementWithoutRemoval() {
        assertThat(queue.front()).hasValue(VALUES.getFirst());
        assertThat(queue).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void rear_emptyQueue_returnsEmpty() {
        assertThat(emptyQueue.rear()).isEmpty();
        assertThat(emptyQueue).isEmpty();
    }

    @Test
    public void rear_existingQueue_returnsRearElementWithoutRemoval() {
        assertThat(queue.rear()).hasValue(VALUES.getLast());
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
    public void iterator_traversesQueueForward() {
        Iterator<Integer> emptyIt = emptyQueue.iterator();
        assertThat(emptyIt.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyIt.next());

        Iterator<Integer> it = queue.iterator();
        for (int i = 0; i < VALUES.size(); i++) {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(VALUES.get(i));
        }
        assertThat(it.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

}
