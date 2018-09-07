#include "binary_heap.hpp"

#include <fstream>
#include <iostream>
#include <random>

using std::endl;

int main() {
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
