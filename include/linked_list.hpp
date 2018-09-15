// linked_list.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Linked Lists in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// The class  LinkedList  provides a few simple methods to interact with a
// linked list, including
// - Creating a linked list
// - Cloning a linked list
// - Assigning one linked list to another
// - Accessing front and back of a linked list
// - Accessing an arbitrary index of a linked list
// - Checking if a linked list contains an element
// - Inserting an element into the front and back of a linked list
// - Inserting an element into an arbitrary index of a linked list
// - Removing an element from the front and back of a linked list
// - Removing an element from an arbitrary index of a linked list
// - Clearing and destroying a linked list

#ifndef ALGORITHMS_INCLUDE_LINKEDLIST_HPP_
#define ALGORITHMS_INCLUDE_LINKEDLIST_HPP_

#include <iostream>
#include <utility>

namespace datastructure {

template<class T>
class LinkedList {
// USE DOUBLY LINKED LIST NODE STRUCTURE
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

// A NOTION OF POSITION IN THE LIST
public:
    class ConstIterator {
    public:
        ConstIterator() { current_ = nullptr; }

        // *iterator returns a constant reference to the data of the current
        // Node member of this object.
        const T& operator*() const { return Retrieve(); }

        // ++iterator sets the current Node to the next Node, then returns the
        // updated iterator.
        ConstIterator& operator++() {
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
        ConstIterator& operator--() {
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
        Iterator() : ConstIterator() {}

        // *iterator returns a reference to the data of the current Node member
        // of this object.
        T& operator*() { return ConstIterator::Retrieve(); }

        // *iterator returns a constant reference to the data of the current
        // Node member of this object.
        const T& operator*() const { return ConstIterator::operator*(); }

        // ++iterator sets the current Node to the next Node, then returns the
        // updated iterator.
        Iterator& operator++() {
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
        Iterator& operator--() {
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
    LinkedList() { Init(); }

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

    ~LinkedList() {
        Clear();
        delete head_;
        delete tail_;
        size_ = 0;
    }

    int Size() const { return size_; }

    bool IsEmpty() const { return size_ == 0; }

    // Returns an Iterator at the node after the sentinel head.
    Iterator Begin() { return Iterator(head_->next); }

    // Returns a ConstIterator at the node after the sentinel head.
    ConstIterator Begin() const { return ConstIterator(head_->next); }

    // Returns an Iterator at the sentinel tail.
    Iterator End() { return Iterator(tail_); }

    // Returns a ConstIterator at the sentinel tail.
    ConstIterator End() const { return ConstIterator(tail_); }

    // Returns a reference to the data of the first real node.
    T& Front() { return *Begin(); }

    // Returns a constant reference to the data of the first real node.
    const T& Front() const { return *Begin(); }

    // Returns a reference to the data of the last real node.
    T& Back() { return *--End(); }

    // Returns a constant reference to the data of the last real node.
    const T& Back() const { return *--End(); }

    void PushFront(const T& value) { Insert(Begin(), value); }

    void PushBack(const T& value) { Insert(End(), value); }

    void PopFront() { Erase(Begin()); }

    void PopBack() { Erase(--End()); }

    // Deallocates all nodes except the two sentinel nodes.
    void Clear() { while (!IsEmpty()) PopFront(); }

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
    void Init() {
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

}  // namespace datastructure

#endif  // #ifndef ALGORITHMS_INCLUDE_LINKEDLIST_HPP_
