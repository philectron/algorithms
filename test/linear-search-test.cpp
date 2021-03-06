// linear-search-test.cpp
//
// Phi Luu
//
// Data Structures and Algorithms: Linear Search in C++
//
// Unit-tests the linear search algorithm implemented in this project using
// the Google Test framework.

#include <cstdlib>
#include <iostream>
#include <vector>
#include <gtest/gtest.h>
#include "linear-search.hpp"
#include "reader.hpp"

// Tests whether the linear search algorithm returns a correct value for
// an empty array.
TEST(LinearSearchTest, EmptyArray) {
    int* arr = nullptr;
    EXPECT_EQ(-1, dsa::LinearSearch(-123, arr, 0));
    EXPECT_EQ(-1, dsa::LinearSearch(0, arr, 0));
    EXPECT_EQ(-1, dsa::LinearSearch(123, arr, 0));
}

// Tests whether the linear search algorithm returns a correct value for
// a key not in the array.
TEST(LinearSearchTest, NotInArray) {
    std::string filename = "array-small.txt";
    Reader reader;
    size_t size = reader.ReadNumString<int>(filename);
    int array[size];
    reader.ReadArray<int>(filename, array, size);
    int key = 6;
    EXPECT_EQ(-1, dsa::LinearSearch(key, array, size));
}

// Tests whether the linear search algorithm returns the correct value for
// a key that is the first element of the array.
TEST(LinearSearchTest, FirstInArray) {
    std::string filename = "array-small.txt";
    Reader reader;
    size_t size = reader.ReadNumString<int>(filename);
    int array[size];
    reader.ReadArray<int>(filename, array, size);
    int key = 3;
    EXPECT_EQ(0, dsa::LinearSearch(key, array, size));
}

// Tests whether the linear search algorithm returns the correct value for
// a key somewhere in the array.
TEST(LinearSearchTest, WithinArray) {
    std::string filename = "array-small.txt";
    Reader reader;
    size_t size = reader.ReadNumString<int>(filename);
    int array[size];
    reader.ReadArray<int>(filename, array, size);
    int key = 9;
    EXPECT_EQ(3, dsa::LinearSearch(key, array, size));
}

// Tests whether the linear search algorithm returns the correct value for
// a key that is the last element of the array.
TEST(LinearSearchTest, LastInArray) {
    std::string filename = "array-small.txt";
    Reader reader;
    size_t size = reader.ReadNumString<int>(filename);
    int array[size];
    reader.ReadArray<int>(filename, array, size);
    int key = -5;
    EXPECT_EQ(size - 1, dsa::LinearSearch(key, array, size));
}

// Tests whether the linear search algorithm returns the correct value for
// a key somewhere in a large array.
TEST(LinearSearchTest, LargeArray) {
    std::string filename = "array-large.txt";
    Reader reader;
    size_t size = reader.ReadNumString<int>(filename);
    int array[size];
    reader.ReadArray<int>(filename, array, size);
    int key = -305;
    EXPECT_EQ(52, dsa::LinearSearch(key, array, size));
}

// Tests whether the linear search algorithm returns the correct value for
// an empty vector.
TEST(LinearSearchTest, EmptyVector) {
    std::vector<int> vec;
    int key = 5;
    EXPECT_EQ(-1, dsa::LinearSearch(key, vec));
}

// Tests whether the linear search algorithm returns the correct value for
// a key not in the vector.
TEST(LinearSearchTest, NotInVector) {
    std::string filename = "array-small.txt";
    Reader reader;
    std::vector<int> vector = reader.ReadVector<int>(filename);
    int key = 6;
    EXPECT_EQ(-1, dsa::LinearSearch(key, vector));
}

// Tests whether the linear search algorithm returns the correct value for
// a key that is the first element of the vector.
TEST(LinearSearchTest, FirstInVector) {
    std::string filename = "array-small.txt";
    Reader reader;
    std::vector<int> vector = reader.ReadVector<int>(filename);
    int key = 3;
    EXPECT_EQ(0, dsa::LinearSearch(key, vector));
}

// Tests whether the linear search algorithm returns the correct value for
// a key somewhere in the vector.
TEST(LinearSearchTest, WithinVector) {
    std::string filename = "array-small.txt";
    Reader reader;
    std::vector<int> vector = reader.ReadVector<int>(filename);
    int key = 21;
    EXPECT_EQ(2, dsa::LinearSearch(key, vector));
}

// Tests whether the linear search algorithm returns the correct value for
// a key that is the last element of the vector.
TEST(LinearSearchTest, LastInVector) {
    std::string filename = "array-small.txt";
    Reader reader;
    std::vector<int> vector = reader.ReadVector<int>(filename);
    int key = -5;
    EXPECT_EQ(vector.size() - 1, dsa::LinearSearch(key, vector));
}

// Tests whether the linear search algorithm returns the correct value for
// a key somewhere in a large vector.
TEST(LinearSearchTest, LargeVector) {
    std::string filename = "array-large.txt";
    Reader reader;
    std::vector<int> vector = reader.ReadVector<int>(filename);
    int key = -305;
    EXPECT_EQ(52, dsa::LinearSearch(key, vector));
}

int main(int argc, char** argv) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
