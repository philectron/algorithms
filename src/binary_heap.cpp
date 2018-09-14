#include "binary_heap.hpp"

#include <fstream>
#include <iostream>
#include <random>

using datastructure::BinaryHeap;
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
    std::uniform_int_distribution<int> rand_int_8_32(8, 32);

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        BinaryHeap<int> heap;

        rng.seed(rd());

        fou << "Initial:" << endl << heap << endl;

        fou << "Trying to remove the highest priority item from the heap:";
        try {
            heap.RemoveMin();
            fou << endl << "Removed" << endl << endl;
        } catch (const std::length_error& e) {
            fou << endl << e.what() << endl << endl;
        }

        int size, value, min_item;
        fin >> size;

        std::vector<int> values;

        for (int i = 0; i < size; i++) {
            fin >> value;
            values.push_back(value);
            heap.Insert(value);
            fou << "After inserting " << value << ":" << endl << heap << endl;
        }

        for (int i = 0; i < 5; i++) {
            int rand_value = rand_int_0_100(rng);
            heap.Insert(rand_value);
            fou << "====After inserting a random values (" << rand_value
                << ") into the binary heap:" << endl << heap << endl;
        }

        int current_min = heap.FindMin();
        fou << "====Highest priority item = " << current_min << endl << endl;

        heap.RemoveMin();
        fou << "====After removing the highest prority item (" << current_min
            << ") from the binary heap:" << endl << heap << endl;

        current_min = heap.FindMin();
        fou << "====New highest priority item = " << current_min << endl;

        heap.RemoveMin(min_item);
        fou << "====After removing the highest prority item and copy value to"
            << "  min_item = " << min_item << endl << heap << endl;

        std::vector<int> heap_vector_form = heap.ToVector();

        fou << "====Exporting heap into a vector:" << endl;
        for (const auto& object : heap_vector_form) fou << ' ' << object;
        fou << endl;

        std::vector<int> random_values;

        for (int i = 0, rand_count = rand_int_8_32(rng); i < rand_count; i++) {
            random_values.push_back(rand_int_0_100(rng));
        }

        fou << "====A random list of values:" << endl;
        for (const auto& object : random_values) fou << ' ' << object;
        fou << endl;

        BinaryHeap<int> heap_from_vector(random_values);

        fou << "====Building a heap from this random list:" << endl
            << heap_from_vector << endl;

        BinaryHeap<int> copyheap = heap;

        fou << "====After creating a copy of the original binary heap:" << endl;
        fou << "  Orignial binary heap:" << endl << heap << endl;
        fou << "  Copied binary heap:" << endl << copyheap << endl;

        copyheap.Clear();
        int rand_int_1 = rand_int_0_100(rng);
        int rand_int_2 = rand_int_0_100(rng);
        int rand_int_3 = rand_int_0_100(rng);
        copyheap.Insert(rand_int_1);
        copyheap.Insert(rand_int_2);
        copyheap.Insert(rand_int_3);
        fou << "====After clearing the copied binary heap and inserting 3 "
            << "random number into the copied binary heap (" << rand_int_1
            << ", " << rand_int_2 << ", " << rand_int_3 << "):" << endl;
        fou << "  Orignial binary heap:" << endl << heap << endl;
        fou << "  Copied binary heap:" << endl << copyheap << endl;

        heap = heap;
        fou << "====After trying to self assign the original binary heap:"
            << endl << heap << endl;

        heap = copyheap;
        fou << "====After assigning the copied binary heap to the original "
            << "binary heap:" << endl;
        fou << "  Orignial binary heap:" << endl << heap << endl;
        fou << "  Copied binary heap:" << endl << copyheap << endl;

        fou << "==========END OF TEST CASE t = " << t << "==========\n";
        if (t < num_test_cases - 1) fou << endl;
    }

    fin.close();
    fou.close();

    return 0;
}
