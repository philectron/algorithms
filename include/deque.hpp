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

namespace datastructure {

template <class T>
class Deque {
public:
    explicit Deque(int size = 0)
        : size_{size}, capacity_{size + SPARE_CAPACITY}, start_{capacity_ / 2} {
        objects_ = new T[capacity_];
    }

    Deque(const Deque& rhs) {

    }

    Deque(Deque&& rhs) {

    }

    Deque& operator=(const Deque& rhs) {

    }

    Deque& operator=(Deque&& rhs) {

    }

    ~Deque() {
        Clear();
        delete[] objects_;
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

    }

    void Reserve(int new_capacity) {

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
