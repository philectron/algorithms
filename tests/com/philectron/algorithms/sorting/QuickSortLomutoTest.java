package com.philectron.algorithms.sorting;

public class QuickSortLomutoTest extends QuickSortTestBase {

    QuickSortLomutoTest() {
        super(new QuickSortLomuto());
    }

    @Override
    QuickSort createQuickSorter() {
        return new QuickSortLomuto();
    }

}
