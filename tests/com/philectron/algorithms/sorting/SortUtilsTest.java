package com.philectron.algorithms.sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class SortUtilsTest {

    @Test
    public void swap_nullArray_fails() {
        assertThrows(IllegalArgumentException.class, () -> SortUtils.swap(null, 0, 0));
    }

    @Test
    public void swap_indexOutOfBound_fails() {
        final int[] array = new int[1];
        assertThrows(IllegalArgumentException.class, () -> SortUtils.swap(array, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> SortUtils.swap(array, 1, 0));
        assertThrows(IllegalArgumentException.class, () -> SortUtils.swap(array, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> SortUtils.swap(array, 0, 1));
    }

    @Test
    public void swap_emptyArray_fails() {
        assertThrows(IllegalArgumentException.class, () -> SortUtils.swap(new int[0], 0, 0));
    }

    @Test
    public void swap_singletonArray_doesNothing() {
        final int[] actualArray = new int[1];
        final int[] expectedArray = actualArray.clone();
        SortUtils.swap(actualArray, 0, 0);
        assertThat(actualArray, is(expectedArray));
    }

    @Test
    public void swap_normalArray() {
        final int[] actualArray = { 1, 2, 3, 4, 5 };
        SortUtils.swap(actualArray, 1, 3);
        assertThat(actualArray, is(new int[] { 1, 4, 3, 2, 5 }));
    }

    @Test
    public void swap_nCopiesArray_doesNothing() {
        final int[] actualArray = { 1, 1, 1, 1, 1 };
        final int[] expectedArray = actualArray.clone();
        SortUtils.swap(actualArray, 0, actualArray.length - 1);
        assertThat(actualArray, is(expectedArray));
    }

    @Test
    public void isSorted_nullArray_fails() {
        assertThrows(IllegalArgumentException.class, () -> SortUtils.isSorted(null));
    }

    @Test
    public void isSorted_emptyArray_yes() {
        assertThat("Empty array is considered sorted", SortUtils.isSorted(new int[0]), is(true));
    }

    @Test
    public void isSorted_singletonArray_yes() {
        assertThat("Singleton array is considered sorted", SortUtils.isSorted(new int[1]),
                is(true));
    }

    @Test
    public void isSorted_ascendingArray_yes() {
        final int[] ascendingArray = { 1, 2, 3, 4, 5 };
        assertThat("Ascending array is considered sorted: " + Arrays.toString(ascendingArray),
                SortUtils.isSorted(ascendingArray), is(true));
    }

    @Test
    public void isSorted_nCopiesArray_yes() {
        final int[] nCopiesArray = { 1, 1, 1, 1, 1 };
        assertThat("N-copies array is considered sorted: " + Arrays.toString(nCopiesArray),
                SortUtils.isSorted(nCopiesArray), is(true));
    }

    @Test
    public void isSorted_unsortedArray_no() {
        final int[] unsortedArray = { 1, 2, 3, 5, 4 };
        assertThat("Unsorted array is considered not sorted: " + Arrays.toString(unsortedArray),
                SortUtils.isSorted(unsortedArray), is(false));
    }

    @Test
    public void shuffle_nullArrayOrRandom_fails() {
        assertThrows(IllegalArgumentException.class, () -> SortUtils.shuffle(null, null));
        assertThrows(IllegalArgumentException.class, () -> SortUtils.shuffle(null, new Random()));
        assertThrows(IllegalArgumentException.class, () -> SortUtils.shuffle(new int[1], null));
    }

    @Test
    public void shuffle_emptyArray_doesNothing() {
        final int[] originalArray = new int[0];
        final int[] shuffledArray = originalArray.clone();
        SortUtils.shuffle(shuffledArray, new Random());
        assertThat(shuffledArray, is(originalArray));
    }

    @Test
    public void shuffle_singletonArray_keepsOrder() {
        final int[] originalArray = new int[1];
        final int[] shuffledArray = originalArray.clone();
        SortUtils.shuffle(shuffledArray, new Random());
        assertThat(shuffledArray, is(originalArray));
    }

    @Test
    public void shuffle_nCopiesArray_keepsOrder() {
        final int[] originalArray = { 1, 1, 1, 1, 1 };
        final int[] shuffledArray = originalArray.clone();
        SortUtils.shuffle(shuffledArray, new Random());
        assertThat(shuffledArray, is(originalArray));
    }

    @Test
    @Timeout(1)
    public void shuffle_arbitraryArray_changesOrder() {
        final int[] originalArray = { 1, 4, 5, 3, 2 };
        final int[] shuffledArray = originalArray.clone();
        final Random random = new Random();

        // It is possible, though very unlikely, to have the same array after a shuffle.
        // We set a timeout for this test method in case this loop runs longer than we want it to.
        while (Arrays.equals(shuffledArray, originalArray)) {
            SortUtils.shuffle(shuffledArray, random);
        }

        assertThat(shuffledArray, is(not(originalArray)));

        final int[] sortedOriginalArray = originalArray.clone();
        final int[] sortedShuffledArray = shuffledArray.clone();
        Arrays.sort(sortedOriginalArray);
        Arrays.sort(sortedShuffledArray);
        assertThat(sortedShuffledArray, is(sortedOriginalArray));
    }

}
