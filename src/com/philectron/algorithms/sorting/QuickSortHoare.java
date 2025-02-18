package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertElementIndexes;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

public class QuickSortHoare implements QuickSort {

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

        int pivotIndex = partition(array, low, high);

        // Sort left half and right half of the array, excluding the pivot itself,
        // because its position after the partitioning is final.
        quicksort(array, low, pivotIndex);
        quicksort(array, pivotIndex + 1, high);
    }

    /**
     * The Hoare partition algorithm partitions {@code array} based on a random pivot within the
     * {@code [low..high]} interval.
     *
     * @param array the array to be partitioned
     * @param low the starting element index of the array, inclusive
     * @param high the ending element index of the array, inclusive
     *
     * @return a partition index in {@code array}, where {@code array[low..index]} are less than or
     *         equal to the pivot, and {@code array[index + 1..high]} are greater than or equal to
     *         the pivot
     */
    private int partition(int[] array, int low, int high) {
        assertNotNull(array);
        assertElementIndexes(low, high, array.length);

        // Choose a random element as the pivot, then swap it with the first element.
        final int randomIndex = getRandomPivotIndex(low, high);
        SortUtils.swap(array, randomIndex, low);

        final int pivot = array[low];

        int left = low - 1;
        int right = high + 1;
        while (true) {
            // Move left bound to the right until it meets an element >= pivot.
            while (array[++left] < pivot);

            // Move right bound to the left until it meets an element <= pivot.
            while (array[--right] > pivot);

            // When 2 pointers crossed, the right pointer is the partition point:
            // array[low..right] <= pivot, and array[right + 1..high] >= pivot. Fast return.
            if (left >= right) {
                // Note that Hoare partitioning does not necessary set the pivot at the final point.
                return right;
            }

            // Swap left and right since they are out of order.
            SortUtils.swap(array, left, right);
        }
    }

}
