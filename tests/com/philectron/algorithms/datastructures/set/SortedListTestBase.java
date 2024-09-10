package com.philectron.algorithms.datastructures.set;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class SortedListTestBase {

    private static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);
    private static final List<Integer> VALUES_UNIQUE_SORTED =
            VALUES.stream().distinct().sorted().toList();

    private SortedList<Integer> list;
    private SortedList<Integer> emptyList;

    abstract SortedList<Integer> createSortedList(Iterable<Integer> iterable);

    @BeforeEach
    private void setUp() {
        list = createSortedList(VALUES);
        emptyList = createSortedList(Collections.emptyList());
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
        assertThat(emptyList).isEmpty();
    }

    @Test
    public void size_returnsNumberOfElements() {
        assertThat(emptyList.size()).isEqualTo(0);
        assertThat(list.size()).isEqualTo(VALUES_UNIQUE_SORTED.size());
    }

    @Test
    public void isEmpty_checksListSize() {
        assertThat(emptyList.isEmpty()).isTrue();
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    public void get_emptyList_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
    }

    @Test
    public void get_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(VALUES_UNIQUE_SORTED.size()));
    }

    @Test
    public void get_returnsValue() {
        for (int i = 0; i < VALUES_UNIQUE_SORTED.size(); i++) {
            assertThat(list.get(i)).isEqualTo(VALUES_UNIQUE_SORTED.get(i));
        }
    }

    @Test
    public void add_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.add(null));
        assertThrows(NullPointerException.class, () -> list.add(null));
    }

    @Test
    public void add_emptyList_insertsValue() {
        final int value = 1;
        assertThat(emptyList.add(value)).isTrue();
        assertThat(emptyList).containsExactlyElementsIn(Collections.singletonList(value)).inOrder();
    }

    @Test
    public void add_newValue_insertsToCorrectPosition_shiftsListRight() {
        // Plus or minus 1 to ensure no duplicates.
        final int smallValue = VALUES_UNIQUE_SORTED.getFirst() - 1;
        final int largeValue = VALUES_UNIQUE_SORTED.getLast() + 1;
        final int midValue = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2) + 1;

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);

        // Test with small value insertion.
        expectedSet.add(smallValue);
        assertThat(list.add(smallValue)).isTrue();
        assertThat(list).containsExactlyElementsIn(expectedSet).inOrder();

        // Test with large value insertion.
        expectedSet.add(largeValue);
        assertThat(list.add(largeValue)).isTrue();
        assertThat(list).containsExactlyElementsIn(expectedSet).inOrder();

        // Test with mid value insertion.
        expectedSet.add(midValue);
        assertThat(list.add(midValue)).isTrue();
        assertThat(list).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void add_alreadyExistingValue_doesNothing() {
        assertThat(list.add(VALUES_UNIQUE_SORTED.getLast())).isFalse();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void addAll_fromNullIterable_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.addAll(null));
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    public void addAll_intoEmptyList_buildsSameListAsInput() {
        assertThat(emptyList.addAll(VALUES_UNIQUE_SORTED)).isTrue();
        assertThat(emptyList).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void addAll_intoExistingList_insertsNewValuesInOrder() {
        // The list to insert will contain some duplicate values and some new values.
        List<Integer> newValues = new ArrayList<>(VALUES);
        VALUES.forEach(value -> newValues.add(-value));

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES);
        expectedSet.addAll(newValues);

        assertThat(list.addAll(newValues)).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void addAll_alreadyExistingValues_doesNothing() {
        // As long as the new values are all duplicates that already exist in the list, the list
        // will not add them.
        assertThat(list.addAll(VALUES_UNIQUE_SORTED)).isFalse();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void indexOf_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.indexOf(null));
        assertThrows(NullPointerException.class, () -> list.indexOf(null));
    }

    @Test
    public void indexOf_emptyList_returnsNotFound() {
        assertThat(emptyList.indexOf(1)).isEqualTo(-1);
        // Searching should not mutate the list.
        assertThat(emptyList).isEmpty();
    }

    @Test
    public void indexOf_valueInList_returnsIndex() {
        final int targetValue = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2);

        // Test the initial searches.
        for (int i = 0; i < VALUES_UNIQUE_SORTED.size(); i++) {
            assertThat(list.indexOf(VALUES_UNIQUE_SORTED.get(i))).isEqualTo(i);
        }

        // Searching should not mutate the list.
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();

        // Add a duplicate value and make sure the first index stays the same.
        assertThat(list.add(targetValue)).isFalse();
        for (int i = 0; i < VALUES_UNIQUE_SORTED.size(); i++) {
            assertThat(list.indexOf(VALUES_UNIQUE_SORTED.get(i))).isEqualTo(i);
        }
    }

    @Test
    public void contains_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.contains(null));
        assertThrows(NullPointerException.class, () -> list.contains(null));
    }

    @Test
    public void contains_checksValueInList() {
        assertThat(emptyList.contains(1)).isFalse();
        assertThat(list.contains(VALUES_UNIQUE_SORTED.getLast())).isTrue();
        assertThat(list.contains(VALUES_UNIQUE_SORTED.getFirst() - 1)).isFalse();
        assertThat(list.contains(VALUES_UNIQUE_SORTED.getLast() + 1)).isFalse();

        // Searching should not mutate the list.
        assertThat(emptyList).isEmpty();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void remove_byIndex_emptyList_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.removeFront());
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.removeBack());
    }

    @Test
    public void remove_byIndex_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(VALUES_UNIQUE_SORTED.size()));
    }

    @Test
    public void remove_byIndex_shiftsListLeft_returnsDeletedValue() {
        final int index = VALUES_UNIQUE_SORTED.size() / 2;
        final int value = VALUES_UNIQUE_SORTED.get(index);

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.remove(value);

        assertThat(list.remove(index)).isEqualTo(value);

        assertThat(list).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void remove_byIndex_thenAdd_resultsInSameList() {
        assertThat(list.add(list.remove(VALUES_UNIQUE_SORTED.size() / 2))).isTrue();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void removeFront_shiftsListLeft_returnsDeletedValue() {
        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.removeFirst();

        assertThat(list.removeFront()).isEqualTo(VALUES_UNIQUE_SORTED.getFirst());

        assertThat(list).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void removeFront_thenAdd_resultsInSameList() {
        assertThat(list.add(list.removeFront())).isTrue();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void removeBack_returnsDeletedValue() {
        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.removeLast();

        assertThat(list.removeBack()).isEqualTo(VALUES_UNIQUE_SORTED.getLast());

        assertThat(list).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void removeBack_thenAdd_resultsInSameList() {
        assertThat(list.add(list.removeBack())).isTrue();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void remove_byValue_shiftsListLeft() {
        final int value = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2);

        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);
        expectedSet.remove(value);

        assertThat(list.remove(Integer.valueOf(value))).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedSet).inOrder();
    }

    @Test
    public void remove_byValue_valueNotExist_doesNothing() {
        assertThat(emptyList.remove(Integer.valueOf(1))).isFalse();
        assertThat(emptyList).isEmpty();

        assertThat(list.remove(Integer.valueOf(VALUES_UNIQUE_SORTED.getFirst() + 1))).isFalse();
        assertThat(list.remove(Integer.valueOf(VALUES_UNIQUE_SORTED.getLast() + 1))).isFalse();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void remove_byValue_thenAdd_resultsInSameList() {
        final int value = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2);

        assertThat(list.remove(Integer.valueOf(value))).isTrue();
        assertThat(list.add(value)).isTrue();

        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void clear_removesAllElements() {
        emptyList.clear();
        assertThat(emptyList.isEmpty()).isTrue();
        assertThat(emptyList).isEmpty();

        list.clear();
        assertThat(list.isEmpty()).isTrue();
        assertThat(list).isEmpty();
    }

    @Test
    public void iterator_traversesListForward() {
        Iterator<Integer> emptyIt = emptyList.iterator();
        assertThat(emptyIt.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyIt.next());

        Iterator<Integer> it = list.iterator();
        VALUES_UNIQUE_SORTED.forEach(value -> {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(value);
        });
        assertThat(it.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void toString_returnsStringContainingAllElements() {
        String listToString = list.toString();
        VALUES_UNIQUE_SORTED.forEach(value -> assertThat(listToString).contains(value.toString()));
    }

}
