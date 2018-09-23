// binary_search.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Binary Search in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// Finds an element in a sorted array by checking the left and right halves of
// the array. Returns the index of the element if found. Returns -1 otherwise.

#ifndef ALGORITHMS_INCLUDE_BINARYSEARCH_HPP_
#define ALGORITHMS_INCLUDE_BINARYSEARCH_HPP_

#include <cstddef>
#include <vector>

namespace algorithm {

namespace {

// Performs binary search on the array within the interval [begin, end).
template <class Comparable>
int BinarySearch(const Comparable& key, Comparable* array,
                 size_t begin, size_t end) {
    // stop index or element is out of bound
    if (begin >= end || key < array[begin] || key > array[end - 1]) return -1;

    size_t low = begin, high = end, middle;

    // use  low ,  high , and  middle  to simulate splitting the array in halves
    while (low < high) {
        middle = (low + high) / 2;

        if (key == array[middle]) return middle;  // return index if found

        if (key < array[middle])
            high = middle;
        else
            low = middle + 1;
    }

    return -1;  // return -1 in other cases
}

// Performs binary search on the array within the interval [begin, end).
template <class Comparable>
int BinarySearch(const Comparable& key, const std::vector<Comparable>& array,
                size_t begin, size_t end) {
    // stop index or element is out of bound
    if (begin >= end || key < array[begin] || key > array[end - 1]) return -1;

    size_t low = begin, high = end, middle;

    // use  low ,  high , and  middle  to simulate splitting the array in halves
    while (low < high) {
        middle = (low + high) / 2;

        if (key == array[middle]) return middle;  // return index if found

        if (key < array[middle])
            high = middle;
        else
            low = middle + 1;
    }

    return -1;  // return -1 in other cases
}

}  // namespace

template <class Comparable>
int BinarySearch(const Comparable& key, Comparable* array, size_t size) {
    return BinarySearch(key, array, 0, size);
}

template <class Comparable>
int BinarySearch(const Comparable& key, const std::vector<Comparable>& array) {
    return BinarySearch(key, array, 0, array.size());
}

}  // namespace algorithm

#endif  // #ifndef ALGORITHMS_INCLUDE_BINARYSEARCH_HPP_


