package com.philectron.algorithms.searching;

import static com.google.common.base.Preconditions.checkNotNull;

public class LinearSearch implements SearchingAlgorithm {

    @Override
    public int findFirst(int[] array, int target) {
        checkNotNull(array);

        for (int i = 0, n = array.length; i < n; i++) {
            if (array[i] == target) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    @Override
    public int findLast(int[] array, int target) {
        checkNotNull(array);

        for (int n = array.length, i = n - 1; i >= 0; i--) {
            if (array[i] == target) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

}
