// binary_search_tree.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Binary Search Trees in C++
//
// This template class serves as a way to practice my data structure skills.
// This class provides methods to interact with a binary search tree, including
//     - Creating the tree
//     - Making a copy of the tree
//     - Find maximum and minimum elements in the tree
//     - Checking whether an element is in the tree
//     - Adding an element to the tree
//     - Removing an element from the tree
//     - Clearing the tree

#ifndef ALGORITHMS_INCLUDE_BINARYSEARCHTREE_HPP_
#define ALGORITHMS_INCLUDE_BINARYSEARCHTREE_HPP_

#include <iostream>
#include <stdexcept>
#include <string>
#include <utility>

namespace datastructure {

template<class Comparable>
class BinarySearchTree {
// BINARY SEARCH TREE NODE. LEFT NODE IS SMALLER. RIGHT NODE IS GREATER.
private:
    struct BinaryNode {
        Comparable data;
        struct BinaryNode* left;
        struct BinaryNode* right;

        BinaryNode(const Comparable& node_data = Comparable(),
                   BinaryNode* left_node = nullptr,
                   BinaryNode* right_node = nullptr)
            : data{node_data}, left{left_node}, right{right_node} {}

        BinaryNode(Comparable&& node_data,
                   BinaryNode* left_node = nullptr,
                   BinaryNode* right_node = nullptr)
            : data{std::move(node_data)}, left{left_node}, right{right_node} {}
    };

// PUBLIC METHODS OF BinarySearchTree
public:
    BinarySearchTree() { root_ = nullptr; }

    // Makes a deep copy of the right-hand side tree and construct a new one.
    BinarySearchTree(const BinarySearchTree& rhs) { root_ = Clone(rhs.root_); }

    // Makes a deep copy of the right-hand side tree and assign to this tree.
    BinarySearchTree& operator=(const BinarySearchTree& rhs) {
        // prevent self-assignment which leads to self-deletion
        if (this == &rhs) return *this;

        // first clear this tree
        Clear();

        // then start cloning from the root of the right-hand side tree
        root_ = Clone(rhs.root_);

        return *this;
    }

    ~BinarySearchTree() { Clear(); }

    // Returns a constant reference to the value of the leftmost binary node.
    // Throws std::out_of_range exception if the tree is empty.
    const Comparable& FindMin() const {
        if (IsEmpty()) throw std::out_of_range(
                      "BinarySearchTree::FindMin(): Tree is empty.");

        return FindMin(root_)->data;
    }

    // Returns a constant reference to the value of the rightmost binary node.
    // Throws std::out_of_range exception if the tree is empty.
    const Comparable& FindMax() const {
        if (IsEmpty()) throw std::out_of_range(
                      "BinarySearchTree::FindMax(): Tree is empty.");

        return FindMax(root_)->data;
    }

    bool Contains(const Comparable& value) const {
        return Contains(value, root_);
    }

    bool IsEmpty() const { return root_ == nullptr; }

    // Adds a new element to the right position in the tree.
    // Assumes that all elements have different values from each other.
    void Insert(const Comparable& value) { Insert(value, root_); }

    // Removes an element from the tree and keeps the tree sorted.
    // Assumes that all elements have different values from each other.
    void Remove(const Comparable& value) { Remove(value, root_); }

    // Removes all elements from the tree.
    void Clear() { Clear(root_); }

    // Prints the tree to std::ostream. Child nodes are indented.
    friend std::ostream& operator<<(std::ostream& out,
                                    const BinarySearchTree& obj) {
        obj.PrintTree(out, obj.root_);
        return out;
    }

// PRIVATE RECURSIVE HELPER METHODS OF BinarySearchTree
private:
    // Internal method that makes a deep copy of a subtree.
    // Returns a pointer to the new binary node.
    BinaryNode* Clone(BinaryNode* tree) const {
        if (tree == nullptr) return nullptr;

        // create a new node with the same value as the root of the subtree
        BinaryNode* new_node = new BinaryNode(tree->data);
        // clone the new node's left child according to the original
        new_node->left = Clone(tree->left);
        // clone the new node's right child according to the original
        new_node->right = Clone(tree->right);

        return new_node;
    }

    // Internal method that finds the minimum element in a subtree.
    // Returns a pointer to the leftmost node.
    BinaryNode* FindMin(BinaryNode* tree) const {
        if (tree == nullptr) return nullptr;

        // the leftmost node without any left child is the min
        if (tree->left == nullptr) return tree;

        return FindMin(tree->left);
    }

    // Internal method that finds the maximum element in a subtree.
    // Returns a pointer to the rightmost node.
    BinaryNode* FindMax(BinaryNode* tree) const {
        if (tree == nullptr) return nullptr;

        // the rightmost node without any right child is the max
        if (tree->right == nullptr) return tree;

        return FindMax(tree->right);
    }

    // Internal method that checks if a subtree has the specified binary node.
    // Assumes that all elements have different values from each other.
    bool Contains(const Comparable& value, BinaryNode* tree) const {
        if (tree == nullptr) return false;

        if (value == tree->data) {
            return true;
        } else if (value < tree->data) {
            return Contains(value, tree->left);
        } else {
            return Contains(value, tree->right);
        }
    }

    // Internal method that inserts a new binary node to a subtree in a correct
    // position.
    void Insert(const Comparable& value, BinaryNode*& tree) {
        if (tree == nullptr) {
            tree = new BinaryNode(value);
        } else if (value < tree->data) {
            Insert(value, tree->left);
        } else if (value > tree->data) {
            Insert(value, tree->right);
        }
    }

    // Internal method that removes a binary node from a subtree and keeps
    // the order of that subtree.
    void Remove(const Comparable& value, BinaryNode*& tree) {
        if (tree == nullptr) return;

        if (value < tree->data) {
            Remove(value, tree->left);
        } else if (value > tree->data) {
            Remove(value, tree->right);
        }
        // two children
        else if ((tree->left != nullptr) && (tree->right != nullptr)) {
            // replace the target node with the smallest element on the right
            tree->data = FindMin(tree->right)->data;
            Remove(tree->data, tree->right);
        }
        // one or zero child
        else {
            // save the target node for later deletion
            BinaryNode* target = tree;

            // move the subtree pointer to the child
            // due to passing as a non-constant reference,
            // the parent node will be modified after this
            if (tree->left != nullptr) {
                // e.g. parent->left (or right) = child->left
                tree = tree->left;
            } else {
                // e.g. parent->left (or right) = child->right
                tree = tree->right;
            }

            delete target;
        }
    }

    // Internal method that removes all childrens node of a subtree.
    void Clear(BinaryNode*& tree) {
        if (tree != nullptr) {
            Clear(tree->left);
            Clear(tree->right);
            delete tree;
        }

        // in the end, the parent of this subtree points left/right to nullptr
        tree = nullptr;
    }

    // Internal method that prints all nodes of a subtree on console.
    // The direction of the tree is left to right (instead of up to down).
    // Source: https://stackoverflow.com/a/19484210/4048938
    void PrintTree(std::ostream& out, BinaryNode* tree) const {
        if (tree == nullptr) {
            out << "Tree is empty." << std::endl;
        } else {
            // print all the nodes to the right of the root of this subtree
            if (tree->right != nullptr) PrintNode(out, tree->right, true, "");
            // print the root of this subtree
            out << tree->data << std::endl;
            // print all the nodes to the left of the root of this subtree
            if (tree->left != nullptr) PrintNode(out, tree->left, false, "");
        }
    }

    // Internal method that prints all nodes of a subtree on console.
    // The direction of the tree is left to right (instead of up to down).
    // Source: https://stackoverflow.com/a/19484210/4048938
    void PrintNode(std::ostream& out, BinaryNode* tree,
                   bool is_right_node, std::string indent) const {
        // print the right nodes until the rightmost
        if (tree->right != nullptr) {
            if (is_right_node) {
                PrintNode(out, tree->right, true, indent + "        ");
            } else {
                PrintNode(out, tree->right, true, indent + " |      ");
            }
        }

        out << indent;

        // different branching symbol for left and right nodes
        if (is_right_node) {
            out << " /";
        } else {
            out << " \\";
        }

        out << "----- " << tree->data << std::endl;

        // print the left nodes until the leftmost
        if (tree->left != nullptr) {
            if (is_right_node) {
                PrintNode(out, tree->left, false, indent + " |      ");
            } else {
                PrintNode(out, tree->left, false, indent + "        ");
            }
        }
    }

// PRIVATE DATA MEMBERS OF BinarySearchTree
private:
    BinaryNode* root_;
};

}  // namespace datastructure

#endif  // ALGORITHMS_INCLUDE_BINARYSEARCHTREE_HPP_
