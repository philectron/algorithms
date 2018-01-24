// File: queue.hpp
// Author: Phi Luu
// Created: December 20, 2017
//
// Data Structures: Queues in C++
//
// This template class mimics the Standard Template Library std::queue of C++
// and serves as a way to practice my data structure skills.
// This class provides methods to interact with a queue, including
//     - Creating the queue
//     - Making a copy of the queue
//     - Getting the size of the queue
//     - Accessing the top element of the queue
//     - Adding an element to the top of the queue
//     - Removing the top element from the queue
//     - Clearing the queue

#ifndef ALGORITHMS_INCLUDE_QUEUE_HPP_
#define ALGORITHMS_INCLUDE_QUEUE_HPP_

#include <iostream>
#include <stdexcept>

template<class T>
class Queue {
// DOUBLY LINKED LIST NODE STRUCTURE
private:
    struct Node {
        T data;
        struct Node* prev;
        struct Node* next;

        Node(const T& data = T {}, Node* prev = nullptr, Node* next = nullptr) {
            this->data = data;
            this->prev = prev;
            this->next = next;
        }
    };

// PUBLIC METHODS
public:
    Queue(void) { Init(); }

    // Makes a deep copy of the right-hand side queue and constructs a new one.
    Queue(const Queue& rhs) {
        // first init this queue empty
        Init();

        // traverse through the right-hand side queue
        for (Node* trav = rhs.head_->next;
             trav != rhs.tail_;
             trav = trav->next) {
            Enqueue(trav->data);
        }
    }

    // Makes a deep copy of the right-hand side queue and assigns to this queue.
    Queue& operator=(const Queue& rhs) {
        // prevent self-assignment which leads to self-deletion
        if (this == &rhs) return *this;

        // first clear this queue
        Clear();

        // traverse through the right-hand side queue
        for (Node* trav = rhs.head_->next;
             trav != rhs.tail_;
             trav = trav->next) {
            Enqueue(trav->data);
        }

        return *this;
    }

    ~Queue(void) {
        Clear();
        delete head_;
        delete tail_;
        size_ = 0;
    }

    int Size(void) const { return size_; }

    bool IsEmpty(void) const { return size_ == 0; }

    // Returns a reference to the data of the oldest element in the queue.
    // Throws std::out_of_range exception if the queue is empty.
    T& Front(void) {
        if (IsEmpty()) throw std::out_of_range("Queue::Front(): Queue is empty.");
        return head_->next->data;
    }

    // Adds an element to the back of the queue.
    void Enqueue(const T& value) {
        Node* new_node = new Node(value, tail_->prev, tail_);

        tail_->prev->next = new_node;  // node before tail -> new node
        tail_->prev = new_node;  // new node <- tail
        size_++;
    }

    // Removes the oldest element from the queue.
    // Throws std::out_of_range exception if the queue is empty.
    void Dequeue(void) {
        if (IsEmpty()) throw std::out_of_range(
                      "Queue::Dequeue(): Queue is empty.");

        Node* front_node = head_->next;

        head_->next = front_node->next;  // head -> second-to-front node
        front_node->next->prev = head_;  // head <- second-to-front node
        delete front_node;
        size_--;
    }

    // Clears all nodes in the queue except the two sentinel nodes.
    void Clear(void) { while (!IsEmpty()) Dequeue(); }

    // Prints under the following format:
    // Node1Data <-> Node2Data <-> Node3Data <-> ... <-> NodeNData
    friend std::ostream& operator<<(std::ostream& out, const Queue& obj) {
        if (obj.IsEmpty()) {
            out << "The queue is empty." << std::endl;
        } else {
            // traverse through the queue
            for (Queue::Node* trav = obj.head_->next;
                 trav != obj.tail_;
                 trav = trav->next) {
                out << trav->data;

                // print the separator if the current node is not the last
                if (trav->next != obj.tail_) out << " <-> ";
            }

            out << "\nSize = " << obj.size_ << std::endl;
        }

        return out;
    }

// PRIVATE HELPER METHODS
private:
    // Initializes an empty queue with two sentinel nodes.
    void Init(void) {
        head_ = new Node;
        tail_ = new Node;
        size_ = 0;
        head_->next = tail_;
        tail_->prev = head_;
    }

// PRIVATE DATA MEMEBERS
private:
    Node* head_;
    Node* tail_;
    int size_;
};

#endif  // ALGORITHMS_INCLUDE_QUEUE_HPP_
