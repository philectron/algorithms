// vector.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Vectors in C++
//
// This template class mimics the Standard Template Library std::vector of C++
// and serves as a way to practice my data structure skills. This class provides
// methods to interact with a vector, including
//     - Creating a vector
//     - Making a copy of a vector
//     - Getting the size of a vector
//     - Accessing an element in a vector
//     - Adding an element to a vector
//     - Removing an element from a vector

#ifndef ALGORITHMS_INCLUDE_VECTOR_HPP_
#define ALGORITHMS_INCLUDE_VECTOR_HPP_

template <class T>
class Vector {


private:
    T* objects_;
    int size_;
    int capacity_;
};

#endif  // ALGORITHMS_INCLUDE_VECTOR_HPP_
