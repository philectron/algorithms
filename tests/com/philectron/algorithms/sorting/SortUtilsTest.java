package com.philectron.algorithms.sorting;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SortUtilsTest {

    @Test
    public void swap_indexOutOfBound_fails() {
        int[] array = new int[1];
        assertThrows(IndexOutOfBoundsException.class, () -> SortUtils.swap(array, -1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> SortUtils.swap(array, 1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> SortUtils.swap(array, 0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> SortUtils.swap(array, 0, 1));
    }

    @Test
    public void swap_emptyArray_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> SortUtils.swap(new int[0], 0, 0));
    }

    @Test
    public void swap_singletonArray_doesNothing() {
        int[] actualArray = new int[1];
        int[] expectedArray = actualArray.clone();
        SortUtils.swap(actualArray, 0, 0);
        assertThat(actualArray).isEqualTo(expectedArray);
    }

    @Test
    public void swap_normalArray() {
        int[] actualArray = { 1, 2, 3, 4, 5 };
        SortUtils.swap(actualArray, 1, 3);
        assertThat(actualArray).isEqualTo(new int[] { 1, 4, 3, 2, 5 });
    }

    @Test
    public void swap_nCopiesArray_retainsOrder() {
        int[] actualArray = { 1, 1, 1, 1, 1 };
        int[] expectedArray = actualArray.clone();
        SortUtils.swap(actualArray, 0, actualArray.length - 1);
        assertThat(actualArray).isEqualTo(expectedArray);
    }

}
