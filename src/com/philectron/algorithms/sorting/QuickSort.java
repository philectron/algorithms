package com.philectron.algorithms.sorting;

import com.google.common.base.Preconditions;
import java.util.Random;

public class QuickSort implements SortingAlgorithm {

    private final Random random;

    public QuickSort() {
        random = new Random();
    }

    @Override
    public void sort(int[] array) {
        Preconditions.checkNotNull(array);
        quicksort(array, 0, array.length);
    }

    private void quicksort(int[] array, int low, int high) {
        if (low >= high - 1) {
            return;
        }

        final int pivotIndex = partition(array, low, high);

        // Sort left half and right half of the array, excluding the pivot itself,
        // because its position after the partitioning is final.
        quicksort(array, low, pivotIndex);
        quicksort(array, pivotIndex + 1, high);
    }

    private int partition(int[] array, int low, int high) {
        // Choose a random element as the pivot, then swap it with the last element.
        final int pivotIndex = low + random.nextInt(high - low);
        final int pivot = array[pivotIndex];
        SortUtils.swap(array, pivotIndex, high - 1);

        int correctPivotIndex = low;

        for (int i = low; i < high - 1; i++) {
            if (array[i] < pivot) {
                SortUtils.swap(array, i, correctPivotIndex++);
            }
        }

        SortUtils.swap(array, correctPivotIndex, high - 1);

        return correctPivotIndex;
    }

}
