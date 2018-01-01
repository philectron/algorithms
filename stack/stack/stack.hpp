// File: stack.hpp
// Author: Phi Luu
// Created: December 19, 2017
//
// Data Structures: Stacks in C++
//
// This template class mimics the Standard Template Library std::stack of C++
// and serves as a way to practice my data structure skills.
// This class provides methods to interact with a stack, including
//     - Creating the stack
//     - Making a copy of the stack
//     - Getting the size of the stack
//     - Accessing the top element of the stack
//     - Adding an element to the top of the stack
//     - Removing the top element from the stack
//     - Clearing the stack

#ifndef STACK_HPP_
#define STACK_HPP_

#include <iostream>
#include <stdexcept>

template<class T>
class Stack {
// SINGLY LINKED LIST NODE STRUCTURE
private:
    struct Node {
        T data;
        struct Node* next;

        Node(const T& data = T {}, Node* next = nullptr) {
            this->data = data;
            this->next = next;
        }
    };

// PUBLIC METHODS
public:
    Stack(void) { Init(); }

    // Make a deep copy of the right-hand side stack and constructs and new
    // stack.
    Stack(const Stack& rhs) {
        // first init an empty stack
        Init();

        // keep track of the back of this stack
        Node* back_node = head_;

        // traverse through the right-hand side stack
        for (Node* trav = rhs.head_->next; trav != nullptr; trav = trav->next) {
            back_node = PushBack(back_node, trav->data);
        }
    }

    // Make a deep copy of the right-hand side stack and assigns to this stack.
    Stack& operator=(const Stack& rhs) {
        // prevent self-assignment which leads to self-deletion
        if (this == &rhs) return *this;

        // first clear this stack
        Clear();

        // keep track of the back of this stack
        Node* back_node = head_;

        // traverse through the right-hand side stack
        for (Node* trav = rhs.head_->next; trav != nullptr; trav = trav->next) {
            back_node = PushBack(back_node, trav->data);
        }

        return *this;
    }

    ~Stack(void) {
        Clear();
        delete head_;
        size_ = 0;
    }

    int Size(void) const { return size_; }

    bool IsEmpty(void) const { return size_ == 0; }

    // Returns a reference to the data of the top element in the stack.
    // Throws std::out_of_range exception if the stack is empty.
    T& Top(void) {
        if (IsEmpty()) throw std::out_of_range("Stack::Top(): Stack is empty.");
        return head_->next->data;
    }

    // Adds an element to the top of the stack.
    void Push(const T& value) {
        Node* new_node = new Node(value, head_->next);

        head_->next = new_node;  // sentinel head -> new node
        size_++;
    }

    // Removes the top element from the stack.
    // Throws std::out_of_range exception if the stack is empty.
    void Pop(void) {
        if (IsEmpty()) throw std::out_of_range("Stack::Pop(): Stack is empty.");

        Node* top_node = head_->next;
        head_->next = top_node->next;  // head -> second-to-top node
        delete top_node;
        size_--;
    }

    // Deallocates all nodes except the sentinel head.
    void Clear(void) { while (!IsEmpty()) Pop(); }

    // Prints under the following format:
    // Node1Data -> Node2Data -> Node3Data -> ... -> NodeNData
    // Size = size_
    void Print(void) const {
        if (IsEmpty()) {
            std::cout << "The stack is empty." << std::endl;
            return;
        }

        // traverse through the stack
        for (Node* trav = head_->next; trav != nullptr; trav = trav->next) {
            std::cout << trav->data;

            // print the separator if the current node is not the last
            if (trav->next != nullptr) {
                std::cout << " -> ";
            }
        }

        std::cout << "\nSize = " << size_ << std::endl;
    }

// PRIVATE HELPER METHODS
private:
    // Initializes an empty stack with a sentinel head.
    void Init(void) {
        head_ = new Node;
        size_ = 0;
    }

    // Adds an element to the back of the stack.
    Node* PushBack(Node* back_node, const T& value) {
        // allocate a new node and set its members
        Node* new_node = new Node(value, nullptr);

        // the new node comes after the back node of this stack
        back_node->next = new_node;
        // the new node becomes the new back of this stack
        back_node = new_node;
        // increase the size of this stack by one
        size_++;

        return back_node;
    }

// PRIVATE DATA MEMBERS
private:
    Node* head_;
    int size_;
};

#endif  // STACK_HPP_
