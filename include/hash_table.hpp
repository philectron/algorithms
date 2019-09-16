// hash_table.hpp
//
// Phi Luu
//
// Data Structures and Algorithms: Hash Tables in C++
//
// This implementation serves as one of many ways to refine skills in
// data structures and algorithms.
//
// The class  HashTable  provides a few simple methods to interact with a
// hash table, including
// - Creating a hash table
// - Cloning a hash table
// - Assigning one hash table to another
// - Checking if a hash table contains an element
// - Inserting an element into a hash table
// - Removing an element from a hash table
// - Clearing and destroying a hash table

#ifndef INCLUDE_HASHTABLE_HPP_
#define INCLUDE_HASHTABLE_HPP_

#include <iostream>
#include <list>
#include <string>
#include <utility>
#include <vector>

namespace dsa {

// Generic hash class declaration
template <class Key>
class Hash {
public:
    size_t operator()(const Key& k) const;
};

// HashedObj  must have  operator==()  and  operator!=()  defined.
template <class HashedObj>
class HashTable {
public:
    explicit HashTable(int list_size = DEFAULT_CAPACITY)
        : lists_{std::vector<std::list<HashedObj>>(list_size)},
          current_size_{0} {}

    HashTable(const HashTable& rhs)
        : lists_{rhs.lists_}, current_size_{rhs.current_size_} {}

    HashTable& operator=(const HashTable& rhs) {
        // check for self-assignment
        if (this != &rhs) {
            lists_ = rhs.lists_;
            current_size_ = rhs.current_size_;
        }

        return *this;
    }

    ~HashTable() {
        Clear();
        lists_.clear();
    }

    // Removes every  HashedObj  from the hash table except the container.
    void Clear() {
        // clear each of the lists one by one
        for (auto& hashlist : lists_) hashlist.clear();
        // reset  current_size
        current_size_ = 0;
    }

    int CurrentSize() const { return current_size_; }

    int ListSize() const { return lists_.size(); }

    bool IsEmpty() const { return current_size_ == 0; }

    // Returns  true  if the hash table contains object x.
    // Returns  false  otherwise.
    bool Contains(const HashedObj& x) const {
        // get the index of the hashed object
        int hash_index = GetHash(x);

        // search through the list at index  hash_index  of the hash table
        for (auto const& object : lists_[hash_index])
            if (object == x) return true;

        return false;
    }

    // Inserts a hashed object  x  into the hash table.
    // Returns  true  when successful and  false  otherwise.
    bool Insert(const HashedObj& x) {
        // if the hash table already contains the object x, do not insert
        if (Contains(x)) return false;

        // get the index of the object and push to the front
        lists_[GetHash(x)].push_front(x);
        // increase size; rehash to get a good table load ratio when needed
        if (++current_size_ > (int)lists_.size()) Rehash();

        return true;
    }

    // Moves a hashed object  x  into the hash table.
    // Returns  true  when successful and  false  otherwise.
    bool Insert(HashedObj&& x) {
        // if the hash table already contains the object x, do not insert
        if (Contains(x)) return false;

        // get the index of the object and push to the front
        lists_[GetHash(x)].push_front(std::move(x));
        // increase size; rehash to get a good table load ratio when needed
        // 1:1 or less is good
        if (++current_size_ > (int)lists_.size()) Rehash();

        return true;
    }

    // Removes a hashed object  x  from the hash table.
    // Returns  true  when successful and  false  otherwise.
    bool Remove(const HashedObj& x) {
        // get the index of the hashed object
        int hash_index = GetHash(x);

        // search through the list at index  hash_index  of the table
        for (auto it = lists_[hash_index].begin();
             it != lists_[hash_index].end();
             ++it) {
            // if reach the object, remove and return
            if (*it == x) {
                lists_[hash_index].erase(it);
                current_size_--;
                return true;
            }
        }

        return false;
    }

    // Used for debugging purposes
    // For this method to work,  HashedObj  needs to have an  operator<<()
    // method.
    friend std::ostream& operator<<(std::ostream& out,
                                    const HashTable& hashtable) {
        if (hashtable.IsEmpty()) {
            out << "Hash table is empty" << std::endl;
        } else {
            // print sizes
            out << "Current size = " << hashtable.CurrentSize() << std::endl
                << "List size = " << hashtable.ListSize() << std::endl;

            // print the table row by row
            for (int i = 0, rows = hashtable.ListSize(); i < rows; i++) {
                // print the row number with right alignment
                std::string rows_string = std::to_string(rows);
                std::string i_string = std::to_string(i);
                int num_digit_diff = rows_string.length() - i_string.length();

                for (int j = 0; j < num_digit_diff; j++) out << ' ';
                out << i << ". ";

                // outstream all objects in the i-th list
                for (auto const& object : hashtable.lists_[i]) {
                    out << object;
                    if (&object != &hashtable.lists_[i].back()) out << " -> ";
                }
                out << std::endl;
            }
        }
        return out;
    }

    static const int DEFAULT_CAPACITY = 64;

private:
    // Private members
    std::vector<std::list<HashedObj>> lists_;  // an array of lists
    int current_size_;  // the total number of hash links in the table

    // Private methods

    // Rehashes all objects in the hash table when the table load ratio is big
    // (current_size_ / lists_.size() > 1 aka current_size_ > lists_.size()).
    // Builds another table that is twice as big and move the data there.
    void Rehash() {
        // save the current data
        std::vector<std::list<HashedObj>> old_lists = lists_;

        // double the size of the table and clear all links in the lists
        lists_.resize(lists_.size() * 2);
        Clear();

        // move the old data to the expanded lists one by one
        for (auto& hashlist : old_lists)
            for (auto& old_obj : hashlist) Insert(std::move(old_obj));
    }

    size_t GetHash(const HashedObj& x) const {
        static Hash<HashedObj> hashfunc;

        return hashfunc(x) % lists_.size();
    }
};

// string is a valid type for the hash class
template <>
class Hash<std::string> {
public:
    // Uses Horner's rule to make an okay hash function.
    // Takes in a string key and returns a size_t index.
    // Usage:
    // Hash<std::string> hf;
    // int index = hf(key);
    size_t operator()(const std::string& key) {
        size_t hashval = 0;

        for (char ch : key) hashval += hashval * 37 + ch; // Horner's rule
        return hashval;
    }
};

}  // namespace dsa

#endif  // #ifndef INCLUDE_HASHTABLE_HPP_
