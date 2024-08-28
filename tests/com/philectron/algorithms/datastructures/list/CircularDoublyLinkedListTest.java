package com.philectron.algorithms.datastructures.list;

import static com.google.common.truth.Truth.assertThat;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class CircularDoublyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createListWithValues() {
        return new CircularDoublyLinkedList<>(VALUES);
    }

    @Override
    List<Integer> createEmptyList() {
        return new CircularDoublyLinkedList<>();
    }

    @Test
    public void iteratorInList_reverseIteratorInReversedList_makesSameList() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>(VALUES);
        java.util.List<Integer> listJava = new java.util.ArrayList<>();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            listJava.add(it.next());
        }

        list.reverse();
        java.util.List<Integer> reversedListJava = new java.util.ArrayList<>();
        Iterator<Integer> rit = list.reverseIterator();
        while (rit.hasNext()) {
            reversedListJava.add(rit.next());
        }

        assertThat(reversedListJava).isEqualTo(listJava);
    }

}
