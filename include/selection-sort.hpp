// selection-sort.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Selection Sort
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// Sorts an array by moving the smallest element of the unsorted part of
// the array to the back of the sorted part of the array (selection sort).

#ifndef INCLUDE_SELECTIONSORT_HPP_
#define ALGORIHTMS_INCLUDE_SELECTIONSORT_HPP_

#include <cstddef>
#include <utility>
#include <vector>

namespace dsa {

template <class Comparable>
void SelectionSort(Comparable* array, size_t size) {
    // current back index of the sorted part
    for (size_t i = 0; i < size - 1; i++) {
        // find the smallest element in the unsorted part
        size_t min_index = i;
        for (size_t j = i + 1; j < size; j++)
            if (array[j] < array[min_index]) min_index = j;
        // swap the smallest of unsorted with current sorted index
        if (i != min_index) std::swap(array[i], array[min_index]);
    }
}

template <class Comparable>
void SelectionSort(std::vector<Comparable>& array) {
    size_t size = array.size();

    // current back index of the sorted part
    for (size_t i = 0; i < size - 1; i++) {
        // find the smallest element in the unsorted part
        size_t min_index = i;
        for (size_t j = i + 1; j < size; j++)
            if (array[j] < array[min_index]) min_index = j;
        // swap the smallest of unsorted with current sorted index
        if (i != min_index) std::swap(array[i], array[min_index]);
    }
}

}  // namespace dsa

#endif  // #ifndef INCLUDE_SELECTIONSORT_HPP_
