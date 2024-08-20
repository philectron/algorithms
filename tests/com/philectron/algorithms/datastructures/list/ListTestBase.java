package com.philectron.algorithms.datastructures.list;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class ListTestBase {

    static final java.util.List<Integer> VALUES = java.util.List.of(1, 2, 3, 4, 5);

    private List<Integer> list;
    private List<Integer> emptyList;

    abstract List<Integer> createListWithValues();

    abstract List<Integer> createEmptyList();

    @BeforeEach
    public void setUp() {
        list = createListWithValues();
        emptyList = createEmptyList();
    }

    @Test
    public void get_emptyList_fails() {
        assertThat(emptyList.isEmpty()).isTrue();
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
    }

    @Test
    public void get_indexOutOfBound_fails() {
        assertThat(list.size()).isEqualTo(VALUES.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(VALUES.size()));
    }

    @Test
    public void get_indexInBound_returnsValue() {
        assertThat(list.size()).isEqualTo(VALUES.size());
        final int index = VALUES.size() - 1;
        assertThat(list.get(index)).isEqualTo(VALUES.get(index));
    }

    @Test
    public void set_emptyList_fails() {
        assertThat(emptyList.isEmpty()).isTrue();
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.set(0, 1));
    }

    @Test
    public void set_indexOutOfBound_fails() {
        assertThat(list.size()).isEqualTo(VALUES.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(VALUES.size(), 1));
    }

    @Test
    public void set_indexInBound_replacesValue_returnsOldValue() {
        assertThat(list.size()).isEqualTo(VALUES.size());

        final int index = VALUES.size() - 1;
        final int expectedOldValue = list.get(index);
        final int expectedNewValue = expectedOldValue + 1;

        assertThat(list.set(index, expectedNewValue)).isEqualTo(expectedOldValue);
        assertThat(list.get(index)).isEqualTo(expectedNewValue);
    }

    @Test
    public void add_emptyList_insertsValue() {
        final int value = VALUES.get(0);
        emptyList.add(0, value);
        assertThat(emptyList.toJavaList()).isEqualTo(Collections.singletonList(value));
    }

    @Test
    public void add_positionOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.add(1, 1));
    }

    @Test
    public void add_insertsValueToPosition_shiftsListRight() {
        final int position = VALUES.size() / 2;
        final int value = VALUES.get(position);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.add(position, value);

        list.add(position, value);

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void addFront_insertsValueToFront_shiftsListRight() {
        final int value = VALUES.get(0);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addFirst(value);

        list.addFront(value);

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void addBack_insertsValueToBack() {
        final int value = VALUES.get(VALUES.size() - 1);

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addLast(value);

        list.addBack(value);

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void indexOf_emptyList_returnsNotFound() {
        assertThat(emptyList.indexOf(VALUES.get(0))).isEqualTo(-1);
    }

    @Test
    public void indexOf_valueWithDuplicates_returnsFirstOccurrence() {
        final int targetValue = VALUES.get(VALUES.size() - 1);
        final int initialFirstIndex = VALUES.indexOf(targetValue);

        assertThat(list.indexOf(targetValue)).isEqualTo(initialFirstIndex);

        // Add a duplicate value to the back and make sure the first index stays the same.
        list.addBack(targetValue);
        int actualFirstIndex = list.indexOf(targetValue);
        assertThat(actualFirstIndex).isNotEqualTo(list.size() - 1);
        assertThat(actualFirstIndex).isEqualTo(initialFirstIndex);

        // Add a duplicate value to the front and make sure the first index changes.
        list.addFront(targetValue);
        actualFirstIndex = list.indexOf(targetValue);
        assertThat(actualFirstIndex).isNotEqualTo(initialFirstIndex);
        assertThat(actualFirstIndex).isEqualTo(0);
    }

    @Test
    public void lastIndexOf_emptyList_returnsNotFound() {
        assertThat(emptyList.lastIndexOf(VALUES.get(0))).isEqualTo(-1);
    }

    @Test
    public void lastIndexOf_valueWithDuplicates_returnsLastOccurrence() {
        final int targetValue = VALUES.get(0);
        final int initialLastIndex = VALUES.lastIndexOf(targetValue);

        assertThat(list.lastIndexOf(targetValue)).isEqualTo(initialLastIndex);

        // Add a duplicate value to the front and make sure the last index changes.
        list.addFront(targetValue);
        int actualLastIndex = list.lastIndexOf(targetValue);
        assertThat(actualLastIndex).isNotEqualTo(0);
        assertThat(actualLastIndex).isEqualTo(initialLastIndex + 1);

        // Add a duplicate value to the back and make sure the last index changes.
        list.addBack(targetValue);
        actualLastIndex = list.lastIndexOf(targetValue);
        assertThat(actualLastIndex).isNotEqualTo(initialLastIndex + 1);
        assertThat(actualLastIndex).isEqualTo(list.size() - 1);
    }

    @Test
    public void contains_emptyList_returnsFalse() {
        assertThat(emptyList.contains(1)).isFalse();
    }

    @Test
    public void contains_searchesAndReturnsResult() {
        assertThat(list.contains(VALUES.get(0))).isTrue();
        assertThat(list.contains(Collections.max(VALUES) + 1)).isFalse();
    }

    @Test
    public void remove_emptyList_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.remove(0));
    }

    @Test
    public void remove_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(VALUES.size()));
    }

    @Test
    public void remove_deletesValueAtIndex_shiftsListLeft() {
        final int index = VALUES.size() / 2;

        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.remove(index);

        list.remove(index);

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void removeFront_deletesValueFromFront_shiftsListLeft() {
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeFirst();

        list.removeFront();

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void removeBack_deletesValueFromBack() {
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeLast();

        list.removeBack();

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void clear_removesEverything() {
        list.clear();
        assertThat(list.isEmpty()).isTrue();
    }

}
