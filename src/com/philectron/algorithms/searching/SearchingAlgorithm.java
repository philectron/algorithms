package com.philectron.algorithms.searching;

import javax.annotation.CheckForNull;

public interface SearchingAlgorithm {

    static final int INDEX_NOT_FOUND = -1;

    public int findFirst(final @CheckForNull int[] array, final int target);

    public int findLast(final @CheckForNull int[] array, final int target);

    public default boolean contains(final @CheckForNull int[] array, final int target) {
        return findFirst(array, target) != INDEX_NOT_FOUND;
    }

}
