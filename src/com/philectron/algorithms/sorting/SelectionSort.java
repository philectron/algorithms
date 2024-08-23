package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;

public class SelectionSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        checkNotNull(array);

        for (int i = 0, n = array.length; i < n - 1; i++) {
            int iMin = i;

            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[iMin]) {
                    iMin = j;
                }
            }

            SortUtils.swap(array, iMin, i);
        }
    }

}
