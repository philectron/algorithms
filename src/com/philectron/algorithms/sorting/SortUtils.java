package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

public final class SortUtils {

    private SortUtils() {}

    public static void swap(int[] array, int i, int j) {
        checkNotNull(array);
        checkElementIndex(i, array.length);
        checkElementIndex(j, array.length);

        // Don't swap if it's the same position.
        if (i == j) {
            return;
        }

        final int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
