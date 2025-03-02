package com.philectron.algorithms.sorting;

import static com.google.common.base.Preconditions.checkNotNull;

public class CountingSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        checkNotNull(array);

        // Empty array is considered sorted.
        if (array.length == 0) {
            return;
        }

        // Find min and max elements in the array.
        int min = array[0];
        int max = array[0];
        for (int i = 0; i < array.length; ++i) {
            min = Math.min(min, array[i]);
            max = Math.max(max, array[i]);
        }

        // Make a count array to store the frequency of each element's offset against min.
        // count[i] = frequency of (element = i + min)
        // For example, count[range] = frequency of (range + min) = frequency of max
        final int range = max - min;
        int[] count = new int[range + 1]; // [0..range]

        // Populate the count array.
        for (int number : array) {
            ++count[number - min];
        }

        // Convert the count array to a prefix sum (cumulative count) array.
        // Then, each value count[i] - 1 = ending index of element (i + min) in the sorted array
        for (int i = 1; i < count.length; ++i) {
            count[i] += count[i - 1];
        }

        // Move from right to left to maintain the stable sort.
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; --i) {
            final int element = array[i];
            final int countIndex = element - min;
            final int sortedIndex = count[countIndex] - 1;

            // Put the current element to its final position in the sorted array.
            sortedArray[sortedIndex] = element;

            // Update its count.
            --count[countIndex];
        }

        // Mutate the original array.
        for (int i = 0; i < array.length; ++i) {
            array[i] = sortedArray[i];
        }
    }

}
