package com.philectron.algorithms.sorting;

public interface QuickSort extends SortingAlgorithm {

    /**
     * Chooses a random pivot index between two inclusive bounds.
     *
     * @param lowerBound the lower bound, inclusive
     * @param upperBound the upper bound, inclusive
     *
     * @return a random index in the range {@code [lowerBound, upperBound]}
     */
    default int getRandomPivotIndex(int lowerBound, int upperBound) {
        return lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));
    }

}
