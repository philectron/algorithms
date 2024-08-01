package com.philectron.algorithms.sorting;

import lombok.NonNull;

public class SelectionSort implements SortingAlgorithm {

    @Override
    public void sort(@NonNull final int[] array) {
        for (int i = 0, n = array.length; i < n - 1; i++) {
            int iMin = i;

            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[iMin]) {
                    iMin = j;
                }
            }

            if (iMin != i) {
                SortUtils.swap(array, iMin, i);
            }
        }
    }

}
