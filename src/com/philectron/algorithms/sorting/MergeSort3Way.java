package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertElementIndexes;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

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
     * @param low the starting element index of the array, inclusive
     * @param high the ending element index of the array, inclusive
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
     * @param array the array containing the two sub-arrays to be merged
     * @param low the starting element index of the left sub-array, inclusive
     * @param midLeft the ending element index of the left sub-array, inclusive
     * @param midRight the ending element index of the middle sub-array, inclusive
     * @param high the ending element index of the right sub-array, inclusive
     */
    private void merge(int[] array, int low, int midLeft, int midRight, int high) {
        assertNotNull(array);
        assertElementIndexes(low, midLeft, array.length);
        assertElementIndexes(midLeft, midRight, array.length);
        assertElementIndexes(midRight, high, array.length);

        // Make a temporary copy array for each sorted third.
        final int numLeft = midLeft - low + 1;
        int[] leftArray = new int[numLeft];

        final int numMiddle = midRight - midLeft;
        int[] middleArray = new int[numMiddle];

        final int numRight = high - midRight;
        int[] rightArray = new int[numRight];

        for (int i = 0; i < numLeft; ++i) {
            leftArray[i] = array[low + i];
        }

        for (int i = 0; i < numMiddle; ++i) {
            middleArray[i] = array[midLeft + 1 + i];
        }

        for (int i = 0; i < numRight; ++i) {
            rightArray[i] = array[midRight + 1 + i];
        }

        int left = 0;
        int middle = 0;
        int right = 0;
        int mergedIndex = low;

        // Copy the smallest of each sorted half to the original array until done with one third.
        while (left < numLeft || middle < numMiddle || right < numRight) {
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            if (left < numLeft && leftArray[left] < minValue) {
                minValue = leftArray[left];
                minIndex = 0;
            }

            if (middle < numMiddle && middleArray[middle] < minValue) {
                minValue = middleArray[middle];
                minIndex = 1;
            }

            if (right < numRight && rightArray[right] < minValue) {
                minValue = rightArray[right];
                minIndex = 2;
            }

            if (minIndex == 0) {
                array[mergedIndex++] = leftArray[left++];
            } else if (minIndex == 1) {
                array[mergedIndex++] = middleArray[middle++];
            } else {
                array[mergedIndex++] = rightArray[right++];
            }
        }
    }

}
