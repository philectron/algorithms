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

    static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);

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
    public void isEmpty_checksSize() {
        assertThat(emptyQueue.isEmpty()).isTrue();
        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    public void offer_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptyQueue.offer(null));
        assertThrows(NullPointerException.class, () -> queue.offer(null));
    }

    @Test
    public void offer_insertsElement_returnsTrue() {
        assertThat(emptyQueue.offer(1)).isTrue();
        assertThat(emptyQueue).containsExactly(1);

        java.util.Queue<Integer> expectedQueue = new java.util.ArrayDeque<>(VALUES);
        expectedQueue.offer(1);
        assertThat(queue.offer(1)).isTrue();
        assertThat(queue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

    @Test
    public void offerAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> queue.offerAll(null));
        assertThrows(NullPointerException.class,
                () -> queue.offerAll(Collections.singletonList(null)));
    }

    @Test
    public void offerAll_fromEmptyInput_insertsNothing_returnsFalse() {
        assertThat(emptyQueue.offerAll(Collections.emptyList())).isFalse();
        assertThat(emptyQueue).isEmpty();

        assertThat(queue.offerAll(Collections.emptyList())).isFalse();
        assertThat(queue).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void offerAll_insertsAllElement_returnsTrue() {
        assertThat(emptyQueue.offerAll(VALUES)).isTrue();
        assertThat(emptyQueue).containsExactlyElementsIn(VALUES).inOrder();

        java.util.Queue<Integer> expectedQueue = new java.util.ArrayDeque<>(VALUES);
        VALUES.forEach(expectedQueue::offer);
        assertThat(queue.offerAll(VALUES)).isTrue();
        assertThat(queue).containsExactlyElementsIn(expectedQueue).inOrder();
    }

    @Test
    public void poll_onEmpty_removesNothing_returnsNull() {
        assertThat(emptyQueue.poll()).isNull();
        assertThat(emptyQueue).isEmpty();
    }

    @Test
    public void poll_removesFrontElement_returnsElement() {
        assertThat(emptyQueue.offer(1)).isTrue();
        assertThat(emptyQueue.poll()).isEqualTo(1);
        assertThat(emptyQueue).isEmpty();

        assertThat(queue.poll()).isEqualTo(VALUES.getFirst());
        assertThat(queue).containsExactlyElementsIn(VALUES.subList(1, VALUES.size())).inOrder();
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
    public void iterator_traversesFrontToRear() {
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
