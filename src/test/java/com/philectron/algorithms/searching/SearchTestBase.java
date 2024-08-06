package com.philectron.algorithms.searching;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.google.common.primitives.Ints;

import lombok.Getter;

public abstract class SearchTestBase {

    private final SearchingAlgorithm searcher;
    private final boolean isSortRequired;

    SearchTestBase(final SearchingAlgorithm searcher, final boolean isSortRequired) {
        this.searcher = searcher;
        this.isSortRequired = isSortRequired;
    }

    @Test
    public void search_nullArray_fails() {
        assertThrows(RuntimeException.class, () -> searcher.search(null, 1));
    }

    @Test
    public void search_emptyArray_notFound() {
        assertSearchNotFound(new int[0], 1);
    }

    @Test
    public void search_singletonArray() {
        final int[] array = new int[1];
        assertSearchFound(array, array[0], 0);
        assertSearchNotFound(array, array[0] + 1);
    }

    @Test
    public void search_nCopiesArray() {
        final int[] array = { 1, 1, 1, 1, 1 };
        assertSearchFound(array, array[array.length - 1], 0);
    }

    @Test
    public void search_arbitraryArray_uniqueTarget() {
        final ArrayTarget arrayAndTargets = new ArrayTarget(isSortRequired);
        final int[] array = arrayAndTargets.getArray();
        final int target = arrayAndTargets.getUniqueTarget();
        assertSearchFound(array, target, Ints.indexOf(array, target));
    }

    @Test
    public void search_arbitraryArray_duplicateTargets() {
        final ArrayTarget arrayAndTargets = new ArrayTarget(isSortRequired);
        final int[] array = arrayAndTargets.getArray();
        final int target = arrayAndTargets.getDuplicateTarget();
        assertSearchFound(array, target, Ints.indexOf(array, target));
    }

    void assertSearchFound(final int[] array, final int target) {
        assertThat(String.format("Target %d is in array %s", target, Arrays.toString(array)),
                searcher.search(array, target), is(greaterThan(-1)));
    }

    void assertSearchFound(final int[] array, final int target, final int expectedIndex) {
        assertThat(
                String.format("Target %d is in array %s at index", target, Arrays.toString(array)),
                searcher.search(array, target), is(expectedIndex));
    }

    void assertSearchNotFound(final int[] array, final int target) {
        assertThat(String.format("Target %d is not in array: %s", target, Arrays.toString(array)),
                searcher.search(array, target), is(-1));
    }

    @Getter
    static class ArrayTarget {
        private final int[] array;
        private final int uniqueTarget;
        private final int duplicateTarget;

        public ArrayTarget(final boolean isSortRequired) {
            uniqueTarget = 34;
            duplicateTarget = 30;
            array = new int[] { 21, 37, 36, 19, duplicateTarget, 27, 25, 36, 32, 28, 13, 33,
                    uniqueTarget, 20, duplicateTarget, 4, 15, 40, 5, 10 };
            if (isSortRequired) {
                Arrays.sort(array);
            }
        }
    }

}
