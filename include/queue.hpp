// queue.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Queues
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// The class  Queue  provides a few simple methods to interact with a queue,
// including
// - Creating a queue
// - Cloning a queue
// - Assigning one queue to another
// - Accessing front and back of a queue
// - Inserting an element into the front and back of a queue
// - Removing an element from the front and back of a queue
// - Clearing and destroying a queue

#ifndef INCLUDE_QUEUE_HPP_
#define INCLUDE_QUEUE_HPP_

#include <iostream>
#include <stdexcept>
#include <utility>

namespace dsa {

template<class T>
class Queue {
// DOUBLY LINKED LIST NODE STRUCTURE
private:
    struct Node {
        T data;
        struct Node* prev;
        struct Node* next;

        Node(const T& node_data = T(), Node* prev_node = nullptr,
             Node* next_node = nullptr)
            : data{node_data}, prev{prev_node}, next{next_node} {}

        Node(T&& node_data, Node* prev_node = nullptr,
             Node* next_node = nullptr)
            : data{std::move(node_data)}, prev{prev_node}, next{next_node} {}
    };

// PUBLIC METHODS
public:
    Queue() { Init(); }

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

    ~Queue() {
        Clear();
        delete head_;
        delete tail_;
        size_ = 0;
    }

    int Size() const { return size_; }

    bool IsEmpty() const { return size_ == 0; }

    // Returns a reference to the data of the oldest element in the queue.
    // Throws std::out_of_range exception if the queue is empty.
    T& Front() {
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
    void Dequeue() {
        if (IsEmpty()) throw std::out_of_range(
                      "Queue::Dequeue(): Queue is empty.");

        Node* front_node = head_->next;

        head_->next = front_node->next;  // head -> second-to-front node
        front_node->next->prev = head_;  // head <- second-to-front node
        delete front_node;
        size_--;
    }

    // Clears all nodes in the queue except the two sentinel nodes.
    void Clear() { while (!IsEmpty()) Dequeue(); }

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
    void Init() {
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

}  // namespace dsa

#endif  // #ifndef INCLUDE_QUEUE_HPP_
