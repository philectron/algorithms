package com.philectron.algorithms.searching;

import lombok.NonNull;

public class BinarySearch implements SearchingAlgorithm {

    @Override
    public int search(@NonNull final int[] sortedArray, final int target) {
        return binarySearch(sortedArray, target, 0, sortedArray.length);
    }

    public int binarySearch(final int[] sortedArray, final int target, final int low,
            final int high) {
        if (low >= high) {
            return -1; // not found
        }

        final int mid = (low + high) / 2;

        if (target == sortedArray[mid]) {
            return mid;
        }

        if (target < sortedArray[mid]) {
            return binarySearch(sortedArray, target, low, mid);
        }

        return binarySearch(sortedArray, target, mid + 1, high);
    }

}
