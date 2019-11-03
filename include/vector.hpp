// vector.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Vectors
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// The class  Vector  provides a few simple methods to interact with a vector,
// including
// - Creating a vector
// - Cloning a vector
// - Assigning one vector to another
// - Accessing elements at the front and back of a vector
// - Accessing the element at an arbitrary index in a vector
// - Inserting an element into the front and back of a vector
// - Inserting an element into an arbitrary index of a vector
// - Removing an element from the front and back of a vector
// - Removing an element from an arbitrary index of a vector
// - Clearing and destroying a vector

#ifndef INCLUDE_VECTOR_HPP_
#define INCLUDE_VECTOR_HPP_

#include <iostream>
#include <stdexcept>
#include <utility>

namespace dsa {

template <class T>
class Vector {
public:
    explicit Vector(int size = 0)
            : size_{size}, capacity_{size + SPARE_CAPACITY} {
        objects_ = new T[capacity_];
    }

    Vector(const Vector& rhs)
            : objects_{nullptr}, size_{rhs.size_}, capacity_{rhs.capacity_} {
        objects_ = new T[capacity_];
        for (int i = 0; i < size_; i++) objects_[i] = rhs.objects_[i];
    }

    Vector(Vector&& rhs)
            : objects_{rhs.objects_},
            size_{rhs.size_},
            capacity_{rhs.capacity_} {
        rhs.objects_ = nullptr;
        rhs.size_ = rhs.capacity_ = 0;
    }

    Vector& operator=(const Vector& rhs) {
        Vector copy = rhs;

        std::swap(*this, copy);
        return *this;
    }

    Vector& operator=(Vector && rhs) {
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
    T& At(int index) const {
        // ensure index is in range
        if (index < 0 || index >= size_)
            throw std::out_of_range("At(): Index out of range");

        return &objects_[index];
    }

    T Front() const {
        if (IsEmpty()) throw std::length_error("Front(): Empty vector");

        return objects_[0];
    }

    T Back() const {
        if (IsEmpty()) throw std::length_error("Back(): Empty vector");

        return objects_[size_ - 1];
    }

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
        if (IsEmpty()) {
            throw std::length_error("PopBack(): Empty vector");
        }

        size_--;
    }

    void Clear() { size_ = 0; }

    // Maintains the order of elements in the vector.
    void InsertAt(int index, const T& object) {
        // ensure index is in range
        if (index < 0 || index > size_) {
            throw std::out_of_range("InsertAt(): Index out of range");
        }
        // if vector is full, increase the capacity
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // shift all objects after index 1 slot to the right
        for (int i = size_; i > index; i--) objects_[i] = objects_[i - 1];

        // insert new object
        objects_[index] = object;
        size_++;
    }

    void InsertAt(int index, T&& object) {
        // ensure index is in range
        if (index < 0 || index > size_) {
            throw std::out_of_range("InsertAt(&&): Index out of range");
        }

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
        if (index < 0 || index >= size_) {
            throw std::out_of_range("RemoveAt(): Index out of range");
        }

        for (int i = index; i < size_ - 1; i++) objects_[i] = objects_[i + 1];

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

    Iterator Begin() { return &objects_[0]; }

    ConstIterator Begin() const { return &objects_[0]; }

    Iterator End() { return &objects_[size_]; }

    ConstIterator End() const { return &objects_[size_]; }

    // Used for debugging purposes
    friend std::ostream& operator<<(std::ostream& out, const Vector& vector) {
        if (vector.IsEmpty()) {
            out << "Vector is empty" << std::endl;
        } else {
            for (int i = 0; i < vector.size_; i++) {
                out << vector[i];
                if (i < vector.size_ - 1) out << ' ';
            }
            out << std::endl << "Size = " << vector.size_;
            out << std::endl << "Capacity = " << vector.capacity_ << std::endl;
        }

        return out;
    }

    static const int SPARE_CAPACITY = 16;

private:
    T* objects_;
    int size_;
    int capacity_;
};

}  // namespace dsa

#endif  // #ifndef INCLUDE_VECTOR_HPP_
