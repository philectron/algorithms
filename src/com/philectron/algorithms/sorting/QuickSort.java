package com.philectron.algorithms.sorting;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class QuickSort implements SortingAlgorithm {

    @NonNull
    private final Random random;

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
        // Choose a random element as the pivot, then swap it with the last element.
        final int pivotIndex = random.nextInt(high - low) + low;
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
