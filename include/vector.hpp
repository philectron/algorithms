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

#include <stdexcept>
#include <utility>

template <class T>
class Vector {
public:
    explicit Vector(int size = 0) : size_{size}, capacity_{size + SPARE_CAPACITY} {
        objects_ = new T[capacity_];
    }

    Vector(const Vector& rhs) : size_{rhs.size_}, capacity_{rhs.capacity_} {
        objects_ = new T[capacity_];
        
        for (int i = 0; i < size_; i++)
            objects_[i] = rhs.objects_[i];
    }

    Vector& operator=(const Vector& rhs) {
        Vector copy = rhs;
        std::swap(*this, copy);
        return *this;
    }

    ~Vector() { delete[] objects_; }

    int Size() { return size_; }

    bool IsEmpty() { return size_ == 0; }
    
    T& operator[](int index) { return objects_[index]; }

    // Safer than [] accessor
    T& At(int index) {
        // throw an exception if the index is out of range
        if (index < 0 || index >= size_)
            throw std::out_of_range("Index out of range");
        // return otherwise
        return &objects_[index];
    }

    T Front() { return objects_[0]; }
    T Back() { return objects_[size_ - 1]; }

    void PushBack(const T& object) {
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2 + 1);
        // insert new object at the back of the vector and update size
        objects_[size_++] = object;
    }

    void PushBack(T&& object) {
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2 + 1);
        // insert new object at the back of the vector and update size
        objects_[size_++] = std::move(object);
    }

    void PopBack() { size_--; }

    void Clear() { size_ = 0; }

    // Maintains the order of elements in the vector.
    void InsertAt(int index, const T& object) {
        // ensure index is in range
        if (index < 0 || index > size_)
            throw std::out_of_range("Index is out of range");
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2 + 1);

        // shift all objects after index 1 slot to the right
        for (int i = size_; i > index; i--)
            objects_[i] = objects_[i - 1];

        // insert new object
        objects_[index] = object;
        size_++;
    }

    void InsertAt(int index, T&& object) {
        // ensure index is in range
        if (index < 0 || index > size_)
            throw std::out_of_range("Index is out of range");
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2 + 1);

        // shift all objects after index 1 slot to the right
        for (int i = size_; i > index; i--)
            objects_[i] = objects_[i - 1];

        // insert new object
        objects_[index] = std::move(object);
        size_++;
    }

    // Maintains the order of elements in the vector.
    void RemoveAt(int index) {
        // ensure index is in range
        if (index < 0 || index >= size_)
            throw std::out_of_range("Index is out of range");
    
        for (int i = index; i < size_ - 1; i ++)
            objects_[i] = objects_[i + 1];

        size_--;
    }

    void Resize(int new_size) {
        if (new_size > capacity_) Reserve(new_size * 2);
        size_ = new_size;
    }

    void Reserve(int new_capacity) {
        if (new_capacity < size_) return;

        // create a temporary, new vector with new capacity
        T* new_objects = new T[new_capacity];
        // move all object values from this vector to the new vector
        for (int i = 0; i < size_; i++) new_objects[i] = std::move(objects_[i]);
        // set new capacity
        capacity_ = new_capacity;
        // swap two vectors and delete the temporary one
        std::swap(objects_, new_objects);
        delete[] new_objects;
    }

    typedef T* Iterator;
    typedef const T* ConstIterator;

    static const int SPARE_CAPACITY = 16;

private:
    T* objects_;
    int size_;
    int capacity_;
};

#endif  // ALGORITHMS_INCLUDE_VECTOR_HPP_