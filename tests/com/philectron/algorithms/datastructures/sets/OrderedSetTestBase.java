package com.philectron.algorithms.datastructures.sets;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.OrderedSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class OrderedSetTestBase {

    private static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);
    private static final List<Integer> VALUES_UNIQUE_SORTED =
            VALUES.stream().distinct().sorted().toList();

    private OrderedSet<Integer> set;
    private OrderedSet<Integer> emptySet;

    abstract OrderedSet<Integer> createOrderedSet(Iterable<Integer> iterable);

    @BeforeEach
    private void setUp() {
        set = createOrderedSet(VALUES);
        emptySet = createOrderedSet(Collections.emptyList());
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
        assertThat(emptySet).isEmpty();
    }

    @Test
    public void size_returnsNumberOfElements() {
        assertThat(emptySet.size()).isEqualTo(0);
        assertThat(set.size()).isEqualTo(VALUES_UNIQUE_SORTED.size());
    }

    @Test
    public void isEmpty_checksSetSize() {
        assertThat(emptySet.isEmpty()).isTrue();
        assertThat(set.isEmpty()).isFalse();
    }

    @Test
    public void add_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptySet.add(null));
        assertThrows(NullPointerException.class, () -> set.add(null));
    }

    @Test
    public void add_emptySet_insertsValue() {
        final int value = 1;
        assertThat(emptySet.add(value)).isTrue();
        assertThat(emptySet).containsExactlyElementsIn(Collections.singletonList(value)).inOrder();
    }

    @Test
    public void add_newValue_insertsToCorrectPosition() {
        // Plus or minus 1 to ensure no duplicates.
        final int smallValue = VALUES_UNIQUE_SORTED.getFirst() - 1;
        final int largeValue = VALUES_UNIQUE_SORTED.getLast() + 1;
        final int midValue = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2) + 1;

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);

        // Test with small value insertion.
        expectedSet.add(smallValue);
        assertThat(set.add(smallValue)).isTrue();
        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();

        // Test with large value insertion.
        expectedSet.add(largeValue);
        assertThat(set.add(largeValue)).isTrue();
        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();

        // Test with mid value insertion.
        expectedSet.add(midValue);
        assertThat(set.add(midValue)).isTrue();
        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void add_alreadyExistingValue_doesNothing() {
        assertThat(set.add(VALUES_UNIQUE_SORTED.getLast())).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void addAll_fromNullIterable_fails() {
        assertThrows(NullPointerException.class, () -> emptySet.addAll(null));
        assertThrows(NullPointerException.class, () -> set.addAll(null));
    }

    @Test
    public void addAll_intoEmptySet_buildsSameSetAsInput() {
        assertThat(emptySet.addAll(VALUES_UNIQUE_SORTED)).isTrue();
        assertThat(emptySet).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void addAll_intoExistingSet_insertsNewValuesInOrder() {
        // The list to insert will contain some duplicate values and some new values.
        List<Integer> newValues = new ArrayList<>(VALUES);
        VALUES.forEach(value -> newValues.add(-value));

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES);
        expectedSet.addAll(newValues);

        assertThat(set.addAll(newValues)).isTrue();

        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void addAll_alreadyExistingValues_doesNothing() {
        // As long as the new values are all duplicates that already exist in the list, the list
        // will not add them.
        assertThat(set.addAll(VALUES_UNIQUE_SORTED)).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void contains_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptySet.contains(null));
        assertThrows(NullPointerException.class, () -> set.contains(null));
    }

    @Test
    public void contains_checksValueInSet() {
        assertThat(emptySet.contains(1)).isFalse();
        assertThat(set.contains(VALUES_UNIQUE_SORTED.getLast())).isTrue();
        assertThat(set.contains(VALUES_UNIQUE_SORTED.getFirst() - 1)).isFalse();
        assertThat(set.contains(VALUES_UNIQUE_SORTED.getLast() + 1)).isFalse();

        // Searching should not mutate the list.
        assertThat(emptySet).isEmpty();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void remove_byIndex_emptySet_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptySet.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptySet.removeFront());
        assertThrows(IndexOutOfBoundsException.class, () -> emptySet.removeBack());
    }

    @Test
    public void remove_byIndex_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> set.remove(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> set.remove(VALUES_UNIQUE_SORTED.size()));
    }

    @Test
    public void remove_byIndex_returnsDeletedValue() {
        final int index = VALUES_UNIQUE_SORTED.size() / 2;
        final int value = VALUES_UNIQUE_SORTED.get(index);

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.remove(value);

        assertThat(set.remove(index)).isEqualTo(value);

        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void remove_byIndex_thenAdd_resultsInSameSet() {
        assertThat(set.add(set.remove(VALUES_UNIQUE_SORTED.size() / 2))).isTrue();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void removeFront_returnsDeletedValue() {
        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.removeFirst();

        assertThat(set.removeFront()).isEqualTo(VALUES_UNIQUE_SORTED.getFirst());

        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void removeFront_thenAdd_resultsInSameSet() {
        assertThat(set.add(set.removeFront())).isTrue();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void removeBack_returnsDeletedValue() {
        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.removeLast();

        assertThat(set.removeBack()).isEqualTo(VALUES_UNIQUE_SORTED.getLast());

        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void removeBack_thenAdd_resultsInSameSet() {
        assertThat(set.add(set.removeBack())).isTrue();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void remove_byValue() {
        final int value = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2);

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.remove(value);

        assertThat(set.remove(Integer.valueOf(value))).isTrue();

        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void remove_byValue_valueNotExist_doesNothing() {
        assertThat(emptySet.remove(Integer.valueOf(1))).isFalse();
        assertThat(emptySet).isEmpty();

        assertThat(set.remove(Integer.valueOf(VALUES_UNIQUE_SORTED.getFirst() + 1))).isFalse();
        assertThat(set.remove(Integer.valueOf(VALUES_UNIQUE_SORTED.getLast() + 1))).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void remove_byValue_thenAdd_resultsInSameSet() {
        final int value = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2);

        assertThat(set.remove(Integer.valueOf(value))).isTrue();
        assertThat(set.add(value)).isTrue();

        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void clear_removesAllElements() {
        emptySet.clear();
        assertThat(emptySet.isEmpty()).isTrue();
        assertThat(emptySet).isEmpty();

        set.clear();
        assertThat(set.isEmpty()).isTrue();
        assertThat(set).isEmpty();
    }

    @Test
    public void iterator_traversesSetForward() {
        Iterator<Integer> emptyIt = emptySet.iterator();
        assertThat(emptyIt.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyIt.next());

        Iterator<Integer> it = set.iterator();
        VALUES_UNIQUE_SORTED.forEach(value -> {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(value);
        });
        assertThat(it.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void toString_returnsStringContainingAllElements() {
        String setToString = set.toString();
        VALUES_UNIQUE_SORTED.forEach(value -> assertThat(setToString).contains(value.toString()));
    }

}
