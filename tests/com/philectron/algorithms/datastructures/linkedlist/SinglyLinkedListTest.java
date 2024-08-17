package com.philectron.algorithms.datastructures.linkedlist;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {

    private static final Integer[] VALUES = { 1, 2, 3, 4, 5 };

    @Test
    public void getNode_emptyList_fails() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThat(list.isEmpty()).isTrue();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void getNode_indexOutOfBound_fails() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThat(list.isEmpty()).isFalse();
        assertThat(list.size()).isEqualTo(VALUES.length);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(VALUES.length));
    }

    @Test
    public void getNode_indexInBound() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThat(list.get(VALUES.length - 1)).isEqualTo(VALUES[VALUES.length - 1]);
    }

    @Test
    public void contains_emptyList() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThat(list.contains(1)).isFalse();
    }

    @Test
    public void contains_existingList() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThat(list.contains(VALUES[VALUES.length - 1])).isTrue();
    }

    @Test
    public void add_emptyList() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        final int value = 1;

        list.add(0, value);

        assertThat(list.toList()).isEqualTo(Arrays.asList(value));
    }

    @Test
    public void add_positionOutOfBound_fails() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 1));
    }

    @Test
    public void add_startOfList_addFront() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        final int value = 6;

        list.addFront(value);

        assertThat(list.toList()).isEqualTo(Arrays.asList(value, 1, 2, 3, 4, 5));
    }

    @Test
    public void add_middleOfList() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        final int value = 6;
        final int position = VALUES.length / 2;

        list.add(position, value);

        assertThat(list.toList()).isEqualTo(Arrays.asList(1, 2, value, 3, 4, 5));
    }

    @Test
    public void add_endOfList_addBack() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        final int value = 6;

        list.addBack(value);

        assertThat(list.toList()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, value));
    }

    @Test
    public void remove_emptyList_fails() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    public void remove_indexOutOfBound_fails() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(VALUES.length));
    }

    @Test
    public void remove_startOfList_removeFront() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);

        list.removeFront();

        assertThat(list.toList()).isEqualTo(Arrays.asList(2, 3, 4, 5));
    }

    @Test
    public void remove_middleOfList() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);

        list.remove(VALUES.length / 2);

        assertThat(list.toList()).isEqualTo(Arrays.asList(1, 2, 4, 5));
    }

    @Test
    public void remove_endOfList_removeBack() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);

        list.removeBack();

        assertThat(list.toList()).isEqualTo(Arrays.asList(1, 2, 3, 4));
    }

    @Test
    public void remove_allOfList() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);

        list.removeAll();

        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void reverse() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<>(VALUES);

        list.reverse();

        assertThat(list.toList()).isEqualTo(Arrays.asList(5, 4, 3, 2, 1));
    }

}
