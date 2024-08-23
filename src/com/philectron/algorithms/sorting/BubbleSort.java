package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;

public class BubbleSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        checkNotNull(array);

        for (int i = 0, n = array.length; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtils.swap(array, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

}
