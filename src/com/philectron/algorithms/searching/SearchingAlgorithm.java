package com.philectron.algorithms.searching;

public interface SearchingAlgorithm {

    static final int INDEX_NOT_FOUND = -1;

    /**
     * Finds the first occurrence of the specified target value in the specified array.
     *
     * @param array the array to be searched for the target value
     * @param target the target value whose first occurrence is to be searched
     *
     * @return the index of the first occurrence of the specified target value in the specified
     *         array, or {@value #INDEX_NOT_FOUND} if the target is not in the array
     *
     * @throws NullPointerException if the specified array is {@code null}
     */
    int findFirst(int[] array, int target);

    /**
     * Finds the last (final) occurrence of the specified target value in the specified array.
     *
     * @param array the array to be searched for the target value
     * @param target the target value whose last (final) occurrence is to be searched
     *
     * @return the index of the last (final) occurrence of the specified target value in the
     *         specified array, or {@value #INDEX_NOT_FOUND} if the target is not in the array
     *
     * @throws NullPointerException if the specified array is {@code null}
     */
    int findLast(int[] array, int target);

    /**
     * Checks if the specified array contains the specified target value. By default, this method
     * uses {@link #findFirst(int[], int)} to search for the target in the array.
     *
     * @param array the array to be searched for the target value
     * @param target the target value whose existence is to be checked in the array
     *
     * @return {@code true} if the specified array contains the specified target value, or
     *         {@code false} otherwise
     *
     * @throws NullPointerException if the specified array is {@code null}
     */
    default boolean contains(int[] array, int target) {
        return findFirst(array, target) != INDEX_NOT_FOUND;
    }

}
