package com.philectron.algorithms.searching;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.primitives.Ints;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public abstract class SearchTestBase {

    private static final int UNIQUE_TARGET = 2;
    private static final int DUPLICATE_TARGET = 5;
    private static final int NON_TARGET = 10;
    private static final int[] ARRAY = { DUPLICATE_TARGET, UNIQUE_TARGET, 8, 6, 4, 3, 5, 7, 1,
            DUPLICATE_TARGET };
    private static final int[] SORTED_ARRAY = ARRAY.clone();

    static {
        Arrays.sort(SORTED_ARRAY);
    }

    private final SearchingAlgorithm searcher;
    private final boolean isSortRequired;

    SearchTestBase(final SearchingAlgorithm searcher, final boolean isSortRequired) {
        this.searcher = searcher;
        this.isSortRequired = isSortRequired;
    }

    @Test
    public void search_nullArray_fails() {
        assertThrows(IllegalArgumentException.class, () -> searcher.findFirst(null, 1));
        assertThrows(IllegalArgumentException.class, () -> searcher.findLast(null, 1));
        assertThrows(IllegalArgumentException.class, () -> searcher.contains(null, 1));
    }

    @Test
    public void search_emptyArray_notFound() {
        assertNotFound(new int[0], 1);
    }

    @Test
    public void search_singletonArray() {
        final int[] array = new int[1];
        assertFound(array, array[0]);
    }

    @Test
    public void search_nCopiesArray() {
        final int target = 1;
        final int[] array = { target, target, target, target, target };
        assertFound(array, target);
    }

    @Test
    public void search_arbitraryArray_uniqueTarget() {
        assertFound(isSortRequired ? SORTED_ARRAY : ARRAY, UNIQUE_TARGET);
    }

    @Test
    public void search_arbitraryArray_duplicateTargets() {
        assertFound(isSortRequired ? SORTED_ARRAY : ARRAY, DUPLICATE_TARGET);
    }

    @Test
    public void search_arbitraryArray_notFound() {
        assertNotFound(isSortRequired ? SORTED_ARRAY : ARRAY, NON_TARGET);
    }

    private void assertFound(final int[] array, final int target) {
        assertFirstIndex(array, target, Ints.indexOf(array, target));
        assertLastIndex(array, target, Ints.lastIndexOf(array, target));
        assertContains(array, target, true);
    }

    private void assertNotFound(final int[] array, final int target) {
        assertFirstIndex(array, target, SearchingAlgorithm.INDEX_NOT_FOUND);
        assertLastIndex(array, target, SearchingAlgorithm.INDEX_NOT_FOUND);
        assertContains(array, target, false);
    }

    private void assertFirstIndex(final int[] array, final int target, final int expectedIndex) {
        assertThat(
                String.format("First index of target %d in array: %s", target,
                        Arrays.toString(array)),
                searcher.findFirst(array, target), is(expectedIndex));
    }

    private void assertLastIndex(final int[] array, final int target, final int expectedIndex) {
        assertThat(
                String.format("Last index of target %d in array: %s", target,
                        Arrays.toString(array)),
                searcher.findLast(array, target), is(expectedIndex));
    }

    private void assertContains(final int[] array, final int target, final boolean expectedResult) {
        assertThat(String.format("Target %d in array: %s", target, Arrays.toString(array)),
                searcher.contains(array, target), is(expectedResult));
    }

}
