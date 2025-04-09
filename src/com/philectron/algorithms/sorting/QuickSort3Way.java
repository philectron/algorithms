package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertElementIndexes;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

public class QuickSort3Way implements QuickSort {

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

        // Partition the array and retrieve the indices before and after the pivot sequence.
        int[] midIndices = partition(array, low, high);

        // Sort left half and right half of the array at the partition point.
        quicksort(array, low, midIndices[0]);
        quicksort(array, midIndices[1], high);
    }

    /**
     * Performs a 3-way partition of {@code array} based on a random pivot within the
     * {@code [low..high]} interval.
     *
     * @param array the array to be partitioned
     * @param low the starting element index of the array, inclusive
     * @param high the ending element index of the array, inclusive
     *
     * @return a partition index pair {@code [preMid, postMid]} in {@code array}, where
     *         {@code array[low..preMid]} contains all elements smaller than pivot, and
     *         {@code array[preMid + 1..postMid - 1]} contains all occurrences of pivot, and
     *         {@code array[postMid..high]} contains all elements greater than pivot
     */
    private int[] partition(int[] array, int low, int high) {
        assertNotNull(array);
        assertElementIndexes(low, high, array.length);

        // Choose a random element as the pivot, then swap it with the last element.
        final int randomIndex = getRandomPivotIndex(low, high);
        SortUtils.swap(array, randomIndex, high);

        final int pivot = array[high];

        // Pointers to determine elements <= pivot and >= pivot, respectively
        int left = low - 1;
        int right = high;

        // Pointers to mark duplicates of pivot at the start and the end, respectively
        int midLeft = low - 1;
        int midRight = high;

        while (true) {
            // Move the left pointer to the right until the first element >= pivot.
            // Guarantee to terminate since array[high] == pivot.
            do {
                ++left;
            } while (array[left] < pivot);

            // Move the right pointer to the left until the first element <= pivot (or until oob).
            do {
                --right;
            } while (array[right] > pivot && right > low);

            // If 2 pointers crossed, we're done.
            if (left >= right) {
                break;
            }

            // Otherwise, smaller should go on the left, and larger should go on the right.
            SortUtils.swap(array, left, right);

            // If the left element is a duplicate of the pivot, move it to the start.
            if (array[left] == pivot) {
                SortUtils.swap(array, ++midLeft, left);
            }

            // If the right element is a duplicate of the pivot, move it to the end.
            if (array[right] == pivot) {
                SortUtils.swap(array, --midRight, right);
            }
        }

        // At this point, all elements to the left of the left pointers is <= pivot,
        // and all elements to its right is >= pivot.
        // Move the pivot to its final position.
        SortUtils.swap(array, left, high);

        // Starting at the pivot index, count backward to find the mark of the pre-mid.
        // We also need to swap back all the pivot duplicates at the start into the middle.
        int preMid = left - 1;
        for (int i = low; i <= midLeft; ++i) {
            SortUtils.swap(array, i, preMid--);
        }

        // Starting at the pivot index, count backward to find the mark of the post-mid.
        // We also need to swap back all the pivot duplicates at the end into the middle.
        int postMid = left + 1;
        for (int i = high - 1; i >= midRight; --i) {
            SortUtils.swap(array, i, postMid++);
        }

        // Return 2 middle points marking the 3-way partition.
        return new int[] { preMid, postMid };
    }

}
