package com.philectron.algorithms.sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public abstract class SortTestBase {

    private final SortingAlgorithm sorter;

    SortTestBase(final SortingAlgorithm sorter) {
        this.sorter = sorter;
    }

    @Test
    public void sort_nullArray_fails() {
        assertThrows(RuntimeException.class, () -> sorter.sort(null));
    }

    @Test
    public void sort_emptyArray() {
        final int[] array = new int[0];
        assertSorted(array);
        sorter.sort(array);
        assertSorted(array);
    }

    @Test
    public void sort_singletonArray() {
        final int[] array = new int[1];
        assertSorted(array);
        sorter.sort(array);
        assertSorted(array);
    }

    @Test
    public void sort_nCopiesArray() {
        final int[] array = { 1, 1, 1, 1, 1 };
        assertSorted(array);
        sorter.sort(array);
        assertSorted(array);
    }

    @Test
    public void sort_ascendingArray() {
        final int[] array = { 1, 2, 3, 4, 5 };
        assertSorted(array);
        sorter.sort(array);
        assertSorted(array);
    }

    @Test
    public void sort_descendingArray() {
        final int[] array = { 5, 4, 3, 2, 1 };
        assertUnsorted(array);
        sorter.sort(array);
        assertSorted(array);
    }

    @Test
    public void sort_arbitraryArray() {
        final int[] array = {
            21, 37, 36, 19, 30, 27, 25, 36, 32, 28, 13, 33, 34, 20, 30, 4, 15, 40, 5, 10
        };

        assertUnsorted(array);
        sorter.sort(array);
        assertSorted(array);
    }

    private void assertSorted(final int[] array) {
        assertThat("Array is sorted: " + Arrays.toString(array), SortUtils.isSorted(array),
                is(true));
    }

    private void assertUnsorted(final int[] array) {
        assertThat("Array is not sorted: " + Arrays.toString(array), SortUtils.isSorted(array),
                is(false));
    }

}
