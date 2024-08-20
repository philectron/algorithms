package com.philectron.algorithms.datastructures.list;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {

    private static final java.util.List<Integer> VALUES = java.util.List.of(1, 2, 3, 4, 5);

    @Test
    public void getNode_emptyList_fails() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThat(list.isEmpty()).isTrue();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void getNode_indexOutOfBound_fails() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThat(list.isEmpty()).isFalse();
        assertThat(list.size()).isEqualTo(VALUES.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(VALUES.size()));
    }

    @Test
    public void getNode_indexInBound() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThat(list.get(VALUES.size() - 1)).isEqualTo(VALUES.get(VALUES.size() - 1));
    }

    @Test
    public void setNode_emptyList_fails() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThat(list.isEmpty()).isTrue();
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 1));
    }

    @Test
    public void setNode_indexOutOfBound_fails() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThat(list.isEmpty()).isFalse();
        assertThat(list.size()).isEqualTo(VALUES.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(VALUES.size(), 1));
    }

    @Test
    public void setNode_indexInBound() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        final int setIndex = VALUES.size() - 1;
        final int expectedOldValue = list.get(setIndex);
        final int expectedNewValue = expectedOldValue + 1;

        assertThat(list.set(setIndex, expectedNewValue)).isEqualTo(expectedOldValue);
        assertThat(list.get(setIndex)).isEqualTo(expectedNewValue);
    }

    @Test
    public void add_emptyList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        final int value = VALUES.get(0);

        list.add(0, value);

        assertThat(list.toJavaList()).isEqualTo(Arrays.asList(value));
    }

    @Test
    public void add_positionOutOfBound_fails() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 1));
    }

    @Test
    public void add_startOfList_addFront() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        final int value = VALUES.get(0);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addFirst(value);

        list.addFront(value);

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void add_middleOfList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        final int position = VALUES.size() / 2;
        final int value = VALUES.get(position);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.add(position, value);

        list.add(position, value);

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void add_endOfList_addBack() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        final int value = VALUES.get(VALUES.size() - 1);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.addLast(value);

        list.addBack(value);

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void indexOf_emptyList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThat(list.indexOf(VALUES.get(0))).isEqualTo(-1);
    }

    @Test
    public void indexOf_existingList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
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
    public void lastIndexOf_emptyList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThat(list.lastIndexOf(VALUES.get(0))).isEqualTo(-1);
    }

    @Test
    public void lastIndexOf_existingList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
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
    public void contains_emptyList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThat(list.contains(1)).isFalse();
    }

    @Test
    public void contains_existingList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThat(list.contains(VALUES.get(0))).isTrue();
    }

    @Test
    public void remove_emptyList_fails() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    public void remove_indexOutOfBound_fails() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(VALUES.size()));
    }

    @Test
    public void remove_startOfList_removeFront() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeFirst();

        list.removeFront();

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void remove_middleOfList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        final int removalIndex = VALUES.size() / 2;
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.remove(removalIndex);

        list.remove(removalIndex);

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void remove_endOfList_removeBack() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        expectedList.removeLast();

        list.removeBack();

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

    @Test
    public void clear_removesEverything() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);

        list.clear();

        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void reverse() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        Collections.reverse(expectedList);

        list.reverse();

        assertThat(list.toJavaList()).isEqualTo(expectedList);
    }

}
