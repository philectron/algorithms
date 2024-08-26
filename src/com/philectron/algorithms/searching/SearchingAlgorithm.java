package com.philectron.algorithms.searching;

import static com.google.common.base.Preconditions.checkNotNull;

public interface SearchingAlgorithm {

    /**
     * Finds the first occurrence of {@code target} in {@code array}.
     *
     * @param array the array that may or may not contain {@code target}
     * @param target the target value to be searched in {@code array}
     *
     * @return the index of the first occurrence of {@code target} in {@code array}, or {@code -1}
     *         if {@code target} is not in {@code array}
     *
     * @throws NullPointerException if {@code array} is {@code null}
     */
    int findFirst(int[] array, int target);

    /**
     * Finds the last (final) occurrence of {@code target} in {@code array}.
     *
     * @param array the array that may or may not contain {@code target}
     * @param target the target value to be searched in {@code array}
     *
     * @return the index of the last (final) occurrence of {@code target} in {@code array}, or
     *         {@code -1} if {@code target} is not in {@code array}
     *
     * @throws NullPointerException if {@code array} is {@code null}
     */
    int findLast(int[] array, int target);

    /**
     * Checks if {@code array} contains {@code target}.
     *
     * @param array the array that may or may not contain {@code target}
     * @param target the target value to be searched in {@code array}
     *
     * @return {@code true} if {@code array} contains {@code target}, or {@code false} otherwise
     *
     * @throws NullPointerException if {@code array} is {@code null}
     */
    default boolean contains(int[] array, int target) {
        checkNotNull(array);
        return findFirst(array, target) >= 0;
    }

}
