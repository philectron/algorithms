// quick_sort.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Quick Sort in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// Sorts an array by repeatedly picking a pivot and moving smaller elements to
// the left and larger elements to the right of the pivot until the array is
// sorted (quick sort).

#ifndef INCLUDE_QUICKSORT_HPP_
#define INCLUDE_QUICKSORT_HPP_

#include <cstddef>
#include <random>
#include <utility>
#include <vector>

namespace dsa {

// Internal helpers

namespace {

std::mt19937 rng;
std::random_device rd;

// range for all indices in the array is [begin, end)

template <class Comparable>
size_t Partition(Comparable* array, size_t begin, size_t end) {
    // choose the back element as the pivot
    size_t pivot_pos = end - 1;
    const Comparable& pivot = array[pivot_pos];

    size_t i = begin;  // elements in [begin, i) will be smaller than the pivot

    // loop through the array and push elements that are smaller than the pivot
    // to index i and elements that are larger than the pivot to the right
    for (size_t j = begin; j < pivot_pos; j++)
        if (array[j] < pivot) std::swap(array[i++], array[j]);

    // the current  i  is the correct position for the pivot
    std::swap(array[i], array[pivot_pos]);

    // return the correct position of the pivot
    return i;
}

template <class Comparable>
void QuickSort(Comparable* array, size_t begin, size_t end) {
    // stop if subarray has less than 2 elements
    if (end - begin < 2) return;

    // choose a random index for the pivot
    rng.seed(rd());
    std::uniform_int_distribution<size_t> rand_pivot(begin, end - 1);
    size_t pivot_pos_rand = rand_pivot(rng);

    // swap the pivot with the element at [end - 1]
    std::swap(array[pivot_pos_rand], array[end - 1]);

    // use the pivot to partition the subarray
    size_t pivot_pos = Partition(array, begin, end);

    QuickSort(array, begin, pivot_pos);
    QuickSort(array, pivot_pos + 1, end);
}

template <class Comparable>
size_t Partition(std::vector<Comparable>& array, size_t begin, size_t end) {
    // choose the back element as the pivot
    size_t pivot_pos = end - 1;
    const Comparable& pivot = array[pivot_pos];

    size_t i = begin;  // elements in [begin, i) will be smaller than the pivot

    // loop through the array and push elements that are smaller than the pivot
    // to index i and elements that are larger than the pivot to the right
    for (size_t j = begin; j < pivot_pos; j++)
        if (array[j] < pivot) std::swap(array[i++], array[j]);

    // the current  i  is the correct position for the pivot
    std::swap(array[i], array[pivot_pos]);

    // return the correct position of the pivot
    return i;
}

template <class Comparable>
void QuickSort(std::vector<Comparable>& array, size_t begin, size_t end) {
    // stop if subarray has less than 2 elements
    if (end - begin < 2) return;

    // choose a random pivot
    rng.seed(rd());
    std::uniform_int_distribution<size_t> rand_pivot(begin, end - 1);
    size_t pivot_pos_rand = rand_pivot(rng);

    // swap the pivot with the element at [end - 1]
    std::swap(array[pivot_pos_rand], array[end - 1]);

    // use the pivot to partition the subarray
    size_t pivot_pos = Partition(array, begin, end);

    QuickSort(array, begin, pivot_pos);
    QuickSort(array, pivot_pos + 1, end);
}

}  // namespace

// Interface

template <class Comparable>
void QuickSort(Comparable* array, size_t size) {
    QuickSort(array, 0, size);
}

template <class Comparable>
void QuickSort(std::vector<Comparable>& array) {
    QuickSort(array, 0, array.size());
}

}  // namespace dsa

#endif  // #ifndef INCLUDE_QUICKSORT_HPP_
