package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertElementIndexes;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

public class MergeSort implements SortingAlgorithm {

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

        final int mid = low + (high - low) / 2;

        mergeSort(array, low, mid);
        mergeSort(array, mid + 1, high);

        merge(array, low, mid, high);
    }

    /**
     * Merges two sorted sub-arrays {@code array[low..mid]} and {@code array[mid + 1..high]}.
     * Modifies the original array such that {@code array[low..high]} is sorted after the merge.
     *
     * @param array the array containing the two sub-arrays to be merged
     * @param low the starting element index of the left sub-array, inclusive
     * @param mid the ending element index of the left sub-array, inclusive
     * @param high the ending element index of the right sub-array, inclusive
     */
    private void merge(int[] array, int low, int mid, int high) {
        assertNotNull(array);
        assertElementIndexes(low, mid, array.length);
        assertElementIndexes(mid + 1, high, array.length);

        final int nLeft = mid - low + 1;
        int[] leftArray = new int[nLeft];
        final int nRight = high - mid;
        int[] rightArray = new int[nRight];

        // Make a temporary copy array for each sorted half.
        for (int i = 0; i < nLeft; i++) {
            leftArray[i] = array[low + i];
        }
        for (int i = 0; i < nRight; i++) {
            rightArray[i] = array[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int nMerged = low;

        // Copy the smallest of each sorted half to the original array until done with one half.
        while (i < nLeft && j < nRight) {
            if (leftArray[i] <= rightArray[j]) {
                array[nMerged] = leftArray[i++];
            } else {
                array[nMerged] = rightArray[j++];
            }
            nMerged++;
        }

        // Copy the remaining left half, if any, to the original array.
        while (i < nLeft) {
            array[nMerged++] = leftArray[i++];
        }

        // Copy the remaining right half, if any, to the original array.
        while (j < nRight) {
            array[nMerged++] = rightArray[j++];
        }
    }

}
