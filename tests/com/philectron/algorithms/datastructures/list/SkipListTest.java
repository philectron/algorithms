package com.philectron.algorithms.datastructures.list;

public class SkipListTest extends SortedListTestBase {

    @Override
    SortedList<Integer> createSortedList(Iterable<Integer> iterable) {
        return new SkipList<>(iterable);
    }

    private static SkipList<Integer> skiplist;

    public static void main(String[] args) {
        skiplist = new SkipList<>();

        // print();

        skiplist.add(10);
        // print();
        skiplist.add(20);
        // print();
        skiplist.add(30);
        // print();
        skiplist.add(40);
        // print();
        skiplist.add(50);
        // print();
        skiplist.add(60);
        print();
    }

    private static void print() {
        System.out.println(skiplist.toString());
    }

}
