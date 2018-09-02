// avl_tree.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: AVL Trees in C++
//
// This template class serves as a way to practice my data structure skills.
// This class provides methods to interact with an AVL tree, including
// - Creating the tree
// - Making a copy of the tree
// - Find maximum and minimum elements in the tree
// - Checking whether an element is in the tree
// - Adding an element to the tree
// - Removing an element from the tree
// - Clearing the tree

#ifndef ALGORITHMS_INCLUDE_AVLTREE_HPP_
#define ALGORITHMS_INCLUDE_AVLTREE_HPP_

#include <iostream>
#include <utility>

using std::ostream;

template <class Comparable>
class AvlTree {
public:
    AvlTree() : root_{nullptr}, size_{0}, height_{0} {}

    AvlTree(const AvlTree& rhs) : size_{rhs.size_}, height_{rhs.height_} {
        root_ = CloneNode(rhs.root_);
    }

    AvlTree(AvlTree&& rhs)
        : root_{rhs.root_}, size_{rhs.size_}, height_{rhs.height_} {
        rhs.root_ = nullptr;
        rhs.size_ = rhs.height_ = 0;
    }

    AvlTree& operator=(const AvlTree& rhs) {
        if (this != &rhs) {
            root_ = CloneNode(rhs.root_);
            size_ = rhs.size_;
            height_ = rhs.height_;
        }

        return *this;
    }

    AvlTree& operator=(AvlTree&& rhs) {
        std::swap(root_, rhs.root_);
        std::swap(size_, rhs.size_);
        std::swap(height_, rhs.height_);

        return *this;
    }

    ~AvlTree() { Clear(); }

    void Clear() {
        DeleteNode(root_);
        size_ = height_ = 0;
    }

    int Size() const { return size_; }

    int Height() const { return height_; }

    bool IsEmpty() const { return size_ == 0; }

    bool Contains(const Comparable& object) const { return false; }

    void Insert(const Comparable& object) {}

    void Insert(Comparable&& object) {}

    void Remove(const Comparable& object) {}

    void RemoveAll(const Comparable& object) {}

    friend ostream& operator<<(ostream& out, const AvlTree& avltree) {
        return out;
    }

private:
    struct AvlNode {
        Comparable data;
        AvlNode* left;
        AvlNode* right;
        int height;

        AvlNode(const Comparable& node_data = Comparable(),
                AvlNode* left_node = nullptr, AvlNode* right_node = nullptr)
            : data{node_data}, left{left_node}, right{right_node} {}

        AvlNode(Comparable&& node_data, AvlNode* left_node = nullptr,
                AvlNode* right_node = nullptr)
            : data{std::move(node_data)},
              left{left_node},
              right{right_node} {}
    };

    // Private members

    AvlNode* root_;
    int size_;
    int height_;

    // Private helpers

    // Internal recursive method
    // Clones the subtree starting from  node  using preorder traversal.
    // In preorder traversal: parent -> left -> right.
    // Returns a newly created AVL node and eventually a newly cloned subtree.
    AvlNode* CloneNode(AvlNode* node) {
        if (!node) return nullptr;

        AvlNode* clone_node = new AvlNode(node->data);
        clone_node->left = CloneNode(node->left);
        clone_node->right = CloneNode(node->right);

        return clone_node;
    }

    // Internal recursive method
    // Deletes the subtree starting from  node  using postorder traversal.
    // In postorder traversal: left -> right -> parent.
    void DeleteNode(AvlNode* node) {
        if (node) {
            DeleteNode(node->left);
            Deletenode(node->right);
            delete node;
        }
    }
};

#endif  // ALGORITHMS_INCLUDE_AVLTREE_HPP_
