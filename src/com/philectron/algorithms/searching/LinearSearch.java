package com.philectron.algorithms.searching;

import lombok.NonNull;

public class LinearSearch implements SearchingAlgorithm {

    @Override
    public int findFirst(final @NonNull int[] array, final int target) {
        for (int i = 0, n = array.length; i < n; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    @Override
    public int findLast(final @NonNull int[] array, final int target) {
        for (int n = array.length, i = n - 1; i >= 0; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

}
