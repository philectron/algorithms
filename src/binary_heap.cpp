#include "binary_heap.hpp"

#include <fstream>
#include <iostream>
#include <random>

using std::endl;

int main() {
    // <QUICK TEST>

    // std::cout << "array_.size() = " << array_.size() << endl;
    // std::cout << "array_.capacity() = " << array_.capacity() << endl;
    // std::cout << "array_.empty() = " << array_.empty() << endl;
    // if (!array_.empty()) {
    //     for (const auto& object : array_) std::cout << object << ' ';
    //     std::cout << endl;
    // }

    BinaryHeap<int> h(8);
    std::cout << h << endl;

    h.Insert(13);
    std::cout << h << endl;

    h.Insert(21);
    std::cout << h << endl;

    h.Insert(19);
    std::cout << h << endl;

    h.Insert(16);
    std::cout << h << endl;

    h.Insert(68);
    std::cout << h << endl;

    h.Insert(31);
    std::cout << h << endl;

    h.Insert(65);
    std::cout << h << endl;

    h.Insert(32);
    std::cout << h << endl;

    h.Insert(24);
    std::cout << h << endl;

    h.Insert(26);
    std::cout << h << endl;

    h.Insert(5);
    std::cout << h << endl;

    h.Insert(1);
    std::cout << "Final Insert" << endl << h << endl;

    int min_item = 0;

    std::cout << "Before RemoveMin, Min item = " << min_item << endl;

    h.RemoveMin(min_item);
    std::cout << "After RemoveMin" << endl << h << endl;
    std::cout << "Min item = " << min_item << endl;

    h.RemoveMin(min_item);
    std::cout << "After RemoveMin" << endl << h << endl;
    std::cout << "Min item = " << min_item << endl;

    return 0;
    // </QUICK TEST>
    std::ifstream fin("../input/binary_heap.in");
    std::ofstream fou("../output/binary_heap.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    std::mt19937 rng;
    std::random_device rd;
    std::uniform_int_distribution<int> rand_int_0_100(0, 100);

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        BinaryHeap<int> heap;
    }

    fin.close();
    fou.close();

    return 0;
}
