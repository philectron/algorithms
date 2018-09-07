// binary_heap.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Binary Heaps (Priority Queues) in C++
//
// This template class serves as a way to practice my data structure skills.
// This class provides methods to interact with a binary heap, including
//     - Creating the heap
//     - Making a copy of the heap
//     - Finding the highest priority (minimum) element in the heap
//     - Adding an element to the heap
//     - Removing the highest priority (minimum) element from the heap
//     - Clearing the heap

#ifndef ALGORITHMS_INCLUDE_BINARYHEAP_HPP_
#define ALGORITHMS_INCLUDE_BINARYHEAP_HPP_

#include <utility>
#include <vector>

template <class Comparable>
class BinaryHeap {
public:
    explicit BinaryHeap(int capacity = DEFAULT_CAPACITY) {}

    explicit BinaryHeap(const std::vector<Comparable>& array) {}

    BinaryHeap(const BinaryHeap& rhs) {}

    BinaryHeap& operator=(const BinaryHeap& rhs) {}

    ~BinaryHeap() {}

    int Size() const { return size_; }

    bool IsEmpty() const { return size_ == 0; }

    const Comparable& FindMin() const { return array_.at(0); }

    void Insert(const Comparable& object) {}

    void RemoveMin() {}

    void RemoveMin(Comparable& min_item) {}

    static const int DEFAULT_CAPACITY = 32;

private:
    std::vector<Comparable> array_;
    int size_;

    void BuildHeap() {}

    void PercolateDown(int hole) {}
};

#endif  // ALGORITHMS_INCLUDE_BINARYHEAP_HPP_
