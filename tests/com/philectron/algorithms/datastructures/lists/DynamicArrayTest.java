package com.philectron.algorithms.datastructures.lists;

import static com.google.common.truth.Truth.assertThat;

import com.philectron.algorithms.datastructures.interfaces.List;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class DynamicArrayTest extends ListTestBase {

    @Override
    List<Integer> createList(Iterable<Integer> iterable) {
        return new DynamicArray<>(iterable);
    }

    @Test
    public void add_exceedsCapacity_growsArray_addsElements_returnsTrue() {
        java.util.List<Integer> fullCapacityList =
                Collections.nCopies(DynamicArray.DEFAULT_CAPACITY, 0);

        java.util.List<Integer> expectedList = new ArrayList<>(fullCapacityList);
        expectedList.add(-1);
        expectedList.addFirst(-2);
        expectedList.addLast(-3);
        expectedList.add(expectedList.size() / 2, -4);
        expectedList.addAll(VALUES);

        List<Integer> list = new DynamicArray<>(fullCapacityList);
        assertThat(list.add(-1)).isTrue();
        assertThat(list.addFirst(-2)).isTrue();
        assertThat(list.addLast(-3)).isTrue();
        assertThat(list.add(list.size() / 2, -4)).isTrue();
        assertThat(list.addAll(VALUES)).isTrue();

        assertThat(list).containsExactlyElementsIn(expectedList).inOrder();
    }

}
