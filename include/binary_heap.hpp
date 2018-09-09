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

#include <cassert>
#include <iostream>
#include <stdexcept>
#include <utility>
#include <vector>

using std::endl;
using std::ostream;

template <class Comparable>
class BinaryHeap {
public:
    explicit BinaryHeap(int capacity = DEFAULT_CAPACITY) : size_{0} {
        array_.reserve(capacity);
    }

    explicit BinaryHeap(const std::vector<Comparable>& array)
        : array_{array}, size_{(int)array.size()} {}

    BinaryHeap(const BinaryHeap& rhs) : array_{rhs.array_}, size_{rhs.size_} {}

    BinaryHeap& operator=(const BinaryHeap& rhs) {
        if (this != &rhs) {
            array_ = rhs.array_;
            size_ = rhs.size_;
        }
        return *this;
    }

    ~BinaryHeap() { Clear(); }

    void Clear() {
        array_.clear();
        size_ = 0;
    }

    int Size() const { return size_; }

    bool IsEmpty() const { return size_ == 0; }

    const Comparable& FindMin() const {
        if (array_.IsEmpty()) throw std::length_error("FindMin(): Empty heap");
        return array_.front();
    }

    std::vector<Comparable> ToVector() const {
        return std::vector<Comparable>(array_);
    }

    void Insert(const Comparable& object) {}

    void RemoveMin() {}

    void RemoveMin(Comparable& min_item) {}

    static const int DEFAULT_CAPACITY = 32;

    // For debugging purposes
    friend ostream& operator<<(ostream& out, const BinaryHeap& heap) {
        if (heap.IsEmpty()) {
            out << "Binary heap is empty" << endl;
        } else {
            out << "Size = " << heap.Size() << endl;
            // print the right nodes. The smallest index of right nodes is 2
            if (heap.size_ >= 3) heap.Print(out, heap.array_, 2, true, "");
            // print the root of the heap (index 0)
            out << heap.array_.front() << endl;
            // print the left nodes. The smallest index of left nodes is 1
            if (heap.size_ >= 2) heap.Print(out, heap.array_, 1, false, "");
        }

        return out;
    }

private:
    // children at index  i  are stored at indices
    // 2i + 1  (left child) and  2i + 2  (right child)
    std::vector<Comparable> array_;
    int size_;

    void BuildHeap() {}

    void PercolateDown(int hole) {}

    // Internal recursive method
    // Out streams all nodes of a heap (dynamic array form) starting from the
    // start_idx .
    // The direction of the heap is left to right (instead of up to down).
    // Credits: https://stackoverflow.com/a/19484210/4048938
    void Print(ostream& out, const std::vector<Comparable>& array,
               int start_idx, bool is_right_node, std::string indent) const {
        // start_idx  should be and must be greater than zero and less than
        // array's size
        assert(start_idx > 0 && start_idx < (int)array.size());

        // print right children first until the right most
        if (start_idx * 2 + 2 < (int)array.size()) {
            if (is_right_node) {
                Print(out, array, start_idx * 2 + 2, true, indent + "        ");
            } else {
                Print(out, array, start_idx * 2 + 2, true, indent + " |      ");
            }
        }

        out << indent;

        // different branching symbol for left and right nodes
        if (is_right_node) out << " /";
        else out << " \\";

        out << "----- " << array[start_idx] << endl;

        // print left children until the leftmost
        if (start_idx * 2 + 1 < (int)array.size()) {
            if (is_right_node) {
                Print(out, array, start_idx * 2 + 1, false,
                      indent + " |      ");
            } else {
                Print(out, array, start_idx * 2 + 1, false,
                      indent + "        ");
            }
        }
    }
};

#endif  // ALGORITHMS_INCLUDE_BINARYHEAP_HPP_
