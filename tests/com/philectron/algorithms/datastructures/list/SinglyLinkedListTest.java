package com.philectron.algorithms.datastructures.list;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new SinglyLinkedList<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new SinglyLinkedList<>();
    }

    @Test
    public void reverse() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>(VALUES);
        java.util.List<Integer> expectedList = new ArrayList<>(VALUES);
        Collections.reverse(expectedList);

        singlyLinkedList.reverse();

        assertThat(singlyLinkedList.toJavaList()).isEqualTo(expectedList);
    }

}
