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

    ASSERT_TRUE(IsSorted<int>(arr, 0));
}

// Tests whether insertion sort sorts an array consisted of one element.
TEST(InsertSortTest, ArrayHasOneElement) {
    int arr[1] = {1};

    dsa::InsertionSort<int>(arr, 1);

    ASSERT_TRUE(IsSorted<int>(arr, 1));
}

// Test whether insertion sort sorts an array consisted of two elements.

// Tests whether insertion sort sorts an empty vector.
TEST(InsertionSortTest, EmptyVector) {
    std::vector<int> vec;

    dsa::InsertionSort<int>(vec);

    ASSERT_TRUE(IsSorted<int>(vec));
}

// Tests whether insertion sort sorts a vector consisted of one element.
TEST(InsertSortTest, VectorHasOneElement) {
    std::vector<int> vec;
    vec.push_back(123);

    dsa::InsertionSort<int>(vec);

    ASSERT_TRUE(IsSorted<int>(vec));
}

// int main(int argc, char** argv) {
//     testing::InitGoogleTest(&argc, argv);
//     return RUN_ALL_TESTS();
// }

int main() {
    #include <string>

    Random r;

    int i = r.RandomInteger<int>(-100000, 100000);
    double d = r.RandomReal<double>(-100000, 100000);
    char c = r.RandomCharacter();

    std::cout << "i = " << i << std::endl;
    std::cout << "d = " << d << std::endl;
    std::cout << "c = " << c << std::endl << std::endl;

    size_t s1 = r.RandomInteger<size_t>(0, 100000);
    int a1[s1];
    r.RandomIntegerArray<int>(a1, s1, -100000, 100000);

    std::cout << "s1 = " << s1 << std::endl;
    std::cout << "a1[0] = " << a1[0] << std::endl;
    std::cout << "a1[" << s1 / 2 << "] = " << a1[s1 / 2] << std::endl;
    std::cout << "a1[" << s1 - 1 << "] = " << a1[s1 - 1] << std::endl << std::endl;

    size_t s2 = r.RandomInteger<size_t>(0, 100000);
    double a2[s2];
    r.RandomRealArray<double>(a2, s2, -100000, 100000);

    std::cout << "s2 = " << s2 << std::endl;
    std::cout << "a2[0] = " << a2[0] << std::endl;
    std::cout << "a2[" << s2 / 2 << "] = " << a2[s2 / 2] << std::endl;
    std::cout << "a2[" << s2 - 1 << "] = " << a2[s2 - 1] << std::endl << std::endl;

    size_t s3 = r.RandomInteger<size_t>(0, 64);
    char a3[s3];
    r.RandomString(a3, s3);

    std::cout << "s3 = " << s3 << std::endl;
    std::cout << "a3 = ";
    for (size_t i = 0; i < s3; i++) std::cout << a3[i];
    std::cout << std::endl << std::endl;

    size_t s4 = r.RandomInteger<size_t>(0, 64);
    std::string a4 = r.RandomString(s4);

    std::cout << "s4 = " << s4 << std::endl;
    std::cout << "a4 = " << a4 << std::endl << std::endl;

    std::vector<int> v1 = r.RandomIntegerVector<int>(100000, -100000, 100000);
    size_t s5 = v1.size();

    std::cout << "s5 = " << s5 << std::endl;
    std::cout << "v1[0] = " << v1[0] << std::endl;
    std::cout << "v1[" << s5 / 2 << "] = " << v1[s5 / 2] << std::endl;
    std::cout << "v1[" << s5 - 1 << "] = " << v1[s5 - 1] << std::endl << std::endl;

    std::vector<double> v2 = r.RandomRealVector<double>(100000, -100000, 100000);
    size_t s6 = v2.size();

    std::cout << "s6 = " << s6 << std::endl;
    std::cout << "v2[0] = " << v2[0] << std::endl;
    std::cout << "v2[" << s6 / 2 << "] = " << v2[s6 / 2] << std::endl;
    std::cout << "v3[" << s6 - 1 << "] = " << v2[s6 - 1] << std::endl << std::endl;

    std::vector<std::string> v3 = r.RandomStringVector(100000, 64);
    size_t s7 = v3.size();

    std::cout << "s7 = " << s7 << std::endl;
    std::cout << "v3[0] = " << v3[0] << std::endl;
    std::cout << "v3[" << s7 / 2 << "] = " << v3[s7 / 2] << std::endl;
    std::cout << "v3[" << s7 - 1 << "] = " << v3[s7 - 1] << std::endl;

    return 0;
}
