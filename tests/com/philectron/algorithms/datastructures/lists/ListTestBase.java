package com.philectron.algorithms.datastructures.lists;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class ListTestBase {

    static final java.util.List<Integer> VALUES =
            java.util.List.of(100, 400, 700, 200, 500, 300, 600, 100);

    private List<Integer> list;
    private List<Integer> emptyList;

    abstract List<Integer> createList(Iterable<Integer> iterable);

    @BeforeEach
    void setUp() {
        list = createList(VALUES);
        emptyList = createList(Collections.emptyList());
        assertThat(list).containsExactlyElementsIn(VALUES).inOrder();
        assertThat(emptyList).isEmpty();
    }

    @Test
    void size_returnsNumberOfElements() {
        assertThat(emptyList.size()).isEqualTo(0);
        assertThat(list.size()).isEqualTo(VALUES.size());
    }

    @Test
    void isEmpty_checksSize() {
        assertThat(emptyList.isEmpty()).isTrue();
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    void get_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.getFirst());
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.getLast());

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(VALUES.size()));
    }

    @Test
    void get_returnsElementAtIndex() {
        final int midIndex = VALUES.size() / 2;
        assertThat(list.get(midIndex)).isEqualTo(VALUES.get(midIndex));
        assertThat(list.getFirst()).isEqualTo(VALUES.getFirst());
        assertThat(list.getLast()).isEqualTo(VALUES.getLast());
    }

    @Test
    void set_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.set(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.setFirst(1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.setLast(1));

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(VALUES.size(), 1));
    }

    @Test
    void set_replacesElement_returnsOldElement() {
        final int midIndex = VALUES.size() / 2;
        final int newValue = Collections.max(VALUES) + 1;

        assertThat(list.set(midIndex, newValue)).isEqualTo(VALUES.get(midIndex));
        assertThat(list.get(midIndex)).isEqualTo(newValue);

        assertThat(list.setFirst(newValue)).isEqualTo(VALUES.getFirst());
        assertThat(list.getFirst()).isEqualTo(newValue);

        assertThat(list.setLast(newValue)).isEqualTo(VALUES.getLast());
        assertThat(list.getLast()).isEqualTo(newValue);
    }

    @Test
    void add_positionOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.add(1, 1));
    }

    @Test
    void add_insertsElementAtPosition_shiftsRight() {
        emptyList.add(1);
        assertThat(emptyList).containsExactly(1);

        final int midPosition = VALUES.size() / 2;
        final int newValue = Collections.max(VALUES) + 1;
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);

        expectedList.add(midPosition, newValue);
        expectedList.add(newValue);
        expectedList.addFirst(newValue);
        expectedList.addLast(newValue);

        list.add(midPosition, newValue);
        assertThat(list.add(newValue)).isTrue();
        list.addFirst(newValue);
        list.addLast(newValue);
        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    void addAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    void addAll_fromEmptyInput_insertsNothing_returnsFalse() {
        assertThat(emptyList.addAll(Collections.emptyList())).isFalse();
        assertThat(emptyList).isEmpty();

        assertThat(list.addAll(Collections.emptyList())).isFalse();
        assertThat(list).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    void addAll_appendsAllElements_returnsTrue() {
        assertThat(emptyList.addAll(VALUES)).isTrue();
        assertThat(emptyList).containsExactlyElementsIn(VALUES).inOrder();

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addAll(VALUES);

        assertThat(list.addAll(VALUES)).isTrue();
        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    void indexOf_onEmpty_modifiesNothing_returnsNotFound() {
        assertThat(emptyList.indexOf(1)).isEqualTo(-1);
        assertThat(emptyList).isEmpty();
    }

    @Test
    void indexOf_nullElement_modifiesNothing_returnsFirstOccurrenceIndex() {
        // Before adding or setting null values, make sure this list does not have any.
        assertThat(list.indexOf(null)).isEqualTo(-1);

        // Choose the middle element of the list and make it null.
        final int initialExpectedFirstIndex = VALUES.size() / 2;
        list.set(initialExpectedFirstIndex, null);
        assertFirstIndexSearch(null, initialExpectedFirstIndex);
    }

    @Test
    void indexOf_modifiesNothing_returnsFirstOccurrenceIndex() {
        final int targetValue = VALUES.get(VALUES.size() / 2);
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
        list.addLast(targetValue);
        int actualFirstIndex = list.indexOf(targetValue);
        assertThat(actualFirstIndex).isNotEqualTo(list.size() - 1);
        assertThat(actualFirstIndex).isEqualTo(initialExpectedFirstIndex);

        // Add a duplicate value to the front and make sure the first index changes.
        list.addFirst(targetValue);
        actualFirstIndex = list.indexOf(targetValue);
        assertThat(actualFirstIndex).isNotEqualTo(initialExpectedFirstIndex);
        assertThat(actualFirstIndex).isEqualTo(0);
    }

    @Test
    void lastIndexOf_onEmpty_modifiesNothing_returnsNotFound() {
        assertThat(emptyList.lastIndexOf(1)).isEqualTo(-1);
        assertThat(emptyList).isEmpty();
    }

    @Test
    void lastIndexOf_nullElement_modifiesNothing_returnsLastOccurrenceIndex() {
        // Before adding or setting null values, make sure this list does not have any.
        assertThat(list.lastIndexOf(null)).isEqualTo(-1);

        // Choose the middle element of the list and make it null.
        final int initialExpectedLastIndex = VALUES.size() / 2;
        list.set(initialExpectedLastIndex, null);
        assertLastIndexSearch(null, initialExpectedLastIndex);
    }

    @Test
    void lastIndexOf_modifiesNothing_returnsLastOccurrence() {
        final int targetValue = VALUES.get(VALUES.size() / 2);
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
        list.addFirst(targetValue);
        int actualLastIndex = list.lastIndexOf(targetValue);
        assertThat(actualLastIndex).isNotEqualTo(0);
        assertThat(actualLastIndex).isEqualTo(initialExpectedLastIndex + 1);

        // Add a duplicate value to the back and make sure the last index changes.
        list.addLast(targetValue);
        actualLastIndex = list.lastIndexOf(targetValue);
        assertThat(actualLastIndex).isNotEqualTo(initialExpectedLastIndex + 1);
        assertThat(actualLastIndex).isEqualTo(list.size() - 1);
    }

    @Test
    void contains_modifiesNothing_checksElementExistence() {
        assertThat(emptyList.contains(1)).isFalse();
        assertThat(emptyList.contains(null)).isFalse();
        assertThat(list.contains(VALUES.getLast())).isTrue();
        assertThat(list.contains(Collections.max(VALUES) + 1)).isFalse();

        // Searching should not mutate the list.
        assertThat(emptyList).isEmpty();
        assertThat(list).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    void remove_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.removeFirst());
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.removeLast());
        assertThat(emptyList.remove(Integer.valueOf(0))).isFalse();
        assertThat(emptyList).isEmpty();

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(VALUES.size()));
    }

    @Test
    void remove_shiftsLeft_returnsRemovedElement() {
        final int midIndex = VALUES.size() / 2;

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        assertThat(list.remove(midIndex)).isEqualTo(expectedList.remove(midIndex));

        Integer maxValue = Collections.max(expectedList);

        expectedList.remove(maxValue);
        assertThat(list.remove(maxValue)).isTrue();
        assertThat(list.removeFirst()).isEqualTo(expectedList.removeFirst());
        assertThat(list.removeLast()).isEqualTo(expectedList.removeLast());

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

    @Test
    void removeAll_fromNullInput_fails() {
        assertThrows(NullPointerException.class, () -> list.removeAll(null));
    }

    @Test
    void removeAll_fromEmptyInput_removesNothing() {
        assertThat(emptyList.removeAll(Collections.emptyList())).isFalse();
        assertThat(emptyList).isEmpty();

        assertThat(list.removeAll(Collections.emptyList())).isFalse();
        assertThat(list).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    void removeAll_noCommonElements_removesNothing() {
        // Test for both null and non-null values.
        java.util.List<Integer> noCommonValues = new ArrayList<>();
        VALUES.forEach(value -> noCommonValues.add(-value));
        noCommonValues.add(null);

        assertThat(list.removeAll(noCommonValues)).isFalse();
        assertThat(list).containsExactlyElementsIn(VALUES).inOrder();
    }

    @Test
    void removeAll_removesAllCommonElements() {
        // Test for both null and non-null values.
        java.util.List<Integer> valuesToRemove = new ArrayList<>(VALUES);
        valuesToRemove.add(null);

        assertThat(list.addAll(Collections.nCopies(VALUES.size(), null))).isTrue();
        assertThat(list.removeAll(valuesToRemove)).isTrue();
        assertThat(list).isEmpty();
    }

    @Test
    void clear_removesAllElements() {
        emptyList.clear();
        assertThat(emptyList.isEmpty()).isTrue();
        assertThat(emptyList).isEmpty();

        list.clear();
        assertThat(list.isEmpty()).isTrue();
        assertThat(list).isEmpty();
    }

    @Test
    void reverse_flipsListOrder() {
        emptyList.reverse();
        assertThat(emptyList).isEmpty();

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
    void iterator_traversesStartToEnd() {
        Iterator<Integer> emptyIt = emptyList.iterator();
        assertThat(emptyIt.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, emptyIt::next);

        Iterator<Integer> it = list.iterator();
        for (int value : VALUES) {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(value);
        }
        assertThat(it.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, it::next);
    }

}
