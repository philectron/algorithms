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

#include <iostream>
#include <random>
#include <utility>

using std::endl;
using std::ostream;

template <class T>
class SkipList {
public:
    SkipList() : size_{0}, flipcoin_{std::uniform_int_distribution<>(0, 1)} {
        tophead_ = new Node;
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

    void Insert(const T& object) {

    }

    void Insert(T&& object) {

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
    Node* tophead_;
    int size_;
   
    // Use Mersenne Twister RNG to flip coins, used when inserting new nodes 
    std::mt19937 rng_;
    std::random_device rd_;
    std::uniform_int_distribution<int> flipcoin_;

    // Private helpers

    // If coin flip gives head (1), return true.
    // If coin flip gives tail (0), return false.
    bool CoinFlipGivesHead() { return flipcoin_(rng_); }
};

#endif  // ALGORITHMS_INCLUDE_SKIPLIST_HPP_
