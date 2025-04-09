package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.philectron.algorithms.logic.Assertion.assertElementIndexes;
import static com.philectron.algorithms.logic.Assertion.assertNotNull;

import java.util.Arrays;

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

        int[] leftArray = Arrays.copyOfRange(array, low, mid + 1);
        int[] rightArray = Arrays.copyOfRange(array, mid + 1, high + 1);

        int left = 0; // running pointer for left array
        int right = 0; // running pointer for right array
        int nMerged = low; // running pointer to overwrite the original array

        // Copy the smallest of each sorted half to the original array until done with one half.
        while (left < leftArray.length && right < rightArray.length) {
            if (leftArray[left] <= rightArray[right]) {
                array[nMerged] = leftArray[left++];
            } else {
                array[nMerged] = rightArray[right++];
            }
            ++nMerged;
        }

        // Copy the remaining left half, if any, to the original array.
        while (left < leftArray.length) {
            array[nMerged++] = leftArray[left++];
        }

        // Copy the remaining right half, if any, to the original array.
        while (right < rightArray.length) {
            array[nMerged++] = rightArray[right++];
        }
    }

}
