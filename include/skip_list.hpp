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

template <class T>
class SkipList {
private:
    struct Node {
        T data;
        struct Node* next;
        struct Node* down;

        Node(const T& data = T {}, Node* next = nullptr, Node* down = nullptr)
            : data{data}, next{next}, down{down} {}
    };

public:

};

#endif  // ALGORITHMS_INCLUDE_SKIPLIST_HPP_
