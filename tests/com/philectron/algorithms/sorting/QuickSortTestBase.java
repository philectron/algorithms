package com.philectron.algorithms.sorting;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.AdditionalAnswers.returnsLastArg;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class QuickSortTestBase extends SortTestBase {

    private QuickSort sorter;

    QuickSortTestBase(QuickSort sorter) {
        super(sorter);
    }

    abstract QuickSort createQuickSorter();

    @BeforeEach
    void setUp() {
        sorter = spy(createQuickSorter());
        assertThat(sorter).isNotNull();
    }

    @Test
    void sort_nCopiesArray_pivotLow() {
        doAnswer(returnsFirstArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildNCopiesArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_nCopiesArray_pivotHigh() {
        doAnswer(returnsLastArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildNCopiesArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_ascendingArray_pivotLow() {
        doAnswer(returnsFirstArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildAscendingArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_ascendingArray_pivotHigh() {
        doAnswer(returnsLastArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildAscendingArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_descendingArray_pivotLow() {
        doAnswer(returnsFirstArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildDescendingArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_descendingArray_pivotHigh() {
        doAnswer(returnsLastArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildDescendingArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_largeMiddleArray_pivotLow() {
        doAnswer(returnsFirstArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildLargeMiddleArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_largeMiddleArray_pivotHigh() {
        doAnswer(returnsLastArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildLargeMiddleArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_arbitraryArray_pivotLow() {
        doAnswer(returnsFirstArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildArbitraryArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

    @Test
    void sort_arbitraryArray_pivotHigh() {
        doAnswer(returnsLastArg()).when(sorter).getRandomPivotIndex(anyInt(), anyInt());

        int[] array = SortTestBase.buildArbitraryArray();
        sorter.sort(array);

        assertThat(array).asList().isInOrder();
    }

}
