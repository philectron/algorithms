// merge_sort.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Merge Sort in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// Sorts an array by dividing it into two halves and sorting each half, then
// merging two halves back into a sorted array (merge sort).

#ifndef INCLUDE_MERGESORT_HPP_
#define INCLUDE_MERGESORT_HPP_

#include <cstddef>
#include <utility>
#include <vector>

namespace dsa {

// Internal helpers

namespace {

template <class Comparable>
void Merge(Comparable* array, size_t begin, size_t middle, size_t end) {
    Comparable merged[end - begin];  // temporary array holding merged halves
    size_t i = begin, j = middle, k = 0;

    // put the smallest of the current elements of two halves first
    while (i < middle && j < end) {
        if (array[i] < array[j])
            merged[k++] = array[i++];
        else
            merged[k++] = array[j++];
    }

    // if there are any remaining elements, put them all in the merged array
    while (i < middle) merged[k++] = array[i++];
    while (j < end) merged[k++] = array[j++];

    // move all merged elements to the real array
    for (size_t t = 0; t < k; t++) array[t + begin] = std::move(merged[t]);
}

template <class Comparable>
void MergeSort(Comparable* array, size_t begin, size_t end) {
    // array is already sorted if it has 1 element or 0 elements
    if (end - begin < 2) return;

    // sort left and right halves, then merge them back
    size_t middle = (begin + end) / 2;
    MergeSort(array, begin, middle);
    MergeSort(array, middle, end);
    Merge(array, begin, middle, end);
}

template <class Comparable>
void Merge(std::vector<Comparable>& array, size_t begin, size_t middle,
           size_t end) {
    // temporary array holding merged halves
    std::vector<Comparable> merged(end - begin);
    size_t i = begin, j = middle, k = 0;

    // put the smallest of the current elements of two halves first
    while (i < middle && j < end) {
        if (array[i] < array[j])
            merged[k++] = array[i++];
        else
            merged[k++] = array[j++];
    }

    // if there are any remaining elements, put them all in the merged array
    while (i < middle) merged[k++] = array[i++];
    while (j < end) merged[k++] = array[j++];

    // move all merged elements to the real array
    for (size_t t = 0; t < k; t++) array[t + begin] = std::move(merged[t]);
}

template <class Comparable>
void MergeSort(std::vector<Comparable>& array, size_t begin, size_t end) {
    // array is already sorted if it has 1 element or 0 elements
    if (end - begin < 2) return;

    // sort left and right halves, then merge them back
    size_t middle = (begin + end) / 2;
    MergeSort(array, begin, middle);
    MergeSort(array, middle, end);
    Merge(array, begin, middle, end);
}

}  // namespace

// Interface

template <class Comparable>
void MergeSort(Comparable* array, size_t size) {
    MergeSort(array, 0, size);
}

template <class Comparable>
void MergeSort(std::vector<Comparable>& array) {
    MergeSort(array, 0, array.size());
}

}  // namespace dsa

#endif  // #ifndef INCLUDE_MERGESORT_HPP_
