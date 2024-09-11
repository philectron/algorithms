package com.philectron.algorithms.datastructures.lists;

import com.philectron.algorithms.datastructures.interfaces.List;

public class SinglyLinkedListTest extends ListTestBase {

    @Override
    List<Integer> createList(Iterable<Integer> iterable) {
        return new SinglyLinkedList<>(iterable);
    }

}
