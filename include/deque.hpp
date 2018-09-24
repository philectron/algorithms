// deque.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Deques (Double-Ended Queues) in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// The class  Deque  provides a few simple methods to interact with a deque,
// including
// - Creating a deque
// - Cloning a deque
// - Assigning one deque to another
// - Accessing elements from the the front and back of a deque
// - Inserting elements into the front and back of a deque
// - Removing elements from the front and back of a deque
// - Clearing and destroying a deque

#ifndef ALGORITHMS_INCLUDE_DEQUE_HPP_
#define ALGORITHMS_INCLUDE_DEQUE_HPP_

#include <iostream>
#include <stdexcept>
#include <utility>

namespace datastructure {

template <class T>
class Deque {
public:
    explicit Deque(int size = 0)
        : size_{size}, capacity_{size + SPARE_CAPACITY}, start_{capacity_ / 2} {
        objects_ = new T[capacity_];
    }

    Deque(const Deque& rhs)
        : size_{rhs.size_}, capacity_{rhs.capacity_}, start_{rhs.start_} {
        objects_ = new T[capacity_];
        for (int i = 0; i < size_; i++) objects_[i] = rhs.objects_[i];
    }

    Deque(Deque&& rhs)
        : size_{std::move(rhs.size_)}, capacity_{std::move(rhs.capacity_)},
          start_{std::move(rhs.start_)} {
        std::swap(objects_, rhs.objects_);
    }

    Deque& operator=(const Deque& rhs) {
        if (this != &rhs) {
            size_ = rhs.size_;
            capacity_ = rhs.capacity_;
            start_ = rhs.start_;
            objects_ = new T[capacity_];
            for (int i = 0; i < size_; i++) objects_[i] = rhs.objects_[i];
        }
        return *this;
    }

    Deque& operator=(Deque&& rhs) {
        std::swap(objects_, rhs.objects_);
        std::swap(size_, rhs.size_);
        std::swap(capacity_, rhs.capacity_);
        std::swap(start_, rhs.start_);
        delete[] rhs.objects_;
    }

    ~Deque() {
        Clear();
        delete[] objects_;
        capacity_ = start_ = 0;
    }

    void Clear() {
        size_ = 0;
        start_ = capacity_ / 2;
    }

    int Size() const { return size_; }

    int Capacity() const { return capacity_; }

    bool IsEmpty() const { return size_ == 0; }

    T Front() const {
        if (IsEmpty()) throw std::length_error("Front(): Empty deque");

        return objects_[start_];
    }

    T Back() const {
        if (IsEmpty()) throw std::length_error("Back(): Empty deque");

        return objects_[(start_ + size_ - 1) % capacity_];
    }

    void PushFront(const T& object) {

    }

    void PushFront(T&& object) {

    }

    void PushBack(const T& object) {

    }

    void PushBack(T&& object) {

    }

    void PopFront() {
        if (IsEmpty()) throw std::length_error("PopFront(): Empty deque");
    }

    void PopBack() {
        if (IsEmpty()) throw std::length_error("PopBack(): Empty deque");
    }

    void Resize(int new_size) {
        if (new_size > capacity_) Reserve(new_size * 2);
        size_ = new_size;
    }

    void Reserve(int new_capacity) {
        if (new_capacity < size_) return;

        // create a temporary array that holds the new capacity
        T* new_objects = new T[new_capacity];
        // move elements over to the new array
        for (int i = 0; i < size_; i++) new_objects[i] = std::move(objects_[i]);
        // swap two arrays and delete the temporary one
        std::swap(objects_, new_objects);
        delete[] new_objects;
    }

    // Used for debugging purposes
    friend std::ostream& operator<<(std::ostream& out, const Deque& deque) {
        if (deque.IsEmpty()) {
            out << "Deque is empty" << std::endl;
        } else {

        }
        return out;
    }

    static const int SPARE_CAPACITY = 16;

private:
    T* objects_;
    int size_;
    int capacity_;
    int start_;
};

}  // namespace datastructure

#endif  // #ifndef ALGORITHMS_INCLUDE_DEQUE_HPP_
