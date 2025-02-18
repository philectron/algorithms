package com.philectron.algorithms.sorting;

public class QuickSortHoareTest extends QuickSortTestBase {

    public QuickSortHoareTest() {
        super(new QuickSortHoare());
    }

    @Override
    QuickSort createQuickSorter() {
        return new QuickSortHoare();
    }

}
