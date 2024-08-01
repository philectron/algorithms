package com.philectron.algorithms.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class SortUtilsTest {

    @Test
    public void swapNullArrayShallThrow() {
        assertThrows(RuntimeException.class, () -> SortUtils.swap(null, 0, 0));
    }

    @Test
    public void swapIndexOutOfBoundShallThrow() {
        assertThrows(RuntimeException.class, () -> SortUtils.swap(new int[1], -1, 0));
        assertThrows(RuntimeException.class, () -> SortUtils.swap(new int[1], 1, 0));
        assertThrows(RuntimeException.class, () -> SortUtils.swap(new int[1], 0, -1));
        assertThrows(RuntimeException.class, () -> SortUtils.swap(new int[1], 0, 1));
    }

    @Test
    public void swapEmptyArrayShallThrow() {
        assertThrows(RuntimeException.class, () -> SortUtils.swap(new int[0], 0, 0));
    }

    @Test
    public void swapSingletonArrayShallSucceed() {
        final int[] actualArray = { 123 };
        SortUtils.swap(actualArray, 0, 0);
        assertArrayEquals(new int[] { 123 }, actualArray);
    }

    @Test
    public void swapNormalArrayShallSwapValues() {
        final int[] actualArray = { 123, 456, 789, 101, 112 };
        SortUtils.swap(actualArray, 1, 3);
        assertArrayEquals(new int[] { 123, 101, 789, 456, 112 }, actualArray);
    }

    @Test
    public void isSortedForNullArrayShallThrow() {
        assertThrows(RuntimeException.class, () -> SortUtils.isSorted(null));
    }

    @Test
    public void isSortedForEmptyArrayShallReturnTrue() {
        assertTrue(SortUtils.isSorted(new int[0]), "Expected empty array to be considered sorted");
    }

    @Test
    public void isSortedForSingletonArrayShallReturnTrue() {
        assertTrue(SortUtils.isSorted(new int[1]),
                "Expected singleton array to be considered sorted");
    }

    @Test
    public void isSortedForSortedArrayShallReturnTrue() {
        final int[] ascendingArray = { 1, 2, 3 };
        assertTrue(SortUtils.isSorted(ascendingArray),
                "Expected array to be considered sorted: " + Arrays.toString(ascendingArray));

        final int[] nCopiesArray = { 1, 1, 1 };
        assertTrue(SortUtils.isSorted(nCopiesArray),
                "Expected array to be considered sorted: " + Arrays.toString(nCopiesArray));
    }

    @Test
    public void isSortedForUnsortedArrayShallReturnFalse() {
        final int[] unsortedArray = { 1, 3, 2 };
        assertFalse(SortUtils.isSorted(unsortedArray),
                "Expected array to not be considered sorted: " + Arrays.toString(unsortedArray));
    }

}
