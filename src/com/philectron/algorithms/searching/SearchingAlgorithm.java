package com.philectron.algorithms.searching;

public interface SearchingAlgorithm {

    static final int INDEX_NOT_FOUND = -1;

    int findFirst(int[] array, int target);

    int findLast(int[] array, int target);

    default boolean contains(int[] array, int target) {
        return findFirst(array, target) != INDEX_NOT_FOUND;
    }

}
