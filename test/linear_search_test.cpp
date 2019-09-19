// linear_search_test.cpp
//
// Phi Luu
//
// Data Structures and Algorithms: Linear Search in C++
//
// Unit-tests the linear search algorithm implemented in this project.

#include <iostream>
#include <vector>
#include <gtest/gtest.h>
#include "linear_search.hpp"

TEST(LinearSearchUnitTests, EmptyArray) {
    int* arr = nullptr;
    EXPECT_EQ(-1, dsa::LinearSearch(-123, arr, 0));
    EXPECT_EQ(-1, dsa::LinearSearch(0, arr, 0));
    EXPECT_EQ(-1, dsa::LinearSearch(123, arr, 0));
}

TEST(LinearSearchUnitTests, NotInArray) {
    int size = 5;
    int arr[size] = {3, 23, 21, 9, -5};
    int key = 6;
    EXPECT_EQ(-1, dsa::LinearSearch(key, arr, size));
}

TEST(LinearSearchUnitTests, FirstInArray) {
    int size = 5;
    int arr[size] = {3, 23, 21, 9, -5};
    int key = 3;
    EXPECT_EQ(0, dsa::LinearSearch(key, arr, size));
}

TEST(LinearSearchUnitTests, WithinArray) {
    int size = 5;
    int arr[size] = {3, 23, 21, 9, -5};
    int key = 9;
    EXPECT_EQ(3, dsa::LinearSearch(key, arr, size));
}

TEST(LinearSearchUnitTests, LastInArray) {
    int size = 5;
    int arr[size] = {3, 23, 21, 9, -5};
    int key = -5;
    EXPECT_EQ(size - 1, dsa::LinearSearch(key, arr, size));
}

TEST(LinearSearchUnitTests, LargeArray) {
    int size = 100;
    int arr[size] = {-442, 122, 318, 313, -473, 423, -126, -28, 384, 260, -312, -282, -440, -205, 130, 2, -480, 450, 331, 56, 402, 16, 335, 91, -494, -91, 408, -254, -48, -20, 433, 441, -183, 204, 461, 354, -173, -110, 165, 418, 272, -213, -51, -24, -321, -367, -307, 480, -200, 178, -146, 40, -305, -94, 155, -36, -143, -148, -120, -122, 463, 119, -365, -467, -26, 17, -224, 64, 168, -97, 206, 26, 107, -202, -50, -393, 216, 208, 336, -108, 232, 86, 470, 424, -79, 92, 306, 350, -21, -281, -459, 378, 449, -457, -309, -343, -72, -124, -478, -76};
    int key = -305;
    EXPECT_EQ(52, dsa::LinearSearch(key, arr, size));
}

TEST(LinearSearchUnitTests, EmptyVector) {
    std::vector<int> vec;
    int key = 5;
    EXPECT_EQ(-1, dsa::LinearSearch(key, vec));
}

TEST(LinearSearchUnitTests, NotInVector) {
    std::vector<int> vec {3, 23, 21, 9, -5};
    int key = 6;
    EXPECT_EQ(-1, dsa::LinearSearch(key, vec));
}

TEST(LinearSearchUnitTests, FirstInVector) {
    std::vector<int> vec {3, 23, 21, 9, -5};
    int key = 3;
    EXPECT_EQ(0, dsa::LinearSearch(key, vec));
}

TEST(LinearSearchUnitTests, WithinVector) {
    std::vector<int> vec {3, 23, 21, 9, -5};
    int key = 21;
    EXPECT_EQ(2, dsa::LinearSearch(key, vec));
}

TEST(LinearSearchUnitTests, LastInVector) {
    std::vector<int> vec {3, 23, 21, 9, -5};
    int key = -5;
    EXPECT_EQ(vec.size() - 1, dsa::LinearSearch(key, vec));
}

TEST(LinearSearchUnitTests, LargeVector) {
    std::vector<int> vec {-442, 122, 318, 313, -473, 423, -126, -28, 384, 260, -312, -282, -440, -205, 130, 2, -480, 450, 331, 56, 402, 16, 335, 91, -494, -91, 408, -254, -48, -20, 433, 441, -183, 204, 461, 354, -173, -110, 165, 418, 272, -213, -51, -24, -321, -367, -307, 480, -200, 178, -146, 40, -305, -94, 155, -36, -143, -148, -120, -122, 463, 119, -365, -467, -26, 17, -224, 64, 168, -97, 206, 26, 107, -202, -50, -393, 216, 208, 336, -108, 232, 86, 470, 424, -79, 92, 306, 350, -21, -281, -459, 378, 449, -457, -309, -343, -72, -124, -478, -76};
    int key = -305;
    EXPECT_EQ(52, dsa::LinearSearch(key, vec));
}

int main(int argc, char** argv) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
