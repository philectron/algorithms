package com.philectron.algorithms.sorting;

public class QuickSortHoareTest extends QuickSortTestBase {

    QuickSortHoareTest() {
        super(new QuickSortHoare());
    }

    @Override
    QuickSort createQuickSorter() {
        return new QuickSortHoare();
    }

}
