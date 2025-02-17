package com.philectron.algorithms.sorting;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuickSortHoareTest extends SortTestBase {

    @Mock
    private Random random;

    @InjectMocks
    private QuickSortHoare sorter;

    public QuickSortHoareTest() {
        super(new QuickSortHoare(new Random()));
    }

    @Test
    public void sort_nCopiesArray_pivotLow() {
        int[] array = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };

        when(random.nextDouble()).thenReturn(0.0);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_nCopiesArray_pivotMid() {
        int[] array = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };

        when(random.nextDouble()).thenReturn(0.5);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_nCopiesArray_pivotHigh() {
        int[] array = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };

        when(random.nextDouble()).thenReturn(0.9);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_ascendingArray_pivotLow() {
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };

        when(random.nextDouble()).thenReturn(0.0);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_ascendingArray_pivotMid() {
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };

        when(random.nextDouble()).thenReturn(0.5);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_ascendingArray_pivotHigh() {
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };

        when(random.nextDouble()).thenReturn(0.9);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_descendingArray_pivotLow() {
        int[] array = new int[] { 8, 7, 6, 5, 4, 3, 2, 1 };

        when(random.nextDouble()).thenReturn(0.0);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_descendingArray_pivotMid() {
        int[] array = new int[] { 8, 7, 6, 5, 4, 3, 2, 1 };

        when(random.nextDouble()).thenReturn(0.5);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_descendingArray_pivotHigh() {
        int[] array = new int[] { 8, 7, 6, 5, 4, 3, 2, 1 };

        when(random.nextDouble()).thenReturn(0.9);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_arbitraryArray_pivotLow() {
        int[] array =
                new int[] { 21, 37, 36, 19, 30, 27, 25, 36, 32, 28, 13, 33, 34, 20, 30, 4, 15, 40 };

        when(random.nextDouble()).thenReturn(0.0);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_arbitraryArray_pivotMid() {
        int[] array =
                new int[] { 21, 37, 36, 19, 30, 27, 25, 36, 32, 28, 13, 33, 34, 20, 30, 4, 15, 40 };

        when(random.nextDouble()).thenReturn(0.5);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    public void sort_arbitraryArray_pivotHigh() {
        int[] array =
                new int[] { 21, 37, 36, 19, 30, 27, 25, 36, 32, 28, 13, 33, 34, 20, 30, 4, 15, 40 };

        when(random.nextDouble()).thenReturn(0.9);
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

}
