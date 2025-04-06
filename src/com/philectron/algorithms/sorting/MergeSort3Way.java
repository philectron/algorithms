package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertElementIndexes;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import java.util.Arrays;

public class MergeSort3Way implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        checkNotNull(array);
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * Performs recursive merge sort on {@code array[low..high]}.
     *
     * @param array the array to be sorted
     * @param low   the starting element index of the array, inclusive
     * @param high  the ending element index of the array, inclusive
     */
    private void mergeSort(int[] array, int low, int high) {
        assertNotNull(array);

        // Singleton and empty arrays are considered sorted.
        if (low >= high) {
            return;
        }

        assertElementIndexes(low, high, array.length);

        final int midLeft = low + (high - low) / 3;
        final int midRight = low + 2 * (high - low) / 3;

        mergeSort(array, low, midLeft);
        mergeSort(array, midLeft + 1, midRight);
        mergeSort(array, midRight + 1, high);

        merge(array, low, midLeft, midRight, high);
    }

    /**
     * Merges three sorted sub-arrays {@code array[low..midLeft]},
     * {@code array[midLeft + 1..midRight]}, and {@code array[midRight + 1..high]}. Modifies the
     * original array such that {@code array[low..high]} is sorted after the merge.
     *
     * @param array    the array containing the two sub-arrays to be merged
     * @param low      the starting element index of the left sub-array, inclusive
     * @param midLeft  the ending element index of the left sub-array, inclusive
     * @param midRight the ending element index of the middle sub-array, inclusive
     * @param high     the ending element index of the right sub-array, inclusive
     */
    private void merge(int[] array, int low, int midLeft, int midRight, int high) {
        assertNotNull(array);
        assertElementIndexes(low, midLeft, array.length);
        assertElementIndexes(midLeft, midRight, array.length);
        assertElementIndexes(midRight, high, array.length);

        // Make a temporary copy array for each sorted third.
        int[] leftArray = Arrays.copyOfRange(array, low, midLeft + 1);
        final int numLeft = leftArray.length;

        int[] middleArray = Arrays.copyOfRange(array, midLeft + 1, midRight + 1);
        final int numMiddle = middleArray.length;

        int[] rightArray = Arrays.copyOfRange(array, midRight + 1, high + 1);
        final int numRight = rightArray.length;

        // Use 3 pointers to merge.
        int left = 0;
        int middle = 0;
        int right = 0;

        // Copy the smallest of each sorted third to the original array.
        for (int i = low; i <= high; ++i) {
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            if (left < numLeft) {
                minValue = leftArray[left];
                minIndex = 0;
            }

            if (middle < numMiddle && middleArray[middle] < minValue) {
                minValue = middleArray[middle];
                minIndex = 1;
            }

            if (right < numRight && rightArray[right] < minValue) {
                minIndex = 2;
            }

            if (minIndex == 0) {
                array[i] = leftArray[left++];
            } else if (minIndex == 1) {
                array[i] = middleArray[middle++];
            } else {
                array[i] = rightArray[right++];
            }
        }
    }

}
