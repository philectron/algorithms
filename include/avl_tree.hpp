// avl_tree.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: AVL Trees in C++
//
// This template class serves as a way to practice my data structure skills.
// This class provides methods to interact with an AVL tree, including
//     - Creating the tree
//     - Making a copy of the tree
//     - Find maximum and minimum elements in the tree
//     - Checking whether an element is in the tree
//     - Adding an element to the tree
//     - Removing an element from the tree
//     - Clearing the tree

#ifndef ALGORITHMS_INCLUDE_AVLTREE_HPP_
#define ALGORITHMS_INCLUDE_AVLTREE_HPP_

#include <cassert>
#include <iostream>
#include <string>
#include <utility>

using std::endl;
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
        DeleteTree(root_);
        size_ = height_ = 0;
    }

    int Size() const { return size_; }

    int Height() const { return height_; }

    bool IsEmpty() const { return size_ == 0; }

    bool Contains(const Comparable& object) const { return false; }

    void Insert(const Comparable& object) {
        InsertNode(object, root_);
        size_++;
        height_ = Height(root_) + 1;
    }

    void Remove(const Comparable& object) {
        if (Contains(object)) {
            RemoveNode(object, root_);
            size_--;
            height_ = Height(root_) + 1;
        }
    }

    void RemoveAll(const Comparable& object) {
        while (Contains(object)) {
            RemoveNode(object, root_);
            size_--;
        }
        height_ = Height(root_) + 1;
    }

    friend ostream& operator<<(ostream& out, const AvlTree& avltree) {
        avltree.Print(out, avltree.root_);
        return out;
    }

private:
    struct AvlNode {
        Comparable data;
        AvlNode* left;
        AvlNode* right;
        int height;

        AvlNode(const Comparable& node_data = Comparable(),
                AvlNode* left_child = nullptr,
                AvlNode* right_child = nullptr,
                int node_height = 0)
            : data{node_data},
              left{left_child},
              right{right_child},
              height{node_height} {}

        AvlNode(Comparable&& node_data, AvlNode* left_child = nullptr,
                AvlNode* right_child = nullptr, int node_height = 0)
            : data{std::move(node_data)},
              left{left_child},
              right{right_child},
              height{node_height} {}
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
    // Deletes the subtree starting from  root  using postorder traversal.
    // In postorder traversal: left -> right -> parent.
    void DeleteTree(AvlNode* root) {
        if (root) {
            DeleteTree(root->left);
            DeleteTree(root->right);
            delete root;
        }
    }

    // Internal recursive method
    // Inserts a new object into the subtree with root  node .
    // Also, balances the new subtree and sets its new root.
    void InsertNode(const Comparable& object, AvlNode*& node) {
        // if reach where the new node needs to be inserted into
        if (!node)
            node = new AvlNode(object);
        // if the object is less than current node, go left
        else if (object < node->data)
            InsertNode(object, node->left);
        // if the object is more than current node, go right
        else if (object > node->data)
            InsertNode(object, node->right);

        // balance the subtree from bottom to top
        Balance(node);
    }

    // If the difference in heights of two sibling nodes is 1 or less, then
    // it is an acceptable imbalance factor.
    static const int ALLOWED_IMBALANCE = 1;

    // Internal method
    // Balances the AVL subtree with root  node  using the acceptable imbalance
    // factor above.
    void Balance(AvlNode*& node) {
        if (!node) return;

        // if subtree is heavy on the left
        if (Height(node->left) - Height(node->right) > ALLOWED_IMBALANCE) {
            // if the left child is heavy on the right, perform double rotation
            if (Height(node->left->left) < Height(node->left->right))
                RotateLeft(node->left);
            // still need to rotate right regardless of double rotation
            RotateRight(node);
        }
        // if subtree is heavy on the right
        else if (Height(node->right) - Height(node->left) > ALLOWED_IMBALANCE) {
            // if the right child is heavy on the left, perform double rotation
            if (Height(node->right->right) < Height(node->right->left))
                RotateRight(node->right);
            // still need to rotate left regardless of double rotation
            RotateLeft(node);
        }

        // set new height for the root of subtree
        SetHeight(node);
    }

    // Internal method
    // Rotates subtree to the left and sets its new root.
    void RotateLeft(AvlNode*& node) {
        // node  should not and must not be a nullptr
        assert(node);

        AvlNode* new_root = node->right;

        node->right = new_root->left;
        new_root->left = node;
        SetHeight(node);
        SetHeight(new_root);
        node = new_root;
    }

    // Internal method
    // Rotates subtree to the right and sets its new root.
    void RotateRight(AvlNode*& node) {
        // node  should not and must not be a nullptr
        assert(node);

        AvlNode* new_root = node->left;

        node->left = new_root->right;
        new_root->right = node;
        SetHeight(node);
        SetHeight(new_root);
        node = new_root;
    }

    // Internal method
    // Returns the height of  node  or -1 if nullptr.
    int Height(AvlNode* node) const {
        if (node)
            return node->height;
        else
            return -1;
    }

    // Internal method
    // Sets the height of  node  when its children change.
    void SetHeight(AvlNode*& node) {
        // node  should not and must not be a nullptr
        assert(node);

        int left_height = Height(node->left);
        int right_height = Height(node->right);

        if (left_height > right_height)
            node->height = left_height + 1;
        else
            node->height = right_height + 1;
    }

    // Internal recursive method
    // Removes an existing object from the subtree with root  node .
    void RemoveNode(const Comparable& object, AvlNode*& node) {
        if (!node) return;

        if (object < node->data) {
            RemoveNode(object, node->left);
        } else if (object > node->data) {
            RemoveNode(object, node->right);
        } else {
            // if the target node has two children
            if (node->left && node->right) {
                // replace this node's data with the left-most node's data
                // of that subtree
                node->data = LeftMostData(node->right);
                // remove that left most node starting from the right subtree
                RemoveNode(node->data, node->right);
            }
            // if the target node has 1 child or 0 children
            else {
                AvlNode* tmp = node;
                if (node->left)
                    node = node->left;
                else
                    node = node->right;
                delete tmp;
            }
        }
        Balance(node);
    }

    // Internal method
    // Returns the data of the left-most node of subtree with root  node .
    Comparable LeftMostData(AvlNode* node) const {
        // node  should not and must not be a nullptr
        assert(node);

        while (node->left) node = node->left;
        return node->data;
    }

    /*
    // Internal recursive method
    // Out streams all nodes of an AVL tree (Ugly version) in preorder.
    // Node format: parentNodeVal_NodeVal
    void UglyPrint(ostream& out, AvlNode* node,
                   AvlNode* parent = nullptr) const {
        if (!node) return;

        if (parent) out << parent->data << '_';
        out << node->data << ' ';
        UglyPrint(out, node->left, node);
        UglyPrint(out, node->right, node);
    }
    */

    // Internal method
    // Out streams all nodes of an AVL tree.
    // The direction of the tree is left to right (instead of up to down).
    // Credits: https://stackoverflow.com/a/19484210/4048938
    void Print(ostream& out, AvlNode* root) const {
        if (!root) {
            out << "Tree is empty" << endl;
        } else {
            // print all the nodes to the right of the root of this subtree
            if (root->right) PrintNode(out, root->right, true, "");
            // print the root of this subtree
            out << root->data << endl;
            // print all the nodes to the left of the root of this subtree
            if (root->left) PrintNode(out, root->left, false, "");
        }
    }

    // Internal recursive method
    // Out streams all nodes of a subtree with root  node .
    // The direction of the tree is left to right (instead of up to down).
    // Credits: https://stackoverflow.com/a/19484210/4048938
    void PrintNode(ostream& out, AvlNode* node, bool is_right_node,
                   std::string indent) const {
        // print the right nodes until the rightmost
        if (node->right) {
            if (is_right_node) {
                PrintNode(out, node->right, true, indent + "        ");
            } else {
                PrintNode(out, node->right, true, indent + " |      ");
            }
        }

        out << indent;

        // different branching symbol for left and right nodes
        if (is_right_node)
            out << " /";
        else
            out << " \\";

        out << "----- " << node->data << endl;

        // print the left nodes until the leftmost
        if (node->left) {
            if (is_right_node) {
                PrintNode(out, node->left, false, indent + " |      ");
            } else {
                PrintNode(out, node->left, false, indent + "        ");
            }
        }
    }
};

#endif  // ALGORITHMS_INCLUDE_AVLTREE_HPP_
