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

    // Inserts a new object into the heap and maintains the order of the heap.
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

    // Removes the object with the highest priority from the heap.
    // Throws std::length_error if the heap is empty.
    void RemoveMin() {
        if (IsEmpty()) throw std::length_error("RemoveMin(): Empty heap");

        // replace the front (min object) with the back (empty hole)
        array_[0] = std::move(array_[array_.size()]);
        // percolate the hole down, choose the smaller child
        PercolateDown(0, array_.size());
        // update size
        array_.resize(array_.size() - 1);
    }

    // Removes the object with the highest priority from the heap.
    // The object will be moved to  min_item .
    void RemoveMin(Comparable& min_item) {
        if (IsEmpty()) {
            throw std::length_error(
                "RemoveMin(Comparable& min_item): Empty heap");
        }

        // place the target object to the item
        min_item = std::move(array_[0]);
        // replace the front (min object) with the back (empty hole)
        array_[0] = std::move(array_[array_.size()]);
        // percolate the hole down, choose the smaller child
        PercolateDown(0, array_.size());
        // update size
        array_.resize(array_.size() - 1);
    }

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

    // Internal method
    // Builds a binary heap from an arbitrary array.
    void BuildHeap() {}

    // Internal recursive method
    // Moves the hole in the heap downward until it's in the correct position.
    // Adjusts the heap in the range  [hole, end_index) . The smaller child will
    // become the parent.
    void PercolateDown(int hole, int end_index) {
        int left_index = hole * 2 + 1, right_index = hole * 2 + 2;

        // if there are two children
        if (0 <= right_index && right_index < end_index) {
            int min_index = MinIndex(left_index, right_index, end_index);

            // move the smaller child up to the hole
            array_[hole] = std::move(array_[min_index]);
            // percolate the empty spot of the  min_index  child
            PercolateDown(min_index, end_index);
        }
        // if there are only one child (aka left child only)
        else if (0 <= left_index && left_index < end_index) {
            // move the child up to the hole
            array_[hole] = std::move(array_[left_index]);
            // percolate the empty spot of the  left_index  child
            PercolateDown(left_index, end_index);
        }
    }

    // Returns the index in  array_  whose object is smaller the other one's.
    int MinIndex(int i, int j, int end_index) {
        // the indices should not and must not be out of range
        assert(0 <= i && i < end_index && 0 <= j && j < end_index);

        if (array_[i] < array_[j]) return i;
        return j;
    }

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
