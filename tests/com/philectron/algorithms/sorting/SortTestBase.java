package com.philectron.algorithms.sorting;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public abstract class SortTestBase {

    private final SortingAlgorithm sorter;

    SortTestBase(final SortingAlgorithm sorter) {
        this.sorter = sorter;
    }

    @Test
    public void sort_emptyArray() {
        final int[] array = new int[0];
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_singletonArray() {
        final int[] array = new int[1];
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_nCopiesArray() {
        final int[] array = { 1, 1, 1, 1, 1 };
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_ascendingArray() {
        final int[] array = { 1, 2, 3, 4, 5 };
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_descendingArray() {
        final int[] array = { 5, 4, 3, 2, 1 };
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_arbitraryArray() {
        final int[] array =
                { 21, 37, 36, 19, 30, 27, 25, 36, 32, 28, 13, 33, 34, 20, 30, 4, 15, 40, 5, 10 };
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

}
