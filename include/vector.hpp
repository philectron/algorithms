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
public:
    explicit Vector(int size = 0) {
        size_ = size;
        capacity_ = size + SPARE_CAPACITY;
        objects_ = new T[capacity_];
    }

    Vector(const Vector& rhs) {

    }

    Vector& operator=(const Vector& rhs) {

    }

    ~Vector() {

    }

    int Size() { return size_; }

    bool IsEmpty() { return size_ == 0; }

    T& operator[](int index) {

    }

    T& At(int index) {

    }

    T Front() { return objects_[0]; }
    T Back() { return objects_[size_ - 1]; }

    void PushBack() {

    }

    void PopBack() {

    }

    void Clear() {

    }

    void InsertAt(int index) {

    }

    void RemoveAt(int index) {

    }

    void Sort(bool ascending = true) {

    }

    void Find(T value) {

    }

    void Resize(int new_size) {

    }

    void Reserve(int new_capacity) {

    }

    static const int SPARE_CAPACITY = 16;

private:
    T* objects_;
    int size_;
    int capacity_;
};

#endif  // ALGORITHMS_INCLUDE_VECTOR_HPP_
