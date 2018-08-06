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

#include <iostream>
#include <stdexcept>
#include <utility>

using std::endl;
using std::ostream;

template <class T>
class Vector {
public:
    explicit Vector(int size = 0) : size_{size}, capacity_{size + SPARE_CAPACITY} {
        objects_ = new T[capacity_];
    }

    Vector(const Vector& rhs) : size_{rhs.size_}, capacity_{rhs.capacity_},
                                objects_{nullptr} {
        objects_ = new T[capacity_];
        for (int i = 0; i < size_; i++) objects_[i] = rhs.objects_[i];
    }

    Vector(Vector&& rhs) : size_{rhs.size_}, capacity_{rhs.capacity_},
                           objects_{rhs.objects_} {
        rhs.objects_ = nullptr;
        rhs.size_ = rhs.capacity_ = 0;
    }

    Vector& operator=(const Vector& rhs) {
        Vector copy = rhs;
        std::swap(*this, copy);
        return *this;
    }

    Vector& opeartor=(Vector&& rhs) {
        std::swap(objects_, rhs.objects_);
        std::swap(size_, rhs.size_);
        std::swap(capacity_, rhs.capacity_);
        return *this;
    }

    ~Vector() { delete[] objects_; }

    int Size() const { return size_; }
    
    int Capacity() const { return capacity_; }

    bool IsEmpty() const { return size_ == 0; }

    T& operator[](int index) { return objects_[index]; }
    
    const T& operator[](int index) const { return objects_[index]; }

    // Safer than [] accessor
    T& At(int index) {
        // ensure index is in range 
        if (index < 0 || index >= size_)
            throw std::out_of_range("At(): Index out of range");

        return &objects_[index];
    }

    T Front() { return objects.At(0); }
    T Back() { return objects.At(size_ - 1); }

    void PushBack(const T& object) {
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // insert new object at the back of the vector and update size
        objects_[size_++] = object;
    }

    void PushBack(T&& object) {
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // insert new object at the back of the vector and update size
        objects_[size_++] = std::move(object);
    }

    void PopBack() {
        // ensure vector is not empty
        if (IsEmpty())
            throw std::length_error("PopBack(): Empty vector");

        size_--;
    }

    void Clear() { size_ = 0; }

    // Maintains the order of elements in the vector.
    void InsertAt(int index, const T& object) {
        // ensure index is in range
        if (index < 0 || index > size_)
            throw std::out_of_range("InsertAt(): Index out of range");
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // shift all objects after index 1 slot to the right
        for (int i = size_; i > index; i--) objects_[i] = objects_[i - 1];

        // insert new object
        objects_[index] = object; size_++;
    }

    void InsertAt(int index, T&& object) {
        // ensure index is in range
        if (index < 0 || index > size_)
            throw std::out_of_range("InsertAt(&&): Index out of range");
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // shift all objects after index 1 slot to the right
        for (int i = size_; i > index; i--) objects_[i] = objects_[i - 1];

        // insert new object
        objects_[index] = std::move(object);
        size_++;
    }

    // Maintains the order of elements in the vector.
    void RemoveAt(int index) {
        // ensure index is in range
        if (index < 0 || index >= size_)
            throw std::out_of_range("RemoveAt(): Index out of range");

        for (int i = index; i < size_ - 1; i ++) objects_[i] = objects_[i + 1];

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

    // Used for debugging purposes
    friend ostream& operator<<(ostream& out, const Vector& vector) {
        if (vector.IsEmpty()) {
            out << "Vector is empty" << endl;
        } else {
            for (int i = 0; i < vector.size_; i++) {
                out << vector[i];
                if (i < vector.size_ - 1) out << ' ';
            }
            out << endl << "Size = " << vector.size_;
            out << endl << "Capacity = " << vector.capacity_ << endl;
        }

        return out;
    }

    static const int SPARE_CAPACITY = 16;

private:
    T* objects_;
    int size_;
    int capacity_;
};

#endif  // ALGORITHMS_INCLUDE_VECTOR_HPP_
