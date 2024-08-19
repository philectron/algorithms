package com.philectron.algorithms.sorting;

import com.google.common.base.Preconditions;

public final class SortUtils {

    private SortUtils() {}

    public static void swap(final int[] array, final int i, final int j) {
        Preconditions.checkNotNull(array);
        Preconditions.checkElementIndex(i, array.length);
        Preconditions.checkElementIndex(j, array.length);

        // Don't swap if it's the same position.
        if (i == j) {
            return;
        }

        final int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
