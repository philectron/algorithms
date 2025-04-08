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
import org.junit.jupiter.api.Test;

public abstract class OrderedSetTestBase {

    static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);
    static final List<Integer> VALUES_UNIQUE_SORTED = VALUES.stream().distinct().sorted().toList();

    private OrderedSet<Integer> set;
    private OrderedSet<Integer> emptySet;

    abstract OrderedSet<Integer> createOrderedSet(Iterable<Integer> iterable);

    void setUp() {
        set = createOrderedSet(VALUES);
        emptySet = createOrderedSet(Collections.emptyList());
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
        assertThat(emptySet).isEmpty();
    }

    @Test
    void size_returnsNumberOfElements() {
        assertThat(emptySet.size()).isEqualTo(0);
        assertThat(set.size()).isEqualTo(VALUES_UNIQUE_SORTED.size());
    }

    @Test
    void isEmpty_checksSetSize() {
        assertThat(emptySet.isEmpty()).isTrue();
        assertThat(set.isEmpty()).isFalse();
    }

    @Test
    void get_emptySet_fails() {
        assertThrows(NoSuchElementException.class, () -> emptySet.getFirst());
        assertThrows(NoSuchElementException.class, () -> emptySet.getLast());
    }

    @Test
    void get_returnsElement() {
        assertThat(set.getFirst()).isEqualTo(VALUES_UNIQUE_SORTED.getFirst());
        assertThat(set.getLast()).isEqualTo(VALUES_UNIQUE_SORTED.getLast());
    }

    @Test
    void add_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptySet.add(null));
        assertThrows(NullPointerException.class, () -> set.add(null));
    }

    @Test
    void add_emptySet_insertsElement() {
        final int value = 1;
        assertThat(emptySet.add(value)).isTrue();
        assertThat(emptySet).containsExactlyElementsIn(Collections.singletonList(value)).inOrder();
    }

    @Test
    void add_duplicateElement_doesNothing() {
        assertThat(set.add(VALUES_UNIQUE_SORTED.getLast())).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void add_newElement_insertsAtCorrectPosition() {
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
    void addAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptySet.addAll(null));
        assertThrows(NullPointerException.class, () -> set.addAll(null));
    }

    @Test
    void addAll_fromInputWithNull_fails() {
        assertThrows(NullPointerException.class,
                () -> emptySet.addAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class, () -> set.addAll(Collections.singletonList(null)));
    }

    @Test
    void addAll_fromEmptyInput_doesNothing() {
        assertThat(emptySet.addAll(Collections.emptyList())).isFalse();
        assertThat(emptySet).isEmpty();
        assertThat(set.addAll(Collections.emptyList())).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void addAll_intoEmptySet_buildsSameSet() {
        assertThat(emptySet.addAll(VALUES_UNIQUE_SORTED)).isTrue();
        assertThat(emptySet).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void addAll_intoExistingSet_insertsOnlyDistinctElementsAtCorrectOrders() {
        // The list to insert will contain some duplicate values and some new values.
        List<Integer> newValues = new ArrayList<>(VALUES);
        VALUES.forEach(value -> newValues.add(-value));

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES);
        expectedSet.addAll(newValues);

        assertThat(set.addAll(newValues)).isTrue();

        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    void addAll_withSameElements_doesNothing() {
        // As long as the new values are all duplicates that already exist in the list, the list
        // will not add them.
        assertThat(set.addAll(VALUES_UNIQUE_SORTED)).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void contains_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptySet.contains(null));
        assertThrows(NullPointerException.class, () -> set.contains(null));
    }

    @Test
    void contains_checksElementInSet() {
        assertThat(emptySet.contains(1)).isFalse();
        assertThat(set.contains(VALUES_UNIQUE_SORTED.getLast())).isTrue();
        assertThat(set.contains(VALUES_UNIQUE_SORTED.getFirst() - 1)).isFalse();
        assertThat(set.contains(VALUES_UNIQUE_SORTED.getLast() + 1)).isFalse();

        // Searching should not mutate the list.
        assertThat(emptySet).isEmpty();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void remove_emptySet_doesNothing() {
        assertThat(emptySet.remove(1)).isFalse();
        assertThat(emptySet).isEmpty();
    }

    @Test
    void remove_nonExistingElement_doesNothing() {
        assertThat(set.remove(VALUES_UNIQUE_SORTED.getFirst() + 1)).isFalse();
        assertThat(set.remove(VALUES_UNIQUE_SORTED.getLast() + 1)).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void remove_returnsDeletedElement() {
        final int oldValue = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2);

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.remove(oldValue);

        assertThat(set.remove(oldValue)).isTrue();

        assertThat(set).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    void remove_thenAddSameElement_resultsInSameSet() {
        final int value = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2);
        assertThat(set.remove(value)).isTrue();
        assertThat(set.add(value)).isTrue();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void removeAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> emptySet.removeAll(null));
        assertThrows(NullPointerException.class, () -> set.removeAll(null));
    }

    @Test
    void removeAll_fromInputWithNullElements_fails() {
        assertThrows(NullPointerException.class,
                () -> emptySet.removeAll(Collections.singletonList(null)));
        assertThrows(NullPointerException.class,
                () -> set.removeAll(Collections.singletonList(null)));
    }

    @Test
    void removeAll_fromEmptyInput_doesNothing() {
        assertThat(emptySet.removeAll(Collections.emptyList())).isFalse();
        assertThat(emptySet).isEmpty();
        assertThat(set.removeAll(Collections.emptyList())).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void removeAll_noCommonElements_doesNothing() {
        List<Integer> noCommonValues = VALUES.stream().map(value -> -value).toList();
        assertThat(set.removeAll(noCommonValues)).isFalse();
        assertThat(set).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void removeAll_deletesAllCommonElements() {
        assertThat(set.removeAll(VALUES)).isTrue();
        assertThat(set).isEmpty();
    }

    @Test
    void clear_removesAllElements() {
        emptySet.clear();
        assertThat(emptySet.isEmpty()).isTrue();
        assertThat(emptySet).isEmpty();

        set.clear();
        assertThat(set.isEmpty()).isTrue();
        assertThat(set).isEmpty();
    }

    @Test
    void iterator_traversesSetForward() {
        Iterator<Integer> emptyIt = emptySet.iterator();
        assertThat(emptyIt.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, emptyIt::next);

        Iterator<Integer> it = set.iterator();
        VALUES_UNIQUE_SORTED.forEach(value -> {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(value);
        });
        assertThat(it.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void toString_returnsStringContainingAllElements() {
        String setToString = set.toString();
        VALUES_UNIQUE_SORTED.forEach(value -> assertThat(setToString).contains(value.toString()));
    }

}
