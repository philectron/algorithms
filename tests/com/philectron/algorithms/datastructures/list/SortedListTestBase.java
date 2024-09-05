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

    private static final java.util.List<Integer> VALUES = java.util.List.of(1, 4, 7, 2, 5, 3, 6);

    private SortedList<Integer> list;
    private SortedList<Integer> emptyList;

    abstract SortedList<Integer> createSortedList(Iterable<Integer> iterable);

    @BeforeEach
    private void setUp() {
        list = createSortedList(VALUES);
        emptyList = createSortedList(Collections.emptyList());
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(VALUES);
        assertThat(emptyList).isEmpty();
    }

    @Test
    public void size_returnsNumberOfElements() {
        assertThat(emptyList.size()).isEqualTo(0);
        assertThat(list.size()).isEqualTo(VALUES.size());
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
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
    }

    @Test
    public void get_returnsValue() {
        java.util.List<Integer> sortedValues = VALUES.stream().sorted().toList();
        for (int i = 0; i < list.size(); i++) {
            assertThat(list.get(i)).isEqualTo(sortedValues.get(i));
        }
    }

    @Test
    public void add_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.add(null));
        assertThrows(NullPointerException.class, () -> list.add(null));
    }

    @Test
    public void add_emptyList_insertsValue() {
        final int value = VALUES.get(0);
        emptyList.add(value);
        assertThat(emptyList).isInOrder();
        assertThat(emptyList).containsExactlyElementsIn(Collections.singletonList(value));
    }

    @Test
    public void add_insertsValueToCorrectPosition_shiftsListRight() {
        final int value = list.get(list.size() / 2);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.add(value);

        list.add(value);

        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
    }

    @Test
    public void addAll_fromNullIterable_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.addAll(null));
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    public void addAll_intoEmptyList_buildsSameListAsInput() {
        emptyList.addAll(VALUES);
        assertThat(emptyList).isInOrder();
        assertThat(emptyList).containsExactlyElementsIn(VALUES);
    }

    @Test
    public void addAll_intoExistingList_appendsInputList() {
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addAll(VALUES);

        list.addAll(VALUES);

        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
    }

    @Test
    public void indexOf_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.indexOf(null));
        assertThrows(NullPointerException.class, () -> list.indexOf(null));
    }

    @Test
    public void indexOf_emptyList_returnsNotFound() {
        assertThat(emptyList.indexOf(VALUES.get(0))).isEqualTo(-1);
        // Searching should not mutate the list.
        assertThat(emptyList).isEmpty();
    }

    @Test
    public void indexOf_valueWithDuplicates_returnsFirstOccurrence() {
        final int targetValue = list.get(list.size() / 2);
        final int initialExpectedFirstIndex =
                VALUES.stream().sorted().toList().indexOf(targetValue);

        // Create a copy of the original list for comparison later.
        SortedList<Integer> originalList = createSortedList(list);

        // Test the initial search.
        assertThat(list.indexOf(targetValue)).isEqualTo(initialExpectedFirstIndex);

        // Searching should not mutate the list.
        assertThat(list).containsExactlyElementsIn(originalList).inOrder();

        // Add a duplicate value and make sure the first index stays the same.
        list.add(targetValue);
        assertThat(list.indexOf(targetValue)).isEqualTo(initialExpectedFirstIndex);
    }

    @Test
    public void lastIndexOf_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.lastIndexOf(null));
        assertThrows(NullPointerException.class, () -> list.lastIndexOf(null));
    }

    @Test
    public void lastIndexOf_emptyList_returnsNotFound() {
        assertThat(emptyList.lastIndexOf(VALUES.get(0))).isEqualTo(-1);
        // Searching should not mutate the list.
        assertThat(emptyList).isEmpty();
    }

    @Test
    public void lastIndexOf_valueWithDuplicates_returnsLastOccurrence() {
        final int targetValue = list.get(list.size() / 2);
        final int initialExpectedLastIndex =
                VALUES.stream().sorted().toList().lastIndexOf(targetValue);

        // Create a copy of the original list for comparison later.
        SortedList<Integer> originalList = createSortedList(list);

        // Test the initial search.
        assertThat(list.lastIndexOf(targetValue)).isEqualTo(initialExpectedLastIndex);

        // Searching should not mutate the list.
        assertThat(list).containsExactlyElementsIn(originalList).inOrder();

        // Add a duplicate value and make sure the last index shifts right by 1.
        list.add(targetValue);
        assertThat(list.lastIndexOf(targetValue)).isEqualTo(initialExpectedLastIndex + 1);
    }

    @Test
    public void contains_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.contains(null));
        assertThrows(NullPointerException.class, () -> list.contains(null));
    }

    @Test
    public void contains_checksValueInList() {
        SortedList<Integer> originalList = createSortedList(list);

        assertThat(emptyList.contains(1)).isFalse();
        assertThat(list.contains(list.get(list.size() - 1))).isTrue();
        assertThat(list.contains(Collections.max(VALUES) + 1)).isFalse();

        // Searching should not mutate the list.
        assertThat(emptyList).isEmpty();
        assertThat(list).containsExactlyElementsIn(originalList).inOrder();
    }

    @Test
    public void remove_emptyList_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.removeFront());
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.removeBack());
    }

    @Test
    public void remove_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
    }

    @Test
    public void remove_shiftsListLeft_returnsDeletedValue() {
        final int index = list.size() / 2;
        final int value = list.get(index);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.remove(index);

        assertThat(list.remove(index)).isEqualTo(value);
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
    }

    @Test
    public void remove_thenAdd_resultsInSameList() {
        final int index = list.size() / 2;
        final int value = list.get(index);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.remove(index);
        expectedList.add(index, value);

        list.add(list.remove(index));

        assertThat(list.get(index)).isEqualTo(value);
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
    }

    @Test
    public void removeFront_shiftsListLeft_returnsDeletedValue() {
        final int value = list.get(0);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeFirst();

        assertThat(list.removeFront()).isEqualTo(value);
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
    }

    @Test
    public void removeFront_thenAdd_resultsInSameList() {
        final int value = list.get(0);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeFirst();
        expectedList.addFirst(value);

        list.add(list.removeFront());

        assertThat(list.get(0)).isEqualTo(value);
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
    }

    @Test
    public void removeBack_returnsDeletedValue() {
        final int value = list.get(list.size() - 1);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeLast();

        assertThat(list.removeBack()).isEqualTo(value);
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
    }

    @Test
    public void removeBack_thenAdd_resultsInSameList() {
        final int value = list.get(list.size() - 1);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeLast();
        expectedList.addLast(value);

        list.add(list.removeBack());

        assertThat(list.get(list.size() - 1)).isEqualTo(value);
        assertThat(list).isInOrder();
        assertThat(list).containsExactlyElementsIn(expectedList);
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
        for (int i = 0; i < list.size(); i++) {
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
