package com.philectron.algorithms.datastructures.linkedlist;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {

    private static final int[] VALUES = { 1, 2, 3, 4, 5 };

    @Test
    public void fromArray_nullArray_fails() {
        assertThrows(NullPointerException.class, () -> SinglyLinkedList.fromArray(null));
    }

    @Test
    public void getNode_emptyList_fails() {
        final SinglyLinkedList list = new SinglyLinkedList();
        assertThat(list.isEmpty()).isTrue();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void getNode_indexOutOfBound_fails() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);
        assertThat(list.isEmpty()).isFalse();
        assertThat(list.size()).isEqualTo(VALUES.length);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(VALUES.length));
    }

    @Test
    public void getNode_indexInBound() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);
        assertThat(list.get(VALUES.length - 1)).isEqualTo(VALUES[VALUES.length - 1]);
    }

    @Test
    public void contains_emptyList() {
        final SinglyLinkedList list = new SinglyLinkedList();
        assertThat(list.contains(1)).isFalse();
    }

    @Test
    public void contains_existingList() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);
        assertThat(list.contains(VALUES[VALUES.length - 1])).isTrue();
    }

    @Test
    public void add_emptyList() {
        final SinglyLinkedList list = new SinglyLinkedList();
        final int value = 1;

        list.add(0, value);

        assertThat(list.toArray()).isEqualTo(new int[] { value });
    }

    @Test
    public void add_positionOutOfBound_fails() {
        final SinglyLinkedList list = new SinglyLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 1));
    }

    @Test
    public void add_startOfList_addFront() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);
        final int value = 6;

        list.addFront(value);

        assertThat(list.toArray()).isEqualTo(new int[] { value, 1, 2, 3, 4, 5 });
    }

    @Test
    public void add_middleOfList() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);
        final int value = 6;
        final int position = VALUES.length / 2;

        list.add(position, value);

        assertThat(list.toArray()).isEqualTo(new int[] { 1, 2, value, 3, 4, 5 });
    }

    @Test
    public void add_endOfList_addBack() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);
        final int value = 6;

        list.addBack(value);

        assertThat(list.toArray()).isEqualTo(new int[] { 1, 2, 3, 4, 5, value });
    }

    @Test
    public void remove_emptyList_fails() {
        final SinglyLinkedList list = new SinglyLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    public void remove_indexOutOfBound_fails() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(VALUES.length));
    }

    @Test
    public void remove_startOfList_removeFront() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);

        list.removeFront();

        assertThat(list.toArray()).isEqualTo(new int[] { 2, 3, 4, 5 });
    }

    @Test
    public void remove_middleOfList() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);

        list.remove(VALUES.length / 2);

        assertThat(list.toArray()).isEqualTo(new int[] { 1, 2, 4, 5 });
    }

    @Test
    public void remove_endOfList_removeBack() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);

        list.removeBack();

        assertThat(list.toArray()).isEqualTo(new int[] { 1, 2, 3, 4 });
    }

    @Test
    public void reverse() {
        final SinglyLinkedList list = SinglyLinkedList.fromArray(VALUES);

        list.reverse();

        assertThat(list.toArray()).isEqualTo(new int[] { 5, 4, 3, 2, 1 });
    }

}
