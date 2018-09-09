#include "binary_heap.hpp"

#include <fstream>
#include <iostream>
#include <random>

using std::endl;

int main() {
    // <QUICK TEST>
    std::vector<int> vector;
    vector.push_back(13);
    vector.push_back(21);
    vector.push_back(16);
    vector.push_back(24);
    vector.push_back(31);
    vector.push_back(19);
    vector.push_back(68);
    vector.push_back(65);
    vector.push_back(26);
    vector.push_back(32);
    BinaryHeap<int> heap(vector);
    BinaryHeap<int> heap2;
    BinaryHeap<int> heap3 = heap;
    heap3 = heap3;
    heap2 = heap3;

    std::vector<int> v1 = heap2.ToVector();
    for (const auto& object : v1) std::cout << object << ' ';
    std::cout << endl;

    std::cout << heap2 << endl;

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
