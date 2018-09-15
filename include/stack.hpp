// stack.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Stacks in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// The class  Stack  provides a few simple methods to interact with a stack,
// including
// - Creating a stack
// - Cloning a stack
// - Assigning one stack to another
// - Accessing the element on the top of a stack
// - Inserting an element into the top of a stack
// - Removing an element from the top of a stack
// - Clearing and destroying a stack


#ifndef ALGORITHMS_INCLUDE_STACK_HPP_
#define ALGORITHMS_INCLUDE_STACK_HPP_

#include <iostream>
#include <stdexcept>
#include <utility>

namespace datastructure {

template<class T>
class Stack {
// SINGLY LINKED LIST NODE STRUCTURE
private:
    struct Node {
        T data;
        struct Node* next;

        Node(const T& node_data = T(), Node* next_node = nullptr)
            : data{node_data}, next{next_node} {}

        Node(T&& node_data, Node* next_node = nullptr)
            : data{std::move(node_data)}, next{next_node} {}
    };

// PUBLIC METHODS
public:
    Stack() { Init(); }

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

    ~Stack() {
        Clear();
        delete head_;
        size_ = 0;
    }

    int Size() const { return size_; }

    bool IsEmpty() const { return size_ == 0; }

    // Returns a reference to the data of the top element in the stack.
    // Throws std::out_of_range exception if the stack is empty.
    T& Top() {
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
    void Pop() {
        if (IsEmpty()) throw std::out_of_range("Stack::Pop(): Stack is empty.");

        Node* top_node = head_->next;
        head_->next = top_node->next;  // head -> second-to-top node
        delete top_node;
        size_--;
    }

    // Deallocates all nodes except the sentinel head.
    void Clear() { while (!IsEmpty()) Pop(); }

    // Prints under the following format:
    // Node1Data -> Node2Data -> Node3Data -> ... -> NodeNData
    // Size = size_
    friend std::ostream& operator<<(std::ostream& out, const Stack& obj) {
        if (obj.IsEmpty()) {
            out << "The stack is empty." << std::endl;
        } else {
            // traverse through the stack
            for (Stack::Node* trav = obj.head_->next;
                 trav != nullptr;
                 trav = trav->next) {
                out << trav->data;

                // print the separator if the current node is not the last
                if (trav->next != nullptr) out << " -> ";
            }
            out << "\nSize = " << obj.size_ << std::endl;
        }

        return out;
    }

// PRIVATE HELPER METHODS
private:
    // Initializes an empty stack with a sentinel head.
    void Init() {
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

}  // namespace datastructure

#endif  // #ifndef ALGORITHMS_INCLUDE_STACK_HPP_
