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
    public void reverse() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>(VALUES);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        Collections.reverse(expectedList);

        doublyLinkedList.reverse();

        assertThat(doublyLinkedList.toJavaList()).isEqualTo(expectedList);
    }

}
