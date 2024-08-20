package com.philectron.algorithms.sorting;

import com.google.common.base.Preconditions;

public class InsertionSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        Preconditions.checkNotNull(array);

        for (int nSorted = 1, n = array.length; nSorted < n; nSorted++) {
            for (int i = nSorted; i > 0 && array[i - 1] > array[i]; i--) {
                SortUtils.swap(array, i - 1, i);
            }
        }
    }

}
