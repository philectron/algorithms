package com.philectron.algorithms.sorting;

import com.google.common.base.Preconditions;

public class MergeSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        Preconditions.checkNotNull(array);
        mergeSort(array, 0, array.length);
    }

    private void mergeSort(int[] array, int low, int high) {
        if (low >= high - 1) {
            return;
        }

        final int mid = low + (high - low) / 2;

        mergeSort(array, low, mid);
        mergeSort(array, mid, high);

        merge(array, low, mid, high);
    }

    private void merge(int[] array, int low, int mid, int high) {
        final int nLeft = mid - low;
        int[] leftArray = new int[nLeft];
        final int nRight = high - mid;
        int[] rightArray = new int[nRight];

        // Make a temporary copy array for each sorted half.
        for (int i = 0; i < nLeft; i++) {
            leftArray[i] = array[low + i];
        }
        for (int i = 0; i < nRight; i++) {
            rightArray[i] = array[mid + i];
        }

        int i = 0;
        int j = 0;
        int nMerged = low;

        // Copy the smallest of each sorted half to the original array until done with one half.
        while (i < nLeft && j < nRight) {
            if (leftArray[i] < rightArray[j]) {
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
