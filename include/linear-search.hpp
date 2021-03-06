// linear-search.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Linear Search
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// Finds an element in an array by checking each element of the array within the
// interval [begin, end). Returns the index of the element if it is in the array
// or -1 if it is not.

#ifndef INCLUDE_LINEARSEARCH_HPP_
#define INCLUDE_LINEARSEARCH_HPP_

#include <cstddef>
#include <vector>

namespace dsa {

template <class Comparable>
int LinearSearch(const Comparable& key, Comparable* array, size_t size) {
    for (size_t i = 0; i < size; i++)
        if (array[i] == key) return i;

    return -1;
}

template <class Comparable>
int LinearSearch(const Comparable& key, const std::vector<Comparable>& array) {
    for (size_t i = 0, size = array.size(); i < size; i++)
        if (array[i] == key) return i;

    return -1;
}

}  // namespace dsa

#endif  // #ifndef INCLUDE_LINEARSEARCH_HPP_
