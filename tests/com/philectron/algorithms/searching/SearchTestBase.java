package com.philectron.algorithms.searching;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import com.google.common.primitives.Ints;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public abstract class SearchTestBase {

    private static final int UNIQUE_TARGET = 2;
    private static final int DUPLICATE_TARGET = 5;
    private static final int NON_TARGET = 10;
    private static final int[] ARRAY =
            { DUPLICATE_TARGET, UNIQUE_TARGET, 8, 6, 4, 3, 5, 7, 1, DUPLICATE_TARGET };
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
    public void search_emptyArray_returnsNotFound() {
        assertNotFound(new int[0], 1);
    }

    @Test
    public void search_singletonArray_returnsIndex() {
        final int[] array = new int[1];
        assertFound(array, array[0]);
    }

    @Test
    public void search_nCopiesArray_returnsIndexes() {
        final int target = 1;
        final int[] array = { target, target, target, target, target };
        assertFound(array, target);
    }

    @Test
    public void search_uniqueTarget_returnsIndex() {
        assertFound(isSortRequired ? SORTED_ARRAY : ARRAY, UNIQUE_TARGET);
    }

    @Test
    public void search_duplicateTargets_returnsIndexes() {
        assertFound(isSortRequired ? SORTED_ARRAY : ARRAY, DUPLICATE_TARGET);
    }

    @Test
    public void search_nonTarget_returnsNotFound() {
        assertNotFound(isSortRequired ? SORTED_ARRAY : ARRAY, NON_TARGET);
    }

    private void assertFound(final int[] array, final int target) {
        final int[] originalArray = array.clone();
        assertFirstIndex(array, target, Ints.indexOf(array, target));
        assertLastIndex(array, target, Ints.lastIndexOf(array, target));
        assertContains(array, target, true);
        assertThat(array).isEqualTo(originalArray); // Searching should not mutate the array.
    }

    private void assertNotFound(final int[] array, final int target) {
        final int[] originalArray = array.clone();
        assertFirstIndex(array, target, SearchingAlgorithm.INDEX_NOT_FOUND);
        assertLastIndex(array, target, SearchingAlgorithm.INDEX_NOT_FOUND);
        assertContains(array, target, false);
        assertThat(array).isEqualTo(originalArray); // Searching should not mutate the array.
    }

    private void assertFirstIndex(final int[] array, final int target, final int expectedIndex) {
        assertWithMessage("First index of target %s in array %s", target, Arrays.toString(array))
                .that(searcher.findFirst(array, target))
                .isEqualTo(expectedIndex);
    }

    private void assertLastIndex(final int[] array, final int target, final int expectedIndex) {
        assertWithMessage("Last index of target %s in array %s", target, Arrays.toString(array))
                .that(searcher.findLast(array, target))
                .isEqualTo(expectedIndex);
    }

    private void assertContains(final int[] array, final int target, final boolean expectedResult) {
        assertWithMessage("Whether array %s contains target %s", Arrays.toString(array), target)
                .that(searcher.contains(array, target))
                .isEqualTo(expectedResult);
    }

}
