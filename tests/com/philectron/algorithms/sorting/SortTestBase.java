package com.philectron.algorithms.sorting;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public abstract class SortTestBase {

    static int[] buildEmptyArray() {
        return new int[0];
    }

    static int[] buildSingletonArray() {
        return new int[1];
    }

    static int[] buildNCopiesArray() {
        return new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };
    }

    static int[] buildAscendingArray() {
        return new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
    }

    static int[] buildDescendingArray() {
        return new int[] { 8, 7, 6, 5, 4, 3, 2, 1 };
    }

    static int[] buildLargeMiddleArray() {
        return new int[] { 1, 2, 3, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1, -5, -4, -3, -2, -1 };
    }

    static int[] buildArbitraryArray() {
        return new int[] { 21, 37, 36, 19, 30, 27, 25, 36, 32, 28, 13, 33, 34, 20, 30, 4, 15, 40 };
    }

    private final SortingAlgorithm sorter;

    SortTestBase(SortingAlgorithm sorter) {
        this.sorter = sorter;
    }

    @Test
    public void sort_nullArray_fails() {
        assertThrows(NullPointerException.class, () -> sorter.sort(null));
    }

    @Test
    public void sort_emptyArray() {
        int[] array = buildEmptyArray();
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_singletonArray() {
        int[] array = buildSingletonArray();
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_nCopiesArray() {
        int[] array = buildNCopiesArray();
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_ascendingArray() {
        int[] array = buildAscendingArray();
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_descendingArray() {
        int[] array = buildDescendingArray();
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_largeMiddleArray() {
        int[] array = buildLargeMiddleArray();
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_arbitraryArray() {
        int[] array = buildArbitraryArray();
        sorter.sort(array);
        assertThat(array).asList().isInOrder();
    }

}
