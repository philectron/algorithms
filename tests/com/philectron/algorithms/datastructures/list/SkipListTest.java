package com.philectron.algorithms.datastructures.list;

public class SkipListTest {

    private static SkipList<Integer> skiplist;

    public static void main(String[] args) {
        skiplist = new SkipList<>();

        // print();

        skiplist.add(10);
        // print();
        skiplist.add(50);
        // print();
        skiplist.add(20);
        // print();
        skiplist.add(40);
        // print();
        skiplist.add(30);
        skiplist.add(10);
        print();
    }

    private static void print() {
        System.out.println(skiplist.toString());
    }

}
