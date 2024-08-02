package com.philectron.algorithms.sorting;

import com.google.common.base.Preconditions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SortUtils {

    public static void swap(@NonNull final int[] array, final int i, final int j) {
        Preconditions.checkArgument(array.length > 0, "Array must not be empty");
        Preconditions.checkArgument(0 <= i && i < array.length,
                "Index i = %d must be in range [0, %d]", i, array.length - 1);
        Preconditions.checkArgument(0 <= j && j < array.length,
                "Index j = %d must be in range [0, %d]", j, array.length - 1);

        // Don't swap if it's the same position.
        if (i == j) {
            return;
        }

        final int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static boolean isSorted(@NonNull final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
