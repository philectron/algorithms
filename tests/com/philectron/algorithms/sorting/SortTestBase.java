package com.philectron.algorithms.sorting;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public abstract class SortTestBase {

    private final SortingAlgorithm sorter;

    private int[] array;

    SortTestBase(SortingAlgorithm sorter) {
        this.sorter = sorter;
    }

    private void sortAndAssert() {
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_nullArray_fails() {
        assertThrows(NullPointerException.class, () -> sorter.sort(null));
    }

    @Test
    public void sort_emptyArray() {
        array = new int[0];
        sortAndAssert();
    }

    @Test
    public void sort_singletonArray() {
        array = new int[1];
        sortAndAssert();
    }

    @Test
    public void sort_nCopiesArray() {
        array = new int[] { 1, 1, 1, 1, 1 };
        sortAndAssert();
    }

    @Test
    public void sort_ascendingArray() {
        array = new int[] { 1, 2, 3, 4, 5 };
        sortAndAssert();
    }

    @Test
    public void sort_descendingArray() {
        array = new int[] { 5, 4, 3, 2, 1 };
        sortAndAssert();
    }

    @Test
    public void sort_arbitraryArray() {
        array = new int[] { 21, 37, 36, 19, 30, 27, 25, 36, 32, 28, 13, 33, 34, 20, 30, 4, 15, 40 };
        sortAndAssert();
    }

}
