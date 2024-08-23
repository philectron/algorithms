package com.philectron.algorithms.sorting;

public interface SortingAlgorithm {

    /**
     * Sorts {@code array} according to the natural ordering of its elements. After this method
     * returns, each element in {@code array} is greater than or equal to the element that precedes
     * it.
     *
     * @param array the array to be sorted
     *
     * @throws NullPointerException if {@code array} is {@code null}
     */
    void sort(int[] array);

}
