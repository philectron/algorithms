package com.philectron.algorithms.datastructures.sets;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.OrderedSet;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkipListTest extends OrderedSetTestBase {

    private SkipList<Integer> skipList;
    private SkipList<Integer> emptySkipList;

    @Override
    OrderedSet<Integer> createOrderedSet(Iterable<Integer> iterable) {
        return new SkipList<>(iterable);
    }

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        skipList = new SkipList<>(VALUES);
        emptySkipList = new SkipList<>(Collections.emptyList());
    }

    @Test
    void get_byIndex_emptySkipList_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptySkipList.get(0));
    }

    @Test
    void get_byIndex_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> skipList.get(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> skipList.get(VALUES_UNIQUE_SORTED.size()));
    }

    @Test
    void get_byIndex_returnsElementAtIndex() {
        final int index = VALUES_UNIQUE_SORTED.size() / 2;
        assertThat(skipList.get(index)).isEqualTo(VALUES_UNIQUE_SORTED.get(index));
    }

    @Test
    void indexOf_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptySkipList.indexOf(null));
        assertThrows(NullPointerException.class, () -> skipList.indexOf(null));
    }

    @Test
    void indexOf_emptySkipList_returnsNotFound() {
        assertThat(emptySkipList.indexOf(1)).isEqualTo(-1);
        // Searching should not mutate the list.
        assertThat(emptySkipList).isEmpty();
    }

    @Test
    void indexOf_returnsSingleOccurrence() {
        final int midIndex = VALUES_UNIQUE_SORTED.size() / 2;
        assertThat(skipList.indexOf(VALUES_UNIQUE_SORTED.get(midIndex))).isEqualTo(midIndex);
        // Searching should not mutate the skip list.
        assertThat(skipList).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void lastIndexOf_nullElement_fails() {
        assertThrows(NullPointerException.class, () -> emptySkipList.lastIndexOf(null));
        assertThrows(NullPointerException.class, () -> skipList.lastIndexOf(null));
    }

    @Test
    void lastIndexOf_emptySkipList_returnsNotFound() {
        assertThat(emptySkipList.lastIndexOf(1)).isEqualTo(-1);
        // Searching should not mutate the list.
        assertThat(emptySkipList).isEmpty();
    }

    @Test
    void lastIndexOf_returnsSingleOccurrence() {
        final int midIndex = VALUES_UNIQUE_SORTED.size() / 2;
        assertThat(skipList.lastIndexOf(VALUES_UNIQUE_SORTED.get(midIndex))).isEqualTo(midIndex);
        // Searching should not mutate the skip list.
        assertThat(skipList).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

    @Test
    void remove_byIndex_emptySkipList_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptySkipList.remove(0));
    }

    @Test
    void remove_byIndex_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> skipList.remove(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> skipList.remove(VALUES_UNIQUE_SORTED.size()));
    }

    @Test
    void remove_byIndex_returnsDeletedElement() {
        final int index = VALUES_UNIQUE_SORTED.size() / 2;
        final int value = VALUES_UNIQUE_SORTED.get(index);
        SortedSet<Integer> expectedSet = new TreeSet<>(VALUES_UNIQUE_SORTED);

        expectedSet.remove(value);
        assertThat(skipList.remove(index)).isEqualTo(value);
        assertThat(skipList).containsExactlyElementsIn(expectedSet).inOrder();
    }

}
