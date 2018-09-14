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

namespace datastructure {

template <class Comparable>
class BinaryHeap {
public:
    // Initializes the heap by reserving  capacity  memory slots for  array_ .
    explicit BinaryHeap(int capacity = DEFAULT_CAPACITY) {
        array_.reserve(capacity);
    }

    // Builds a heap from an arbitrary array of (unordered) items.
    explicit BinaryHeap(const std::vector<Comparable>& array) : array_{array} {
        BuildHeap();
    }

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

        // replace the front (the object) with the back (lowest priority object)
        array_[0] = std::move(array_.back());
        // update size
        array_.resize(array_.size() - 1);
        // percolate the hole down, choose the smaller child
        PercolateDown(0, array_.size());
    }

    // Removes the object with the highest priority from the heap.
    // The object will be moved to  min_item .
    void RemoveMin(Comparable& min_item) {
        if (IsEmpty()) {
            throw std::length_error("RemoveMin(Comparable&): Empty heap");
        }

        // place the target object to the item
        min_item = std::move(array_[0]);
        // replace the front (min object) with the back (empty hole)
        array_[0] = std::move(array_.back());
        // update size
        array_.resize(array_.size() - 1);
        // percolate the hole down, choose the smaller child
        PercolateDown(0, array_.size());
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
    void BuildHeap() {
        // the leaves (from [size - 1] to [size / 2]) already form a valid heap
        // so percolate every object in the first half down
        for (int i = array_.size() / 2 - 1; i >= 0; i--)
            PercolateDown(i, array_.size());
    }

    // Internal method
    // Moves the hole in the heap downward until it's in the correct position.
    // Adjusts the heap in the range  [hole, end_index) . The smaller child will
    // become the parent.
    void PercolateDown(int hole, int end_index) {
        // hole  should not and must not be out of range
        assert(0 <= hole && hole < end_index);

        int child_index;
        Comparable tmp = std::move(array_[hole]);

        // as long as the object at index  hole  has at least one child
        while (hole * 2 + 1 < end_index) {
            child_index = hole * 2 + 1;  // left child. right = left + 1

            // if the right child is in range and smaller than the left child
            if (child_index + 1 < end_index
                && array_[child_index + 1] < array_[child_index]) {
                // choose the right child, otherwise keep the index unchanged
                child_index++;
            }
            // when the smaller child has been chosen, if it is smaller than the
            // parent, then the parent becomes the smaller child
            if (array_[child_index] < tmp)
                array_[hole] = std::move(array_[child_index]);
            else
                break;

            hole = child_index;
        }

        // when hole is at the correct position, bring back object to hole
        array_[hole] = std::move(tmp);
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

}  // namespace datastructure

#endif  // ALGORITHMS_INCLUDE_BINARYHEAP_HPP_
