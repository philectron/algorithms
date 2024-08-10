package com.philectron.algorithms.searching;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.primitives.Ints;
import java.util.Arrays;
import lombok.Getter;
import org.junit.jupiter.api.Test;

public abstract class SearchTestBase {

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
        assertFindFirst(array, array[0], 0);
        assertFindLast(array, array[0], 0);
        assertContains(array, 0);
    }

    @Test
    public void search_nCopiesArray() {
        final int target = 1;
        final int[] array = { target, target, target, target, target };
        assertFindFirst(array, target, 0);
        assertFindLast(array, target, array.length - 1);
        assertContains(array, target);
    }

    @Test
    public void search_arbitraryArray_uniqueTarget() {
        final ArrayTarget arrayAndTargets = new ArrayTarget(isSortRequired);
        final int[] array = arrayAndTargets.getArray();
        final int target = arrayAndTargets.getUniqTarget();
        assertFindFirst(array, target, Ints.indexOf(array, target));
        assertFindLast(array, target, Ints.lastIndexOf(array, target));
        assertContains(array, target);
    }

    @Test
    public void search_arbitraryArray_duplicateTargets() {
        final ArrayTarget arrayAndTargets = new ArrayTarget(isSortRequired);
        final int[] array = arrayAndTargets.getArray();
        final int target = arrayAndTargets.getDupTarget();
        assertFindFirst(array, target, Ints.indexOf(array, target));
        assertFindLast(array, target, Ints.lastIndexOf(array, target));
        assertContains(array, target);
    }

    void assertContains(final int[] array, final int target) {
        assertThat(String.format("Target %d in array: %s", target, Arrays.toString(array)),
                searcher.contains(array, target), is(true));
    }

    void assertNotFound(final int[] array, final int target) {
        assertFindFirst(array, target, -1);
        assertFindLast(array, target, -1);
        assertThat(String.format("Target %d in array: %s", target, Arrays.toString(array)),
                searcher.contains(array, target), is(false));
    }

    private void assertFindFirst(final int[] array, final int target, final int expectedIndex) {
        assertThat(
                String.format("First index of target %d in array: %s", target,
                        Arrays.toString(array)),
                searcher.findFirst(array, target), is(expectedIndex));
    }

    private void assertFindLast(final int[] array, final int target, final int expectedIndex) {
        assertThat(
                String.format("Last index of target %d in array: %s", target,
                        Arrays.toString(array)),
                searcher.findLast(array, target), is(expectedIndex));
    }

    @Getter
    static class ArrayTarget {
        private final int[] array;
        private final int uniqTarget;
        private final int dupTarget;

        public ArrayTarget(final boolean isSortRequired) {
            uniqTarget = 2;
            dupTarget = 5;
            array = new int[] { dupTarget, uniqTarget, 8, 6, 4, 3, 5, 7, 1, dupTarget };
            if (isSortRequired) {
                Arrays.sort(array);
            }
        }
    }

}
