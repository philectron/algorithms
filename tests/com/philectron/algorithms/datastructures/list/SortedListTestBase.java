package com.philectron.algorithms.datastructures.list;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class SortedListTestBase {

    private static final java.util.List<Integer> VALUES =
            java.util.List.of(100, 400, 700, 200, 500, 300, 600, 100);
    private static final java.util.List<Integer> VALUES_UNIQUE_SORTED =
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
    public void add_uniqueValue_insertsToCorrectPosition_shiftsListRight() {
        // Plus or minus 1 to ensure no duplicates.
        final int smallValue = Collections.min(VALUES_UNIQUE_SORTED) - 1;
        final int largeValue = Collections.max(VALUES_UNIQUE_SORTED) + 1;
        final int midValue = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() / 2) + 1;

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);

        // Test with small value insertion.
        expectedList.add(smallValue);
        assertThat(list.add(smallValue)).isTrue();
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);

        // Test with large value insertion.
        expectedList.add(largeValue);
        assertThat(list.add(largeValue)).isTrue();
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);

        // Test with mid value insertion.
        expectedList.add(midValue);
        assertThat(list.add(midValue)).isTrue();
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
    }

    @Test
    public void add_duplicateValue_doesNothing() {
        assertThat(list.add(VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() - 1))).isFalse();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void addAll_fromNullIterable_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.addAll(null));
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    public void addAll_intoEmptyList_buildsSameListAsInput() {
        assertThat(emptyList.addAll(VALUES)).isTrue();
        assertThat(emptyList).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void addAll_intoExistingList_insertsUniqueElementsInOrder() {
        // The list to insert will contain some duplicate values and some new values.
        java.util.List<Integer> newValues = new ArrayList<>(VALUES);
        VALUES.forEach(value -> newValues.add(-value));

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addAll(newValues);
        expectedList = expectedList.stream().distinct().sorted().toList();

        assertThat(list.addAll(newValues)).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void addAll_fromIterableWithOnlyDuplicates_doesNothing() {
        // As long as the new values are all duplicates that already exist in the list, the list
        // will not add them.
        assertThat(list.addAll(VALUES)).isFalse();
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
        final int initialExpectedFirstIndex = VALUES_UNIQUE_SORTED.indexOf(targetValue);

        // Test the initial search.
        assertThat(list.indexOf(targetValue)).isEqualTo(initialExpectedFirstIndex);

        // Searching should not mutate the list.
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();

        // Add a duplicate value and make sure the first index stays the same.
        assertThat(list.add(targetValue)).isFalse();
        assertThat(list.indexOf(targetValue)).isEqualTo(initialExpectedFirstIndex);
    }

    @Test
    public void contains_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.contains(null));
        assertThrows(NullPointerException.class, () -> list.contains(null));
    }

    @Test
    public void contains_checksValueInList() {
        assertThat(emptyList.contains(1)).isFalse();
        assertThat(list.contains(VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() - 1)))
                .isTrue();
        assertThat(list.contains(Collections.max(VALUES) + 1)).isFalse();

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

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);
        expectedList.remove(index);

        assertThat(list.remove(index)).isEqualTo(value);

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void remove_byIndex_thenAdd_resultsInSameList() {
        final int index = VALUES_UNIQUE_SORTED.size() / 2;
        final int value = VALUES_UNIQUE_SORTED.get(index);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);
        expectedList.remove(index);
        expectedList.add(index, value);

        assertThat(list.add(list.remove(index))).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void removeFront_shiftsListLeft_returnsDeletedValue() {
        final int value = VALUES_UNIQUE_SORTED.get(0);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);
        expectedList.removeFirst();

        assertThat(list.removeFront()).isEqualTo(value);

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void removeFront_thenAdd_resultsInSameList() {
        final int value = VALUES_UNIQUE_SORTED.get(0);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);
        expectedList.removeFirst();
        expectedList.addFirst(value);

        assertThat(list.add(list.removeFront())).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void removeBack_returnsDeletedValue() {
        final int value = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() - 1);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);
        expectedList.removeLast();

        assertThat(list.removeBack()).isEqualTo(value);

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void removeBack_thenAdd_resultsInSameList() {
        final int value = VALUES_UNIQUE_SORTED.get(VALUES_UNIQUE_SORTED.size() - 1);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);
        expectedList.removeLast();
        expectedList.addLast(value);

        assertThat(list.add(list.removeBack())).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void remove_byValue_shiftsListLeft() {
        final int index = VALUES_UNIQUE_SORTED.size() / 2;
        final int value = VALUES_UNIQUE_SORTED.get(index);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);
        expectedList.remove(index);

        assertThat(list.remove(Integer.valueOf(value))).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void remove_byValue_valueNotExist_doesNothing() {
        assertThat(emptyList.remove(Integer.valueOf(1))).isFalse();
        assertThat(emptyList).isEmpty();

        final int value = Collections.max(VALUES_UNIQUE_SORTED) + 1;
        assertThat(list.remove(Integer.valueOf(value))).isFalse();
        assertThat(list).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    public void remove_byValue_thenAdd_resultsInSameList() {
        final int index = VALUES_UNIQUE_SORTED.size() / 2;
        final int value = VALUES_UNIQUE_SORTED.get(index);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES_UNIQUE_SORTED);
        expectedList.remove(index);
        expectedList.add(index, value);

        assertThat(list.remove(Integer.valueOf(value))).isTrue();
        assertThat(list.add(value)).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
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
        for (int i = 0; i < VALUES_UNIQUE_SORTED.size(); i++) {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(VALUES.get(i));
        }
        assertThat(it.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void toString_returnsStringContainingAllElements() {
        String listToString = list.toString();
        for (Integer value : VALUES) {
            assertThat(listToString).contains(value.toString());
        }
    }

}
