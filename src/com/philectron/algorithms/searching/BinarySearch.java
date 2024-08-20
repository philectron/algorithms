package com.philectron.algorithms.searching;

import com.google.common.base.Preconditions;

public class BinarySearch implements SearchingAlgorithm {

    @Override
    public int findFirst(int[] sortedArray, int target) {
        Preconditions.checkNotNull(sortedArray);

        int low = 0;
        int high = sortedArray.length;

        while (low < high) {
            final int mid = low + (high - low) / 2;

            // Only return the mid index if the mid value does not have any duplicates before it.
            if (target == sortedArray[mid] && (mid == 0 || target > sortedArray[mid - 1])) {
                return mid;
            }

            if (target > sortedArray[mid]) {
                low = mid + 1;
            } else {
                // This branch covers the case where the mid value has a duplicate before it.
                // Finding first will need to move to the left half on duplicates.
                high = mid;
            }
        }

        return INDEX_NOT_FOUND;
    }

    @Override
    public int findLast(int[] sortedArray, int target) {
        Preconditions.checkNotNull(sortedArray);

        final int n = sortedArray.length;
        int low = 0;
        int high = sortedArray.length;

        while (low < high) {
            final int mid = low + (high - low) / 2;

            // Only return the mid index if the mid value does not have any duplicates after it.
            if (target == sortedArray[mid] && (mid == n - 1 || target < sortedArray[mid + 1])) {
                return mid;
            }

            if (target < sortedArray[mid]) {
                high = mid;
            } else {
                // This branch covers the case where the mid value has a duplicate after it.
                // Finding last will need to move to the right half on duplicates.
                low = mid + 1;
            }
        }

        return INDEX_NOT_FOUND;
    }

}
