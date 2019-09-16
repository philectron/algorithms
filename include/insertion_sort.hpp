// insertion_sort.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Insertion Sort in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// Sorts an array by removing an element from the unsorted part of the array and
// inserting it into the correct position in the sorted part of the array
// (insertion sort).

#ifndef INCLUDE_INSERTIONSORT_HPP_
#define INCLUDE_INSERTIONSORT_HPP_

#include <cstddef>
#include <utility>
#include <vector>

namespace dsa {

template <class Comparable>
void InsertionSort(Comparable* array, size_t size) {
    // keep track of the sorted part (the first element is already sorted)
    for (size_t i = 1; i < size; i++) {
        // put the left-most element of the unsorted part to the correct order
        size_t j = i;
        while (j > 0 && array[j] < array[j - 1]) {
            std::swap(array[j], array[j - 1]);
            j--;
        }
    }
}

template <class Comparable>
void InsertionSort(std::vector<Comparable>& array) {
    size_t size = array.size();

    // keep track of the sorted part (the first element is already sorted)
    for (size_t i = 1; i < size; i++) {
        // put the left-most element of the unsorted part to the correct order
        size_t j = i;
        while (j > 0 && array[j] < array[j - 1]) {
            std::swap(array[j], array[j - 1]);
            j--;
        }
    }
}

}  // namespace dsa

#endif  // #ifndef INCLUDE_INSERTIONSORT_HPP_
