package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertElementIndexes;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import java.util.Random;

public class QuickSort implements SortingAlgorithm {

    private final Random random;

    public QuickSort() {
        random = new Random();
    }

    @Override
    public void sort(int[] array) {
        checkNotNull(array);
        quicksort(array, 0, array.length - 1);
    }

    /**
     * Performs recursive quick sort on {@code array[low..high]}.
     *
     * @param array the array to be sorted
     * @param low the starting element index of the array, inclusive
     * @param high the ending element index of the array, inclusive
     */
    private void quicksort(int[] array, int low, int high) {
        assertNotNull(array);

        // Singleton and empty arrays are considered sorted.
        if (low >= high) {
            return;
        }

        assertElementIndexes(low, high, array.length);

        final int pivotIndex = partition(array, low, high);

        // Sort left half and right half of the array, excluding the pivot itself,
        // because its position after the partitioning is final.
        quicksort(array, low, pivotIndex - 1);
        quicksort(array, pivotIndex + 1, high);
    }

    /**
     * Partitions {@code array} based on a random pivot within the {@code [low..high]} interval.
     *
     * @param array the array to be partitioned
     * @param low the starting element index of the array, inclusive
     * @param high the ending element index of the array, inclusive
     *
     * @return the final and correct index of the pivot in {@code array}, where
     *         {@code array[low..index - 1]} are less than or equal to the pivot, and
     *         {@code array[index + 1..high]} are greater than or equal to the pivot
     */
    private int partition(int[] array, int low, int high) {
        assertNotNull(array);
        assertNotNull(random);
        assertElementIndexes(low, high, array.length);

        // Choose a random element as the pivot, then swap it with the last element.
        final int pivotIndex = low + random.nextInt(high - low + 1);
        final int pivot = array[pivotIndex];
        SortUtils.swap(array, pivotIndex, high);

        int correctPivotIndex = low;
        for (int i = low; i < high; i++) {
            if (array[i] < pivot) {
                SortUtils.swap(array, i, correctPivotIndex++);
            }
        }

        SortUtils.swap(array, correctPivotIndex, high);

        return correctPivotIndex;
    }

}
