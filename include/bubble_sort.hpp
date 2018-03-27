// bubble_sort.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Bubble Sort in C++
//
// Sorts an array of integers by moving larger values to the right and smaller
// values to the left (bubble sort).

#ifndef ALGORITHMS_INCLUDE_BUBBLESORT_HPP_
#define ALGORITHMS_INCLUDE_BUBBLESORT_HPP_

void BubbleSort(int* array, int size) {
    // flag to check swapped elements, first assuming the array is unsorted
    bool swapped = true;

    // keep sorting while there are unorder pairs
    while (swapped) {
        // assume there is no out-of-order pairs
        swapped = false;

        // for each adjacent pair, swap the out-of-order ones (if any)
        for (int i = 0; i < size - 1; i++) {
            if (array[i] > array[i + 1]) {
                // perform the swap
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                // swap performed means there is an unordered pair
                swapped = true;
            }
        }
        // the largest value is at the right most of the array
        // so decrease the size of the array that needs to be sorted
        size--;
    }
}

#endif  // ifndef ALGORITHMS_INCLUDE_BUBBLESORT_HPP_
