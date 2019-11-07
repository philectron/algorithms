// insertion-sort-test.cpp
//
// Phi Luu
//
// Data Structures and Algorithms: Insertion Sort
//
// Unit-tests the insertion sort algorithm implemented in this project using
// the Google Test framework.

#include <gtest/gtest.h>
#include <vector>
#include "insertion-sort.hpp"
#include "random.hpp"

#define NUM_MIN -100000
#define NUM_MAX 100000
#define STR_MAX_LEN 100

// Returns true if the array is sorted, or false if there is any unsorted pair
// in the array.
template <class Comparable>
bool IsSorted(Comparable* array, size_t size) {
    // null arrays and empty arrays are sorted
    if (!array || size == 0)
        return true;

    for (size_t i = 0; i < size - 1; i++) {
        if (array[i] > array[i + 1])
            return false;
    }

    return true;
}

// Returns true if the vector is sorted, or false if there is any unsorted pair
// in the vector.
template <class Comparable>
bool IsSorted(const std::vector<Comparable>& vector) {
    // empty vectors are sorted
    if (vector.empty())
        return true;

    for (size_t i = 0, size = vector.size(); i < size - 1; i++) {
        if (vector[i] > vector[i + 1])
            return false;
    }

    return true;
}

// Tests whether insertion sort sorts an empty array.
TEST(InsertionSortTest, EmptyArray) {
    int* arr = nullptr;

    dsa::InsertionSort<int>(arr, 0);

    EXPECT_TRUE(IsSorted<int>(arr, 0));
}

// Tests whether insertion sort sorts an array consisted of one element.
TEST(InsertSortTest, ArrayHasOneElement) {
    Random r;
    int intArr[1] = {r.RandomInteger<int>(NUM_MIN, NUM_MAX)};
    double doubleArr[1] = {r.RandomReal<double>(NUM_MIN, NUM_MAX)};

    dsa::InsertionSort<int>(intArr, 1);
    EXPECT_TRUE(IsSorted<int>(intArr, 1));

    dsa::InsertionSort<double>(doubleArr, 1);
    EXPECT_TRUE(IsSorted<double>(doubleArr, 1));
}

// TODO

// Tests whether insertion sort sorts an empty vector.
TEST(InsertionSortTest, EmptyVector) {
    std::vector<int> vec;

    dsa::InsertionSort<int>(vec);

    EXPECT_TRUE(IsSorted<int>(vec));
}

// Tests whether insertion sort sorts a vector consisted of one element.
TEST(InsertSortTest, VectorHasOneElement) {
    Random r;
    std::vector<int> intVec;
    intVec.push_back(r.RandomInteger<int>(NUM_MIN, NUM_MAX));
    std::vector<double> doubleVec;
    doubleVec.push_back(r.RandomReal<double>(NUM_MIN, NUM_MAX));

    dsa::InsertionSort<int>(intVec);
    EXPECT_TRUE(IsSorted<int>(intVec));

    dsa::InsertionSort<double>(doubleVec);
    EXPECT_TRUE(IsSorted<double>(doubleVec));
}

int main(int argc, char** argv) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
