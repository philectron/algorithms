// bubble-sort.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Bubble Sort
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// Sorts an array by moving larger values to the right and smaller values to
// the left (bubble sort).

#ifndef INCLUDE_BUBBLESORT_HPP_
#define INCLUDE_BUBBLESORT_HPP_

#include <cstddef>
#include <utility>
#include <vector>

namespace dsa {

template <class Comparable>
void BubbleSort(Comparable* array, size_t size) {
    // flag to check swapped elements
    bool swapped;

    // keep sorting while there are unorder pairs
    do {
        // assume there is no out-of-order pairs
        swapped = false;

        // for each adjacent pair, swap the out-of-order ones (if any)
        for (size_t i = 0; i < size - 1; i++) {
            if (array[i] > array[i + 1]) {
                std::swap(array[i], array[i + 1]);
                swapped = true;
            }
        }

        // the largest value is at the right most of the array
        // so decrease the size of the array that needs to be sorted
        size--;
    } while (swapped);
}

template<class Comparable>
void BubbleSort(std::vector<Comparable>& array) {
    // flag to check swapped elements
    bool swapped;
    size_t size = array.size();

    // keep sorting while there are unorder pairs
    do {
        // assume there is no out-of-order pairs
        swapped = false;

        // for each adjacent pair, swap the out-of-order ones (if any)
        for (size_t i = 0; i < size - 1; i++) {
            if (array[i] > array[i + 1]) {
                std::swap(array[i], array[i + 1]);
                swapped = true;
            }
        }

        // the largest value is at the right most of the array
        // so decrease the size of the array that needs to be sorted
        size--;
    } while (swapped);
}

}  // namespace dsa

#endif  // #ifndef INCLUDE_BUBBLESORT_HPP_
