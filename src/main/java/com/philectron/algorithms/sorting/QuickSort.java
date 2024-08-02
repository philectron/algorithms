package com.philectron.algorithms.sorting;

import lombok.NonNull;

public class QuickSort implements SortingAlgorithm {

    @Override
    public void sort(@NonNull final int[] array) {
        quicksort(array, 0, array.length);
    }

    private void quicksort(final int[] array, final int low, final int high) {
        if (low >= high - 1) {
            return;
        }

        final int pivotIndex = partition(array, low, high);

        // Sort left half and right half of the array, excluding the pivot itself,
        // because its position after the partitioning is final.
        quicksort(array, low, pivotIndex);
        quicksort(array, pivotIndex + 1, high);
    }

    private int partition(final int[] array, final int low, final int high) {
        final int pivot = array[high - 1];
        int finalPivotIndex = low;

        for (int i = low; i < high - 1; i++) {
            if (array[i] < pivot) {
                SortUtils.swap(array, i, finalPivotIndex++);
            }
        }

        SortUtils.swap(array, finalPivotIndex, high - 1);

        return finalPivotIndex;
    }

}
