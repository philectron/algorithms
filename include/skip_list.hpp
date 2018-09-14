// skip_list.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Skip Lists in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// The class  SkipList  provides a few simple methods to interact with
// a skip list, including
// - Creating a skip list
// - Making soft copies and hard copies of a skip list
// - Assigning one skip list to another
// - Checking if a skip list contains an element
// - Inserting an element into a skip list
// - Removing an element from a skip list
// - Clearing and destroying a skip list

#ifndef ALGORITHMS_INCLUDE_SKIPLIST_HPP_
#define ALGORITHMS_INCLUDE_SKIPLIST_HPP_

#include <cassert>
#include <iostream>
#include <random>
#include <stdexcept>
#include <string>
#include <utility>

using std::endl;
using std::ostream;

namespace datastructure {

template <class T>
class SkipList {
public:
    SkipList()
        : size_{0},
          height_{1},
          coinflip_{std::uniform_int_distribution<>(0, 1)} {
        top_head_ = new Node;
        rng_.seed(rd_());
    }

    // Makes a hard copy of the right-hand side skip list. EVERYTHING, including
    // the skip list nodes and their levels, will be copied to the left-hand
    // side skip list.
    //
    // To make a soft copy in which two lists will contain same objects and
    // their occurrences but not necessarily have identical rows, see SoftCopy()
    // method for more info.
    SkipList(const SkipList& rhs)
        : size_{rhs.size_},
          height_{rhs.height_},
          coinflip_{std::uniform_int_distribution<>(0, 1)} {
        HardCopy(rhs);
    }

    // Makes a hard copy of the right-hand side skip list. EVERYTHING, including
    // the skip list nodes and their levels, will be copied to the left-hand
    // side skip list.
    //
    // To make a soft copy in which two lists will contain same objects and
    // their occurrences but not necessarily have identical rows, see SoftCopy()
    // method for more info.
    SkipList& operator=(const SkipList& rhs) {
        if (this != &rhs) {
            size_ = rhs.size_;
            height_ = rhs.height_;
            coinflip_ = std::uniform_int_distribution<>(0, 1);
            HardCopy(rhs);
        }
        return *this;
    }

    // Makes a soft copy of the right-hand side skip list. All elements of the
    // right-hand side skip list will be inserted into the left-hand side list
    // Two lists will have the same objects and their occurrences but not
    // necessarily have identical skip list rows.
    //
    // To make a hard copy in which two lists will have EVERYTHING identical,
    // see the Copy Constructor and operator=() methods for more info.
    void SoftCopy(SkipList& destination) const {
        // self assignment check - only copy if the destination != source
        if (this != &destination) {
            // go to the bottom row of this skip list
            Node* current = top_head_;
            while (current && current->down) current = current->down;
            // go to the first node of the bottom row
            current = current->next;

            // insert while going right
            while (current) {
                destination.Insert(current->data);
                current = current->next;
            }
        }
    }

    ~SkipList() {
        Clear();
        delete top_head_;
    }

    int Size() const { return size_; }

    int Height() const { return height_; }

    bool IsEmpty() const { return size_ == 0; }

    // Inserts a new object to the skip list in the right order.
    void Insert(const T& object) {
        // from the top largest, recursively add new Nodes to existing rows
        Node* down_node = InsertAfter(SlideRight(top_head_, object), object);

        // if the new Node makes it to the top row and coin flip gives head
        if (down_node && CoinFlipGivesHead()) {
            // add a new Node on the new top row
            Node* new_node = new Node(object, nullptr, down_node);
            // add a new top head on the new top row
            top_head_ = new Node({}, new_node, top_head_);
            // update height
            height_++;
        }

        // update skip list's size
        size_++;
    }

    bool Contains(const T& object) const {
        // start from the top head
        Node* current = top_head_;
        while (current) {
            // go to the largest node smaller than  object
            current = SlideRight(current, object);
            // if the next node of the current node matches  object, return true
            if (current->next && current->next->data == object) return true;
            // otherwise, go down
            current = current->down;
        }

        // haven't found the object, return false
        return false;
    }

    // Removes one instance of  object  in the skip list.
    // If there are multiple instance, deletes the first occurrence.
    // If a row only consists of an instance of  object , deletes that row.
    //
    // For example,
    // Given s1 =
    //  Head -----------> 5
    //  Head -> 1 ------> 5 ------> 9
    //  Head -> 1 -> 3 -> 5 -> 7 -> 9
    // Given s2 =
    //  Head -----------> 5
    //  Head -> 1 ------> 5 -----------> 9
    //  Head -> 1 -> 3 -> 5 -> 5 -> 7 -> 9
    //
    // Then s1.Remove(5) will remove the only instance of 5 in s1
    //      s2.Remove(5) will remove the first occurrence of 5 in s2
    //
    // Namely, the result will look like the following
    // s1 =
    //  Head -> 1 -----------> 9
    //  Head -> 1 -> 3 -> 7 -> 9
    // s2 =
    //  Head -> 1 ----------------> 9
    //  Head -> 1 -> 3 -> 5 -> 7 -> 9
    void Remove(const T& object) {
        // start from the top head
        Node* current = top_head_;
        while (current) {
            // slide right to the largest node smaller than  object
            current = SlideRight(current, object);

            // if the next node exists and matches  object , delete that node
            if (current->next && current->next->data == object) {
                Node* tmp = current->next;
                current->next = tmp->next;
                delete tmp;

                // if at the bottom row, update size
                if (!current->down) size_--;
            }

            // delete the top row if the node was the only node in the top row
            if (!top_head_->next && top_head_->down) {
                Node* tmp = top_head_;
                top_head_ = top_head_->down;
                delete tmp;
                height_--;  // update height
            }

            // go down one level
            current = current->down;
        }
    }

    // Removes ALL instance of  object  in the skip list.
    // If a row only consists of an instance of  object , deletes that row.
    //
    // For example,
    // Given s1 =
    //  Head -----------> 5
    //  Head -> 1 ------> 5 ------> 9
    //  Head -> 1 -> 3 -> 5 -> 7 -> 9
    // Given s2 =
    //  Head -----------> 5
    //  Head -> 1 ------> 5 -----------> 9
    //  Head -> 1 -> 3 -> 5 -> 5 -> 7 -> 9
    //
    // Then s1.Remove(5) will remove the only instance of 5 in s1
    //      s2.Remove(5) will remove the both instances of 5 in s2
    //
    // Namely, the result will look like the following
    // s1 =
    //  Head -> 1 -----------> 9
    //  Head -> 1 -> 3 -> 7 -> 9
    // s2 =
    //  Head -> 1 -----------> 9
    //  Head -> 1 -> 3 -> 7 -> 9
    void RemoveAll(const T& object) {
        // start from the top head
        Node* current = top_head_;
        while (current) {
            // slide right to the largest node smaller than  object
            current = SlideRight(current, object);

            // if the next node exists and matches  object , delete that node
            while (current->next && current->next->data == object) {
                Node* tmp = current->next;
                current->next = tmp->next;
                delete tmp;

                // if at the bottom row, update size
                if (!current->down) size_--;
            }

            // delete the top row if the node was the only node in the top row
            if (!top_head_->next) {
                Node* tmp = top_head_;
                top_head_ = top_head_->down;
                delete tmp;
                height_--;  // update height
            }

            // go down one level
            current = current->down;
        }
    }

    // Deletes all nodes, leaving only one sentinel head in the skip list.
    void Clear() {
        // start from the top head
        while (top_head_ && top_head_->down) {
            // save the current top head for later deletion
            Node* current = top_head_;

            // move to the row beneath
            top_head_ = top_head_->down;

            // delete everything on the old top row
            while (current) {
                Node* tmp = current;
                current = current->next;
                delete tmp;
            }
        }

        // when reach the bottom row, delete everything except the head
        if (top_head_) {
            Node* current = top_head_->next;
            // let top head point next to nullptr
            top_head_->next = nullptr;
            // delete everything on the bottom row except the head
            while (current) {
                Node* tmp = current;
                current = current->next;
                delete tmp;
            }
        }
    }

    // // Ugly prints, for debugging purposes
    // friend ostream& operator<<(ostream& out, const SkipList& skiplist) {
    //     if (skiplist.IsEmpty()) {
    //         out << "Skip list is empty" << endl;
    //     } else {
    //         out << "Size = " << skiplist.Size()
    //             << ", Height = " << skiplist.Height() << endl;
    //         Node* current_head = skiplist.top_head_;
    //         while (current_head) {
    //             out << "Head";
    //             Node* current = current_head->next;
    //             while (current) {
    //                 out << " -> " << current->data;
    //                 current = current->next;
    //             }
    //             out << endl;
    //             current_head = current_head->down;
    //         }
    //     }
    //     return out;
    // }

    // For debugging purposes
    friend ostream& operator<<(ostream& out, const SkipList& skiplist) {
        if (skiplist.IsEmpty()) {
            out << "Skip list is empty" << endl;
        } else {
            out << "Size = " << skiplist.Size()
                << ", Height = " << skiplist.Height() << endl;
            skiplist.Print(out, skiplist.top_head_);
        }

        return out;
    }

private:
    struct Node {
        T data;
        struct Node* next;
        struct Node* down;

        Node(const T& node_data = T(), Node* next_node = nullptr,
             Node* down_node = nullptr)
            : data{node_data}, next{next_node}, down{down_node} {}

        Node(T&& node_data, Node* next_node = nullptr,
             Node* down_node = nullptr)
            : data{std::move(node_data)}, next{next_node}, down{down_node} {}
    };

    // Skip list's members
    Node* top_head_;
    int size_;
    int height_;

    // Use Mersenne Twister RNG to flip coins, used when inserting new nodes
    std::mt19937 rng_;
    std::random_device rd_;
    std::uniform_int_distribution<int> coinflip_;

    // Private helpers

    // If coin flip gives head (1), returns true.
    // If coin flip gives tail (0), returns false.
    bool CoinFlipGivesHead() { return coinflip_(rng_); }

    // Traverses to the right until reaching nullptr or the input value.
    //
    // current  starting node and must not (and should not) be a nullptr
    // val      the input value which the returned Node* should be smaller than
    //
    // Returns a Node* that is either
    // - right before the nullptr.
    // - OR having the largest  data  that is smaller than  val .
    //
    // For example: sentinel_head -> 1 -> 2 -> 3 -> 4 -> nullptr
    // - SlideRight(sentinel_head, 4) will return pointer to node with value 3.
    // - SlideRight(sentinel_head, 5) will return pointer to node with value 4.
    Node* SlideRight(Node* current, const T& val) const {
        // current  should not and must not be a nullptr
        assert(current);

        while (current->next && current->next->data < val) {
            current = current->next;
        }
        return current;
    }

    // Inserts a new Node (or new Nodes) in existing lists of the skip list.
    //
    // current  the largest node of its row that is smaller than  val
    // val      the value of the new Node (or new Nodes)
    //
    // A new Node will certainly be inserted in the right order on the bottom
    // row of the skip list. After that, whether this new Node makes it to
    // the higher rows depends on the coin flips.
    //
    // Returns
    // - pointer to the new Node if it makes to the highest row of the skip list
    // - nullptr if the new Node doesn't make it to the highest row
    Node* InsertAfter(Node* current, const T& val) {
        // current  should not and must not be a nullptr
        assert(current);

        Node* new_node = nullptr;
        Node* down_node = nullptr;

        if (!current->down) {
            // base case, if reach bottom then make a new node
            new_node = new Node(val, current->next, nullptr);
            current->next = new_node;
        } else {
            // recursive case, let the newest node be the down node
            down_node = InsertAfter(SlideRight(current->down, val), val);
            // if down node exists and the coin flip gives head
            if (down_node && CoinFlipGivesHead()) {
                // make a new node on the current level
                new_node = new Node(val, current->next, down_node);
                current->next = new_node;
            }
        }

        return new_node;
    }

    // Makes a hard copy of  rhs  to this skip list.
    void HardCopy(const SkipList& rhs) {
        int bottom = height_ - 1;
        // keep track of the current node on each of the rows of  rhs
        Node* row_trav_rhs[height_];  // [0] = top, [height_ - 1] = bottom
        // keep track of the current node on each of the rows of this skip list
        Node* row_trav_lhs[height_];  // [0] = top, [height_ - 1] = bottom

        // from top to bottom, save all rhs heads to  row_trav_rhs
        Node* tmp = rhs.top_head_;
        for (int i = 0; i < height_ && tmp; i++) {
            row_trav_rhs[i] = tmp;
            tmp = tmp->down;
        }

        // from bottom to top, create identical heads to  row_trav_lhs
        top_head_ = nullptr;
        for (int i = bottom; i >= 0; i--) {
            // new top head identical to rhs
            // point next to nullptr and down to old top head
            top_head_ = new Node(row_trav_rhs[i]->data, nullptr, top_head_);
            // save these heads to  row_trav_lhs
            row_trav_lhs[i] = top_head_;
        }

        // while the traversal nodes of the bottom rows of both lists exist
        // and while there is a node after the rhs traversal node
        while (row_trav_rhs[bottom] && row_trav_lhs[bottom]
               && row_trav_rhs[bottom]->next) {
            // copy the next node of the bottom row from  rhs  to  lhs
            row_trav_lhs[bottom]->next =
                new Node(row_trav_rhs[bottom]->next->data);

            // if the node takes multiple levels in  rhs , copy them too
            Node* down_node = row_trav_lhs[bottom]->next;

            for (int i = bottom - 1;
                 i >= 0 && row_trav_rhs[i]->next
                        && row_trav_rhs[i]->next->data == down_node->data;
                 i--) {
                // create the node one level above
                down_node = new Node(down_node->data, nullptr, down_node);
                row_trav_lhs[i]->next = down_node;
                // update the traversal pointers of the respective rows
                row_trav_lhs[i] = row_trav_lhs[i]->next;
                row_trav_rhs[i] = row_trav_rhs[i]->next;
            }
            // update the traversal pointers of the bottom rows of both lists
            row_trav_lhs[bottom] = row_trav_lhs[bottom]->next;
            row_trav_rhs[bottom] = row_trav_rhs[bottom]->next;
        }
    }

    // Prints the skip list prettily, starting from the current head.
    void Print(ostream& out, Node* current_head) const {
        // current_head  should not and must not be a nullptr
        assert(current_head);

        while (current_head) {
            Node* current = current_head;
            out << "Head";
            while (current && current->next) {
                PrintNode(out, current, current->next->data);
                current = current->next;
            }
            current_head = current_head->down;
            out << endl;
        }
    }

    // Goes to the bottom row and figures out the space between the current node
    // with the next node and add hyphens when needed.
    void PrintNode(ostream& out, Node* prev, const T& nextvalue) const {
        // prev  should not and must not be a nullptr
        assert(prev);

        // go straight to the bottom row from the current node
        Node* current = prev->next;
        while (current && current->down) current = current->down;

        // go straight to the bottom row from the previous node
        while (prev->down) prev = prev->down;

        Node* trav = prev->next;
        std::string prefix;

        // go right until address matches current's bottom & add necessary space
        while (trav && trav != current) {
            prefix += " -> " + std::to_string(trav->data);
            trav = trav->next;
        }
        prefix += " -> " + std::to_string(nextvalue);

        // prefix will look like the following string:
        // " -> 1 -> 2 -> 3 -> 100 -> 2000 -> nextvalue"
        // find the last occurrence of the '>' character convert all characters
        // of prefix from i = 1 to i = last_of('>')
        for (int i = 1, arrow = prefix.find_last_of('>'); i < arrow; i++) {
            prefix[i] = '-';
        }

        out << prefix;
    }
};

}  // namespace datastructure

#endif  // ALGORITHMS_INCLUDE_SKIPLIST_HPP_
