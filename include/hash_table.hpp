// hash_table.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Hash Tables in C++
//
// This template class is an implementation of the hash table abstract data type
// and serves as a way to practice my data structure skills.
// This class provides methods to interact with a hash table, including
//     - Creating the table
//     - Making a copy of the table
//     - Getting the size of the table
//     - Adding an element to the table
//     - Removing an element from the table
//     - Checking whether the table contains an element or not

#ifndef ALGORITHMS_INCLUDE_HASHTABLE_HPP_
#define ALGORITHMS_INCLUDE_HASHTABLE_HPP_

#include <iostream>
#include <list>
#include <string>
#include <utility>
#include <vector>

using std::endl;
using std::ostream;

template <class HashedObj>
class HashTable {
public:
    explicit HashTable(int size = DEFAULT_CAPACITY) {
        // TODO
    }

    HashTable(const HashTable& rhs) {
        // TODO
    }

    HashTable& operator=(const HashTable& rhs) {
        // TODO
        return *this;
    }

    ~HashTable() {
        // TODO
    }

    void Clear() {
        // TODO
    }

    int Size() const { return size_; }

    bool IsEmpty() const { return size_ == 0; }

    bool Contains(const HashedObj& x) const {
        // TODO
        return false;
    }

    bool Insert(const HashedObj& x) {
        // TODO
        return false;
    }

    bool Insert(HashedObj&& x) {
        // TODO
        return false;
    }

    bool Remove(const HashedObj& x) {
        // TODO
        return false;
    }

    // Used for debugging purposes
    friend ostream& operator<<(ostream& out, const HashTable& hashtable) {
        // TODO
        return out;
    }

    static const int DEFAULT_CAPACITY = 128;

private:
    // Private members
    std::vector<std::list<HashedObj>> lists_;
    int size_;

    // Private methods

    void Rehash() {
        // TODO
    }

    size_t GetHash(const HashedObj& x) const {
        static Hash<HashedObj> hashfunc;

        return hashfunc(x) % lists_.size();
    }
};

// Generic hash class declaration
template <class Key>
class Hash {
public:
    size_t operator()(const Key& k) const;
};

// std::string is a valid type for the hash class
template <>
class Hash<std::string> {
public:
    // Uses Horner's rule to make an okay hash function.
    // Takes in an std::string key and returns a size_t index.
    // Usage:
    // Hash<std::string> hf;
    // int index = hf(key);
    size_t operator()(const std::string& key) {
        size_t hashval = 0;

        for (char ch : key) hashval += hashval * 37 + ch; // Horner's rule
        return hashval;
    }
};

#endif  // ALGORITHMS_INCLUDE_HASHTABLE_HPP_
