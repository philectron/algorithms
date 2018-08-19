// skip_list.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Skip Lists in C++
//
// This template class is an implementation of the skip list abstract data type
// and serves as a way to practice my data structure skills.
// This class provides methods to interact with a skip list, including
//     - Creating the list
//     - Making a copy of the list
//     - Getting the size of the list
//     - Accessing an element in the list
//     - Adding an element to the list
//     - Removing an element from the list
//     - Finding an element in the list

#ifndef ALGORITHMS_INCLUDE_SKIPLIST_HPP_
#define ALGORITHMS_INCLUDE_SKIPLIST_HPP_

#include <cassert>
#include <iostream>
#include <stdexcept>
#include <random>
#include <utility>

using std::endl;
using std::ostream;

template <class T>
class SkipList {
public:
    SkipList() : size_{0}, flipcoin_{std::uniform_int_distribution<>(0, 1)} {
        top_head_ = new Node;
        rng_.seed(rd_());
    }

    SkipList(const SkipList& rhs) {

    }

    SkipList(SkipList&& rhs) {

    }

    SkipList& operator=(const SkipList& rhs) {

    }

    SkipList& operator=(SkipList&& rhs) {

    }

    ~SkipList() {

    }

    int Size() const { return size_; }

    bool IsEmpty() const { return size_ == 0; }

    // Inserts a new object to the skip list in the right order.
    void Insert(const T& object) {
        // from the top largest, recursively add new Nodes to existing rows
        Node* down_node = InsertAfter(SlideRight(top_head_, object), object);

        // if the new Node makes it to the top row and coin flip gives head
        if (down_node && CoinFlipGivesHead()) {
            // add a new Node on the new top row
            Node* new_node = new Node(object, nullptr, down_node);
            // add a new top head on the new top row
            top_head_ = new Node({}, new_node, top_head_);
        }

        // update skip list's size
        size_++;
    }

    bool Contains(const T& object) const {

    }

    void Remove(const T& object) {

    }

    void Clear() {

    }

    // For debugging purposes
    friend ostream& operator<<(const ostream& out, const SkipList& skiplist) {

    }

private:
    struct Node {
        T data;
        struct Node* next;
        struct Node* down;

        Node(const T& data = T {}, Node* next = nullptr, Node* down = nullptr)
            : data{data}, next{next}, down{down} {}
    };

    // Skip list's members
    Node* top_head_;
    int size_;

    // Use Mersenne Twister RNG to flip coins, used when inserting new nodes
    std::mt19937 rng_;
    std::random_device rd_;
    std::uniform_int_distribution<int> flipcoin_;

    // Private helpers

    // If coin flip gives head (1), returns true.
    // If coin flip gives tail (0), returns false.
    bool CoinFlipGivesHead() { return flipcoin_(rng_); }

    // Traverses to the right until reaching nullptr or the input value.
    //
    // current  starting node and must not (and should not) be a nullptr
    // val      the input value which the returned Node* should be smaller than
    //
    // Returns a Node* that is either
    // - right before the nullptr.
    // - OR having the largest  data  that is smaller than  val .
    //
    // For example: sentinel_head -> 1 -> 2 -> 3 -> 4 -> nullptr
    // - SlideRight(sentinel_head, 4) will return pointer to node with value 3.
    // - SlideRight(sentinel_head, 5) will return pointer to node with value 4.
    Node* SlideRight(Node* current, const T& val) {
        // current  should not and must not be a nullptr
        assert(current);

        while (current->next && current->next->data < val) {
            current = current->next;
        }
        return current;
    }

    // Inserts a new Node (or new Nodes) in existing lists of the skip list.
    //
    // current  the largest node of its row that is smaller than  val
    // val      the value of the new Node (or new Nodes)
    //
    // A new Node will certainly be inserted in the right order on the bottom
    // row of the skip list. After that, whether this new Node makes it to
    // the higher rows depends on the coin flips.
    //
    // Returns
    // - pointer to the new Node if it makes to the highest row of the skip list
    // - nullptr if the new Node doesn't make it to the highest row 
    Node* InsertAfter(Node* current, const T& val) {
        // current  should not and must not be a nullptr
        assert(current);

        Node* new_node = nullptr;
        Node* down_node = nullptr;

        if (!current->down) {
            // base case, if reach bottom then make a new node
            new_node = new Node(val, current->next, nullptr);
            current->next = new_node;
        } else {
            // recursive case, let the newest node be the down node
            down_node = InsertAfter(SlideRight(current->down, val), val);
            // if down node exists and the coin flip gives head
            if (down_node && CoinFlipGivesHead()) {
                // make a new node on the current level
                new_node = new Node(val, current->next, down_node);
                current->next = new_node;
            } 
        }

        return new_node;
    }
};

#endif  // ALGORITHMS_INCLUDE_SKIPLIST_HPP_
