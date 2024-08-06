package com.philectron.algorithms.searching;

import org.junit.jupiter.api.Test;

public class BinarySearchTest extends SearchTestBase {

    public BinarySearchTest() {
        super(new BinarySearch(), true);
    }

    @Override
    @Test
    public void search_nCopiesArray() {
        final int[] array = { 1, 1, 1, 1, 1 };
        assertSearchFound(array, array[array.length - 1], array.length / 2);
    }

    @Override
    @Test
    public void search_arbitraryArray_duplicateTargets() {
        final ArrayTarget arrayAndTargets = new ArrayTarget(true);
        final int[] array = arrayAndTargets.getArray();
        final int target = arrayAndTargets.getDuplicateTarget();
        assertSearchFound(array, target);
    }

}
