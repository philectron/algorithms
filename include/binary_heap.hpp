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
    // Initializes the heap by reserving  capacity  memory slots for  array_ .
    explicit BinaryHeap(int capacity = DEFAULT_CAPACITY) {
        array_.reserve(capacity);
    }

    // Builds a heap from an arbitrary array of (unordered) items.
    explicit BinaryHeap(const std::vector<Comparable>& array) {}

    BinaryHeap(const BinaryHeap& rhs) : array_{rhs.array_} {}

    BinaryHeap& operator=(const BinaryHeap& rhs) {
        if (this != &rhs) array_ = rhs.array_;
        return *this;
    }

    ~BinaryHeap() { Clear(); }

    void Clear() { array_.clear(); }

    int Size() const { return array_.size(); }

    bool IsEmpty() const { return array_.empty(); }

    // Returns the value of the object with the highest priority.
    const Comparable& FindMin() const {
        if (IsEmpty()) throw std::length_error("FindMin(): Empty heap");
        return array_.front();
    }

    std::vector<Comparable> ToVector() const {
        return std::vector<Comparable>(array_);
    }

    void Insert(const Comparable& object) {
        int hole = array_.size();

        // create a hole at the back of the array
        array_.resize(hole + 1);

        // as long as children is smaller than parent
        while (hole > 0 && object < array_[(hole - 1) / 2]) {
            // percolate the hole up (move the parent down)
            array_[hole] = std::move(array_[(hole - 1) / 2]);
            hole = (hole - 1) / 2;
        }

        array_[hole] = object;
    }

    void RemoveMin() {}

    void RemoveMin(Comparable& min_item) {}

    static const int DEFAULT_CAPACITY = 32;

    // For debugging purposes
    friend ostream& operator<<(ostream& out, const BinaryHeap& heap) {
        if (heap.IsEmpty()) {
            out << "Binary heap is empty" << endl;
        } else {
            out << "Size = " << heap.Size()
                << ", Capacity = " << heap.array_.capacity() << endl;
            // print the right nodes. The smallest index of right nodes is 2
            if (heap.Size() >= 3) heap.Print(out, heap.array_, 2, true, "");
            // print the root of the heap (index 0)
            out << heap.array_.front() << endl;
            // print the left nodes. The smallest index of left nodes is 1
            if (heap.Size() >= 2) heap.Print(out, heap.array_, 1, false, "");
        }

        return out;
    }

private:
    // children of index  i  are stored at indices
    // 2i + 1  (left child) and  2i + 2  (right child)
    // parent of index  i  (i > 0) is stored at index  (i - 1) / 2
    std::vector<Comparable> array_;  // start at 0

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
