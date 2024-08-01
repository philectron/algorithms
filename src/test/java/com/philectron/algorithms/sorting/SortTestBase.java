package com.philectron.algorithms.sorting;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public abstract class SortTestBase {

    private static final int ARRAY_SIZE = 20;

    final SortingAlgorithm sorter;

    SortTestBase(final SortingAlgorithm sorter) {
        this.sorter = sorter;
    }

    @Test
    public void sortNullArrayShallThrow() {
        assertThrows(RuntimeException.class, () -> sorter.sort(null));
    }

    @Test
    public void sortEmptyArrayShallSucceed() {
        sortAndAssert(new int[0]);
    }

    @Test
    public void sortSingletonArrayShallSucceed() {
        sortAndAssert(new int[1]);
    }

    @Test
    public void sortRandomArrayShallSortTheArray() {
        final int[] array = new int[ARRAY_SIZE];
        final Random rand = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = rand.nextInt(ARRAY_SIZE * 2);
        }
        sortAndAssert(array);
    }

    private void sortAndAssert(final int[] array) {
        sorter.sort(array);
        assertTrue(SortUtils.isSorted(array),
                "Expected array to be sorted: " + Arrays.toString(array));
    }

}
