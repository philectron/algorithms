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
#include "reader.hpp"

#define NUM_MIN -100000
#define NUM_MAX 100000
#define ARR_SIZE_MAX 30000
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
    int int_arr[1] = {r.RandomInteger<int>(NUM_MIN, NUM_MAX)};
    double double_arr[1] = {r.RandomReal<double>(NUM_MIN, NUM_MAX)};
    char char_arr[1];
    r.RandomString(char_arr, 1);

    dsa::InsertionSort<int>(int_arr, 1);
    EXPECT_TRUE(IsSorted<int>(int_arr, 1));

    dsa::InsertionSort<double>(double_arr, 1);
    EXPECT_TRUE(IsSorted<double>(double_arr, 1));

    dsa::InsertionSort<char>(char_arr, 1);
    EXPECT_TRUE(IsSorted<char>(char_arr, 1));
}

// Tests whether insertion sort sorts an array consisted of two elements.
TEST(InsertionSortTest, ArrayHasTwoElements) {
    Random r;
    int int_arr[2];
    r.RandomIntegerArray<int>(int_arr, 2, NUM_MIN, NUM_MAX);
    double double_arr[2];
    r.RandomRealArray<double>(double_arr, 2, NUM_MIN, NUM_MAX);
    char char_arr[2];
    r.RandomString(char_arr, 2);

    dsa::InsertionSort<int>(int_arr, 2);
    EXPECT_TRUE(IsSorted<int>(int_arr, 2));

    dsa::InsertionSort<double>(double_arr, 2);
    EXPECT_TRUE(IsSorted<double>(double_arr, 2));

    dsa::InsertionSort<char>(char_arr, 2);
    EXPECT_TRUE(IsSorted<char>(char_arr, 2));
}

// Tests whether insertion sort sorts a small array.
TEST(InsertionSortTest, SmallArray) {
    std::string filename = "array-small.txt";
    Reader r;
    size_t size = r.ReadNumString<int>(filename);
    int arr[size];
    r.ReadArray<int>(filename, arr, size);

    dsa::InsertionSort<int>(arr, size);
    EXPECT_TRUE(IsSorted<int>(arr, size));
}

// Tests whether insertion sort sorts a large array.
TEST(InsertionSortTest, LargeArray) {
    std::string filename = "array-large.txt";
    Reader r;
    size_t size = r.ReadNumString<int>(filename);
    int arr[size];
    r.ReadArray<int>(filename, arr, size);

    dsa::InsertionSort<int>(arr, size);
    EXPECT_TRUE(IsSorted<int>(arr, size));
}

// Tests whether insertion sort sorts arrays consisted of random elements.
TEST(InsertionSortTest, RandomArray) {
    Random r;
    size_t int_size = r.RandomInteger<size_t>(0, ARR_SIZE_MAX);
    int int_arr[int_size];
    r.RandomIntegerArray<int>(int_arr, int_size, NUM_MIN, NUM_MAX);

    size_t double_size = r.RandomInteger<size_t>(0, ARR_SIZE_MAX);
    double double_arr[double_size];
    r.RandomRealArray<double>(double_arr, double_size, NUM_MIN, NUM_MAX);

    size_t char_size = r.RandomInteger<size_t>(0, ARR_SIZE_MAX);
    char char_arr[char_size];
    r.RandomString(char_arr, char_size);

    dsa::InsertionSort<int>(int_arr, int_size);
    EXPECT_TRUE(IsSorted<int>(int_arr, int_size));

    dsa::InsertionSort<double>(double_arr, double_size);
    EXPECT_TRUE(IsSorted<double>(double_arr, double_size));

    dsa::InsertionSort<char>(char_arr, char_size);
    EXPECT_TRUE(IsSorted<char>(char_arr, char_size));
}

// Tests whether insertion sort sorts an empty vector.
TEST(InsertionSortTest, EmptyVector) {
    std::vector<int> vec;

    dsa::InsertionSort<int>(vec);

    EXPECT_TRUE(IsSorted<int>(vec));
}

// Tests whether insertion sort sorts a vector consisted of one element.
TEST(InsertSortTest, VectorHasOneElement) {
    Random r;
    std::vector<int> int_vec;
    int_vec.push_back(r.RandomInteger<int>(NUM_MIN, NUM_MAX));
    std::vector<double> double_vec;
    double_vec.push_back(r.RandomReal<double>(NUM_MIN, NUM_MAX));
    std::vector<char> char_vec;
    char_vec.push_back(r.RandomCharacter());

    dsa::InsertionSort<int>(int_vec);
    EXPECT_TRUE(IsSorted<int>(int_vec));

    dsa::InsertionSort<double>(double_vec);
    EXPECT_TRUE(IsSorted<double>(double_vec));

    dsa::InsertionSort<char>(char_vec);
    EXPECT_TRUE(IsSorted<char>(char_vec));
}

// Tests whether insertion sort sorts a vector consisted of two elements.
TEST(InsertionSortTest, VectorHasTwoElements) {
    Random r;
    std::vector<int> int_vec = r.RandomIntegerVector<int>(2, NUM_MIN, NUM_MAX);
    std::vector<double> double_vec = r.RandomRealVector<double>(2, NUM_MIN, NUM_MAX);
    std::vector<char> char_vec(2);
    for (size_t i = 0, size = char_vec.size(); i < size; i++)
        char_vec[i] = r.RandomCharacter();

    dsa::InsertionSort<int>(int_vec);
    EXPECT_TRUE(IsSorted<int>(int_vec));

    dsa::InsertionSort<double>(double_vec);
    EXPECT_TRUE(IsSorted<double>(double_vec));

    dsa::InsertionSort<char>(char_vec);
    EXPECT_TRUE(IsSorted<char>(char_vec));
}

// Tests whether insertion sort sorts a small vector.
TEST(InsertionSortTest, SmallVector) {
    std::string filename = "array-small.txt";
    Reader r;
    std::vector<int> vec = r.ReadVector<int>(filename);

    dsa::InsertionSort<int>(vec);
    EXPECT_TRUE(IsSorted<int>(vec));
}

// Tests whether insertion sort sorts a large vector.
TEST(InsertionSortTest, LargeVector) {
    std::string filename = "array-large.txt";
    Reader r;
    std::vector<int> vec = r.ReadVector<int>(filename);

    dsa::InsertionSort<int>(vec);
    EXPECT_TRUE(IsSorted<int>(vec));
}

// Tests whether insertion sort sorts vectors consisted of random elements.
TEST(InsertionSortTest, RandomVector) {
    Random r;
    size_t int_size = r.RandomInteger<size_t>(0, ARR_SIZE_MAX);
    std::vector<int> int_vec = r.RandomIntegerVector<int>(int_size, NUM_MIN, NUM_MAX);

    size_t double_size = r.RandomInteger<size_t>(0, ARR_SIZE_MAX);
    std::vector<double> double_vec = r.RandomRealVector<double>(double_size, NUM_MIN, NUM_MAX);

    size_t str_size = r.RandomInteger<size_t>(0, ARR_SIZE_MAX);
    std::vector<std::string> str_vec = r.RandomStringVector(str_size, STR_MAX_LEN);

    dsa::InsertionSort<int>(int_vec);
    EXPECT_TRUE(IsSorted<int>(int_vec));

    dsa::InsertionSort<double>(double_vec);
    EXPECT_TRUE(IsSorted<double>(double_vec));

    dsa::InsertionSort<std::string>(str_vec);
    EXPECT_TRUE(IsSorted<std::string>(str_vec));
}

int main(int argc, char** argv) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
