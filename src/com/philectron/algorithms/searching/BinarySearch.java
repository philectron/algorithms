package com.philectron.algorithms.searching;

import static com.google.common.base.Preconditions.checkNotNull;

public class BinarySearch implements SearchingAlgorithm {

    @Override
    public int findFirst(int[] sortedArray, int target) {
        checkNotNull(sortedArray);

        final int n = sortedArray.length;

        // Fast return if the target value is not even in the sorted array's value range.
        if (n == 0 || target < sortedArray[0] || target > sortedArray[n - 1]) {
            return -1;
        }

        // All indices are inclusive.
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            // Divide the array into [low..mid] and [mid + 1..high].
            final int mid = low + (high - low) / 2;

            // Only return the mid index if the mid value does not have any duplicates before it.
            if (target == sortedArray[mid] && (mid == 0 || target > sortedArray[mid - 1])) {
                return mid;
            }

            if (target > sortedArray[mid]) {
                // Move to the right half.
                low = mid + 1;
            } else {
                // This branch covers the case where the mid value has a duplicate before it.
                // Finding first will need to move to the left half on duplicates.
                high = mid - 1;
            }
        }

        return -1;
    }

    @Override
    public int findLast(int[] sortedArray, int target) {
        checkNotNull(sortedArray);

        final int n = sortedArray.length;

        // Fast return if the target value is not even in the sorted array's value range.
        if (n == 0 || target < sortedArray[0] || target > sortedArray[n - 1]) {
            return -1;
        }

        // All indices are inclusive.
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            // Divide the array into [low..mid] and [mid + 1..high].
            final int mid = low + (high - low) / 2;

            // Only return the mid index if the mid value does not have any duplicates after it.
            if (target == sortedArray[mid] && (mid == n - 1 || target < sortedArray[mid + 1])) {
                return mid;
            }

            if (target < sortedArray[mid]) {
                // Move to the left half.
                high = mid - 1;
            } else {
                // This branch covers the case where the mid value has a duplicate after it.
                // Finding last will need to move to the right half on duplicates.
                low = mid + 1;
            }
        }

        return -1;
    }

}
