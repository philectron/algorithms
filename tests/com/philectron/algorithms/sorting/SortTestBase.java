package com.philectron.algorithms.sorting;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public abstract class SortTestBase {

    private final SortingAlgorithm sorter;

    private int[] array;

    SortTestBase(SortingAlgorithm sorter) {
        this.sorter = sorter;
    }

    @AfterEach
    public void sortAndAssert() {
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_emptyArray() {
        array = new int[0];
    }

    @Test
    public void sort_singletonArray() {
        array = new int[1];
    }

    @Test
    public void sort_nCopiesArray() {
        array = new int[] { 1, 1, 1, 1, 1 };
    }

    @Test
    public void sort_ascendingArray() {
        array = new int[] { 1, 2, 3, 4, 5 };
    }

    @Test
    public void sort_descendingArray() {
        array = new int[] { 5, 4, 3, 2, 1 };
    }

    @Test
    public void sort_arbitraryArray() {
        array = new int[] { 21, 37, 36, 19, 30, 27, 25, 36, 32, 28, 13, 33, 34, 20, 30, 4, 15, 40 };
    }

}
