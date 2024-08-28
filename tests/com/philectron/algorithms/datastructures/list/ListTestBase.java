package com.philectron.algorithms.datastructures.list;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class ListTestBase {

    private static final java.util.List<Integer> VALUES = java.util.List.of(1, 2, 3, 4, 5, 6, 7);

    private List<Integer> list;
    private List<Integer> emptyList;

    abstract List<Integer> createList(Iterable<Integer> iterable);

    @BeforeEach
    private void setUp() {
        list = createList(VALUES);
        emptyList = createList(Collections.emptyList());
        assertThat(list).containsExactlyElementsIn(VALUES).inOrder();
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
        assertThat(list.get(list.size() - 1)).isEqualTo(VALUES.get(VALUES.size() - 1));
    }

    @Test
    public void set_emptyList_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.set(0, 1));
    }

    @Test
    public void set_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(list.size(), 1));
    }

    @Test
    public void set_replacesValue_returnsOldValue() {
        final int index = list.size() - 1;
        final int expectedOldValue = list.get(index);
        final int expectedNewValue = expectedOldValue + 1;

        assertThat(list.set(index, expectedNewValue)).isEqualTo(expectedOldValue);
        assertThat(list.get(index)).isEqualTo(expectedNewValue);
    }

    @Test
    public void add_emptyList_insertsValue() {
        final int value = VALUES.get(0);
        emptyList.add(0, value);
        assertThat(emptyList).containsExactlyElementsIn(Collections.singletonList(value)).inOrder();
    }

    @Test
    public void add_positionOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.add(1, 1));
    }

    @Test
    public void add_insertsValueToPosition_shiftsListRight() {
        final int position = list.size() / 2;
        final int value = list.get(position);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.add(position, value);

        list.add(position, value);

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void addFront_insertsValueToFront_shiftsListRight() {
        final int value = list.get(0);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addFirst(value);

        list.addFront(value);

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void addBack_insertsValueToBack() {
        final int value = list.get(list.size() - 1);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addLast(value);

        list.addBack(value);

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void addAll_fromNullList_fails() {
        assertThrows(NullPointerException.class, () -> emptyList.addAll(null));
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    public void addAll_intoEmptyList_buildsSameListAsInput() {
        emptyList.addAll(VALUES);
        assertThat(list).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void addAll_intoExistingList_appendsInputList() {
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addAll(VALUES);

        list.addAll(VALUES);

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void indexOf_emptyList_returnsNotFound() {
        assertThat(emptyList.indexOf(VALUES.get(0))).isEqualTo(-1);
        // Searching should not mutate the list.
        assertThat(emptyList).isEmpty();
    }

    @Test
    public void indexOf_nullValue_returnsFirstOccurrence() {
        // Choose the middle element of the list and make it null.
        final int initialExpectedFirstIndex = list.size() / 2;
        list.set(initialExpectedFirstIndex, null);
        assertFirstIndexSearch(null, initialExpectedFirstIndex);
    }

    @Test
    public void indexOf_valueWithDuplicates_returnsFirstOccurrence() {
        final int targetValue = list.get(list.size() / 2);
        assertFirstIndexSearch(targetValue, VALUES.indexOf(targetValue));
    }

    private void assertFirstIndexSearch(Integer targetValue, int initialExpectedFirstIndex) {
        // Create a copy of the original list for comparison later.
        List<Integer> originalList = createList(list);

        // Test the initial search.
        assertThat(list.indexOf(targetValue)).isEqualTo(initialExpectedFirstIndex);

        // Searching should not mutate the list.
        assertThat(list).containsExactlyElementsIn(originalList).inOrder();

        // Add a duplicate value to the back and make sure the first index stays the same.
        list.addBack(targetValue);
        int actualFirstIndex = list.indexOf(targetValue);
        assertThat(actualFirstIndex).isNotEqualTo(list.size() - 1);
        assertThat(actualFirstIndex).isEqualTo(initialExpectedFirstIndex);

        // Add a duplicate value to the front and make sure the first index changes.
        list.addFront(targetValue);
        actualFirstIndex = list.indexOf(targetValue);
        assertThat(actualFirstIndex).isNotEqualTo(initialExpectedFirstIndex);
        assertThat(actualFirstIndex).isEqualTo(0);
    }

    @Test
    public void lastIndexOf_emptyList_returnsNotFound() {
        assertThat(emptyList.lastIndexOf(VALUES.get(0))).isEqualTo(-1);
        // Searching should not mutate the list.
        assertThat(emptyList).isEmpty();
    }

    @Test
    public void lastIndexOf_nullValue_returnsLastOccurrence() {
        // Choose the middle element of the list and make it null.
        final int initialExpectedLastIndex = list.size() / 2;
        list.set(initialExpectedLastIndex, null);
        assertLastIndexSearch(null, initialExpectedLastIndex);
    }

    @Test
    public void lastIndexOf_valueWithDuplicates_returnsLastOccurrence() {
        final int targetValue = list.get(list.size() / 2);
        assertLastIndexSearch(targetValue, VALUES.lastIndexOf(targetValue));

    }

    private void assertLastIndexSearch(Integer targetValue, int initialExpectedLastIndex) {
        // Create a copy of the original list for comparison later.
        List<Integer> originalList = createList(list);

        // Test the initial search.
        assertThat(list.lastIndexOf(targetValue)).isEqualTo(initialExpectedLastIndex);

        // Searching should not mutate the list.
        assertThat(list).containsExactlyElementsIn(originalList).inOrder();

        // Add a duplicate value to the front and make sure the last index shifts right by 1.
        list.addFront(targetValue);
        int actualLastIndex = list.lastIndexOf(targetValue);
        assertThat(actualLastIndex).isNotEqualTo(0);
        assertThat(actualLastIndex).isEqualTo(initialExpectedLastIndex + 1);

        // Add a duplicate value to the back and make sure the last index changes.
        list.addBack(targetValue);
        actualLastIndex = list.lastIndexOf(targetValue);
        assertThat(actualLastIndex).isNotEqualTo(initialExpectedLastIndex + 1);
        assertThat(actualLastIndex).isEqualTo(list.size() - 1);
    }

    @Test
    public void contains_checksValueInList() {
        List<Integer> originalList = createList(list);

        assertThat(emptyList.contains(1)).isFalse();
        assertThat(emptyList.contains(null)).isFalse();
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
        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void remove_thenAdd_resultsInSameList() {
        final int index = list.size() / 2;
        final int value = list.get(index);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.remove(index);
        expectedList.add(index, value);

        list.add(index, list.remove(index));

        assertThat(list.get(index)).isEqualTo(value);
        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void removeFront_shiftsListLeft_returnsDeletedValue() {
        final int value = list.get(0);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeFirst();

        assertThat(list.removeFront()).isEqualTo(value);
        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void removeFront_thenAddFront_resultsInSameList() {
        final int value = list.get(0);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeFirst();
        expectedList.addFirst(value);

        list.addFront(list.removeFront());

        assertThat(list.get(0)).isEqualTo(value);
        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void removeBack_returnsDeletedValue() {
        final int value = list.get(list.size() - 1);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeLast();

        assertThat(list.removeBack()).isEqualTo(value);
        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    public void removeBack_thenAddBack_resultsInSameList() {
        final int value = list.get(list.size() - 1);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeLast();
        expectedList.addLast(value);

        list.addBack(list.removeBack());

        assertThat(list.get(list.size() - 1)).isEqualTo(value);
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
    public void reverse_emptyList_doesNothing() {
        emptyList.reverse();
        assertThat(emptyList).isEmpty();
    }

    @Test
    public void reverse_flipsListOrder() {
        // Reverse the list and make sure the order is reversed.
        java.util.List<Integer> expectedReversedList = new ArrayList<>(VALUES);
        Collections.reverse(expectedReversedList);
        list.reverse();
        assertThat(list).containsExactlyElementsIn(expectedReversedList).inOrder();

        // Reverse the list again and make sure the order is back to original.
        list.reverse();
        assertThat(list).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    public void toJavaList_returnsEquivalentList() {
        assertThat(emptyList.toJavaList()).isEqualTo(Collections.emptyList());
        assertThat(list.toJavaList()).isEqualTo(VALUES);
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
    public void reverseIterator_traversesListBackward() {
        Iterator<Integer> emptyRit = emptyList.reverseIterator();
        assertThat(emptyRit.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> emptyRit.next());

        Iterator<Integer> rit = list.reverseIterator();
        for (int i = list.size() - 1; i >= 0; i--) {
            assertThat(rit.hasNext()).isTrue();
            assertThat(rit.next()).isEqualTo(VALUES.get(i));
        }
        assertThat(rit.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> rit.next());
    }

}
