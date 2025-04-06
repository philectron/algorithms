package com.philectron.algorithms.sorting;

public class QuickSort3WayTest extends QuickSortTestBase {

    QuickSort3WayTest() {
        super(new QuickSort3Way());
    }

    @Override
    QuickSort createQuickSorter() {
        return new QuickSort3Way();
    }

}
