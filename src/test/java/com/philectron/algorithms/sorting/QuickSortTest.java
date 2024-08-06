package com.philectron.algorithms.sorting;

import java.util.Random;

public class QuickSortTest extends SortTestBase {

    public QuickSortTest() {
        super(new QuickSort(new Random()));
    }

}
