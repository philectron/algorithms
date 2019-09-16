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

#ifndef INCLUDE_DEQUE_HPP_
#define INCLUDE_DEQUE_HPP_

#include <iostream>
#include <stdexcept>
#include <utility>

namespace dsa {

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
        for (int j = 0, i = start_; j < size_; j++) {
            objects_[i] = rhs.objects_[i];
            i = (i + 1) % capacity_;
        }
    }

    Deque(Deque&& rhs)
        : size_{std::move(rhs.size_)}, capacity_{std::move(rhs.capacity_)},
          start_{std::move(rhs.start_)} {
        std::swap(objects_, rhs.objects_);
    }

    Deque& operator=(const Deque& rhs) {
        if (this != &rhs) {
            delete[] objects_;
            size_ = rhs.size_;
            capacity_ = rhs.capacity_;
            objects_ = new T[capacity_];
            start_ = rhs.start_;
            for (int j = 0, i = start_; j < size_; j++) {
                objects_[i] = rhs.objects_[i];
                i = (i + 1) % capacity_;
            }
        }
        return *this;
    }

    Deque& operator=(Deque&& rhs) {
        std::swap(objects_, rhs.objects_);
        std::swap(size_, rhs.size_);
        std::swap(capacity_, rhs.capacity_);
        std::swap(start_, rhs.start_);
        delete[] rhs.objects_;
        rhs.size_ = rhs.capacity_ = rhs.start_ = 0;
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
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // wrap around if necessary
        start_--;
        if (start_ < 0) start_ += capacity_;
        // push to new front
        objects_[start_] = object;
        size_++;
    }

    void PushFront(T&& object) {
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // wrap around if necessary
        start_--;
        if (start_ < 0) start_ += capacity_;
        // push to new front
        objects_[start_] = std::move(object); size_++;
    }

    void PushBack(const T& object) {
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // end index = index next to the back element of the deque
        objects_[GetEndIndex()] = object;
        size_++;
    }

    void PushBack(T&& object) {
        if (size_ == capacity_) Reserve(capacity_ * 2);

        // end index = index next to the back element of the deque
        objects_[GetEndIndex()] = std::move(object);
        size_++;
    }

    void PopFront() {
        if (IsEmpty()) throw std::length_error("PopFront(): Empty deque");

        start_ = (start_ + 1) % capacity_;
        size_--;
    }

    void PopBack() {
        if (IsEmpty()) throw std::length_error("PopBack(): Empty deque");

        size_--;
    }

    void Resize(int new_size) {
        if (new_size > capacity_) Reserve(new_size * 2);
        size_ = new_size;
    }

    void Reserve(int new_capacity) {
        if (new_capacity < size_) return;

        // create a temporary array that holds the new capacity
        T* new_objects = new T[new_capacity];
        int new_start = new_capacity / 2;

        // move elements over to the new array
        for (int j = 0, i = start_, k = new_start; j < size_; j++) {
            // i  wraps around the old, and  k  wraps around the new
            new_objects[k] = std::move(objects_[i]);
            k = (k + 1) % new_capacity;
            i = (i + 1) % capacity_;
        }

        // update deque's members to new
        capacity_ = new_capacity;
        start_ = new_start;

        // swap two arrays and delete the temporary one
        std::swap(objects_, new_objects);
        delete[] new_objects;
    }

    // Used for debugging purposes
    friend std::ostream& operator<<(std::ostream& out, const Deque& deque) {
        if (deque.IsEmpty()) {
            out << "Deque is empty" << std::endl;
        } else {
            int end_index = deque.GetEndIndex();

            out << "Size = " << deque.size_
                << ", Capacity = " << deque.capacity_
                << ", Start Index = " << deque.start_
                << ", End Index = " << end_index << std::endl;

            // print the deque differently based on its wrapping property
            if (deque.start_ < end_index) {
                // if the deque doesn't wrap around, then only prints
                // elements in [start, end)
                for (int i = 0; i < deque.start_; i++)
                    out << "Hole ";
                for (int i = deque.start_; i < end_index; i++) {
                    out << deque.objects_[i];
                    if (i < end_index - 1) out << ' ';
                }
                for (int i = end_index; i < deque.capacity_; i++)
                    out << " Hole";
            } else {
                // if the deque wraps around, then only prints elements
                // in [start, capacity) and in [0, end)
                for (int i = 0; i < end_index; i++) {
                    out << deque.objects_[i] << ' ';
                }
                for (int i = end_index; i < deque.start_; i++)
                    out << "Hole ";
                for (int i = deque.start_; i < deque.capacity_; i++) {
                    out << deque.objects_[i];
                    if (i < deque.capacity_ - 1) out << ' ';
                }
            }

            out << std::endl;
        }
        return out;
    }

    static const int SPARE_CAPACITY = 16;

private:
    T* objects_;
    int size_;
    int capacity_;
    int start_;

    int GetEndIndex() const { return (start_ + size_) % capacity_; }
};

}  // namespace dsa

#endif  // #ifndef INCLUDE_DEQUE_HPP_
