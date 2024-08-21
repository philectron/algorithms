package com.philectron.algorithms.datastructures.list;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class DoublyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new DoublyLinkedList<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new DoublyLinkedList<>();
    }

    @Test
    public void reverse_flipsListOrder() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>(VALUES);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);

        // Reverse the list and make sure the order is reversed.
        Collections.reverse(expectedList);
        doublyLinkedList.reverse();
        assertThat(doublyLinkedList.toJavaList()).isEqualTo(expectedList);

        // Reverse the list again and make sure the order is back to original.
        Collections.reverse(expectedList);
        doublyLinkedList.reverse();
        assertThat(doublyLinkedList.toJavaList()).isEqualTo(expectedList);
    }

}
