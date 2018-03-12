// File: linked_list.hpp
// Author: Phi Luu
// Created: December 18, 2017
//
// Data Structures and Algorithms: Linked Lists in C++
//
// This template class mimics the Standard Template Library std::list of C++ and
// serves as a way to practice my data structure skills.
// This class provides methods to interact with a linked list, including
//     - Creating the list
//     - Making a copy of the list
//     - Getting the size of the list
//     - Accessing an element in the list
//     - Adding an element to the list
//     - Removing an element from the list

#ifndef ALGORITHMS_INCLUDE_LINKEDLIST_HPP_
#define ALGORITHMS_INCLUDE_LINKEDLIST_HPP_

#include <iostream>

template<class T>
class LinkedList {
// USE DOUBLY LINKED LIST NODE STRUCTURE
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

// A NOTION OF POSITION IN THE LIST
public:
    class ConstIterator {
    public:
        ConstIterator(void) { current_ = nullptr; }

        // *iterator returns a constant reference to the data of the current
        // Node member of this object.
        const T& operator*(void) const { return Retrieve(); }

        // ++iterator sets the current Node to the next Node, then returns the
        // updated iterator.
        ConstIterator& operator++(void) {
            current_ = current_->next;
            return *this;
        }

        // iterator++ sets the current Node to the next Node, then returns the
        // old iterator.
        ConstIterator operator++(int) {
            ConstIterator old = *this;

            ++*this;  // use the prefix ++ overloaded above
            return old;
        }

        // iterator + n sets the current Node to the next Node that is n steps
        // away, then returns the updated iterator.
        //
        // WARNING: This method will cause segfault if it tries to access
        // outside of the list.
        ConstIterator operator+(int steps) {
            for (int i = 0; i < steps; i++) {
                current_ = current_->next;
            }
            return *this;
        }

        // --iterator sets the current Node to the previous Node, then returns
        // the updated iterator.
        ConstIterator& operator--(void) {
            current_ = current_->prev;
            return *this;
        }

        // iterator-- sets the current Node to the previous Node, then returns
        // the old iterator.
        ConstIterator operator--(int) {
            ConstIterator old = *this;

            --*this;  // use the prefix -- overloaded above
            return old;
        }

        // iterator - n sets the current Node to the previous Node that is n
        // steps away, then returns the updated iterator.
        //
        // WARNING: This method will cause segfault if it tries to access
        // outside of the list.
        ConstIterator operator-(int steps) {
            for (int i = 0; i < steps; i++) {
                current_ = current_->prev;
            }
            return *this;
        }

        // iteratorA == iteratorB if they both have the same Node
        bool operator==(const ConstIterator& rhs) const {
            return current_ == rhs.current_;
        }

        bool operator!=(const ConstIterator& rhs) const {
            return !(*this == rhs);  // use the == overloaded above
        }

    protected:
        // the Node to which this Iterator points
        Node* current_;

        ConstIterator(Node* current) { current_ = current; }

        T& Retrieve() const { return current_->data; }

        // give LinkedList access to non-public members of ConstIterator
        friend class LinkedList<T>;
    };

    class Iterator : public ConstIterator {
    public:
        Iterator(void) : ConstIterator() {}

        // *iterator returns a reference to the data of the current Node member
        // of this object.
        T& operator*(void) { return ConstIterator::Retrieve(); }

        // *iterator returns a constant reference to the data of the current
        // Node member of this object.
        const T& operator*(void) const { return ConstIterator::operator*(); }

        // ++iterator sets the current Node to the next Node, then returns the
        // updated iterator.
        Iterator& operator++(void) {
            this->current_ = this->current_->next;
            return *this;
        }

        // iterator++ sets the current Node to the next Node, then returns the
        // old iterator.
        Iterator operator++(int) {
            Iterator old = *this;

            ++*this;  // use the prefix ++ overloaded above
            return old;
        }

        // iterator + n sets the current Node to the next Node that is n steps
        // away, then returns the updated iterator.
        //
        // WARNING: This method will cause segfault if it tries to access
        // outside of list.
        Iterator operator+(int steps) {
            for (int i = 0; i < steps; i++) {
                this->current_ = this->current_->next;
            }
            return *this;
        }

        // --iterator sets the current Node to the previous Node, then returns
        // the updated iterator.
        Iterator& operator--(void) {
            this->current_ = this->current_->prev;
            return *this;
        }

        // iterator-- sets the current Node to the previous Node, then returns
        // the old iterator.
        Iterator operator--(int) {
            Iterator old = *this;

            --*this;  // use the prefix -- overloaded above
            return old;
        }

        // iterator - n sets the current Node to the previous Node that is n
        // steps away, then returns the updated iterator.
        //
        // WARNING: This method will cause segfault if it tries to access
        // outside of list.
        Iterator operator-(int steps) {
            for (int i = 0; i < steps; i++) {
                this->current_ = this->current_->next;
            }
            return *this;
        }

    protected:
        Iterator(Node* current) : ConstIterator(current) {}

        // give LinkedList access to non-public members of Iterator
        friend class LinkedList<T>;
    };

// PUBLIC METHODS
public:
    LinkedList(void) { Init(); }

    // Make a deep copy of the right-hand side list and constructs and new list.
    LinkedList(const LinkedList& rhs) {
        // first init an empty list
        Init();

        for (ConstIterator it = rhs.Begin(); it != rhs.End(); ++it) {
            PushBack(*it);
        }
    }

    // Make a deep copy of the right-hand side list and assign this list to it.
    LinkedList& operator=(const LinkedList& rhs) {
        // prevent self-assignment which leads to self-deletion
        if (this == &rhs) return *this;

        // first clear this list
        Clear();

        for (ConstIterator it = rhs.Begin(); it != rhs.End(); ++it) {
            PushBack(*it);
        }

        return *this;
    }

    ~LinkedList(void) {
        Clear();
        delete head_;
        delete tail_;
        size_ = 0;
    }

    int Size(void) const { return size_; }

    bool IsEmpty(void) const { return size_ == 0; }

    // Returns an Iterator at the node after the sentinel head.
    Iterator Begin(void) { return Iterator(head_->next); }

    // Returns a ConstIterator at the node after the sentinel head.
    ConstIterator Begin(void) const { return ConstIterator(head_->next); }

    // Returns an Iterator at the sentinel tail.
    Iterator End(void) { return Iterator(tail_); }

    // Returns a ConstIterator at the sentinel tail.
    ConstIterator End(void) const { return ConstIterator(tail_); }

    // Returns a reference to the data of the first real node.
    T& Front(void) { return *Begin(); }

    // Returns a constant reference to the data of the first real node.
    const T& Front(void) const { return *Begin(); }

    // Returns a reference to the data of the last real node.
    T& Back(void) { return *--End(); }

    // Returns a constant reference to the data of the last real node.
    const T& Back(void) const { return *--End(); }

    void PushFront(const T& value) { Insert(Begin(), value); }

    void PushBack(const T& value) { Insert(End(), value); }

    void PopFront(void) { Erase(Begin()); }

    void PopBack(void) { Erase(--End()); }

    // Deallocates all nodes except the two sentinel nodes.
    void Clear(void) { while (!IsEmpty()) PopFront(); }

    // Inserts a node with data = value to the position pos in the list.
    // Returns an Iterator at the new node.
    Iterator Insert(Iterator pos, const T& value) {
        Node* new_node = new Node(value, pos.current_->prev, pos.current_);

        pos.current_->prev->next = new_node;  // the node before pos -> new_node
        pos.current_->prev = new_node;  // new_node -> the node at pos
        size_++;

        return Iterator(new_node);
    }

    /**
     * Removes a node from the list at the position  pos .
     * The node after the target node will be at position  pos  after removal.
     *
     * Returns an iterator pointing to the node after the target.
     */
    Iterator Erase(Iterator pos) {
        // safely return an Iterator to the sentinel tail if the list is empty
        if (IsEmpty()) return End();

        // the node after pos is after the node before pos
        pos.current_->prev->next = pos.current_->next;
        // the node before pos is before the node after pos
        pos.current_->next->prev = pos.current_->prev;
        // save the node after pos as the return value
        Iterator return_value = pos.current_->next;
        // deallocate the current node at pos
        delete pos.current_;
        // decrease list size by 1
        size_--;

        return return_value;
    }

    // Prints under the following format:
    // Node1Data <-> Node2Data <-> Node3Data <-> ... <-> NodeNData
    // Size = size_
    friend std::ostream& operator<<(std::ostream& out, const LinkedList& obj) {
        if (obj.IsEmpty()) {
            out << "List is empty." << std::endl;
        } else {
            // iterate over this list
            for (LinkedList::ConstIterator it = obj.Begin();
                 it != obj.End();
                 ++it) {
                // print the value of each node
                out << *it;
                // print the separator if the current node is not the back node
                if (it != obj.End() - 1) out << " <-> ";
            }

            // print size on the next line and finish the line
            out << "\nSize = " << obj.size_ << std::endl;
        }
        return out;
    }

// PRIVATE HELPER METHODS
private:
    // Initializes an empty list with two sentinel nodes.
    void Init(void) {
        head_ = new Node;
        tail_ = new Node;
        size_ = 0;
        head_->next = tail_;
        tail_->prev = head_;
    }

// PRIVATE DATA MEMBERS
private:
    Node* head_;
    Node* tail_;
    int size_;
};

#endif  // ALGORITHMS_INCLUDE_LINKEDLIST_HPP_
