package com.philectron.algorithms.searching;

import lombok.NonNull;

public interface SearchingAlgorithm {

    static final int INDEX_NOT_FOUND = -1;

    public int findFirst(@NonNull final int[] array, final int target);

    public int findLast(@NonNull final int[] array, final int target);

    public default boolean contains(@NonNull final int[] array, final int target) {
        return findFirst(array, target) != INDEX_NOT_FOUND;
    }

}
