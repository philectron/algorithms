package com.philectron.algorithms.searching;

import lombok.NonNull;

public class LinearSearch implements SearchingAlgorithm {

    @Override
    public int search(@NonNull final int[] array, final int target) {
        for (int i = 0, n = array.length; i < n; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // not found
    }

}
