#include "binary_heap.hpp"

#include <fstream>
#include <iostream>
#include <random>

int main() {
    std::ifstream fin("../input/binary_heap.in");
    std::ofstream fou("../output/binary_heap.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    std::mt19937 rng;
    std::random_device rd;
    std::uniform_int_distribution<int> rand_int_0_100(0, 100);
    std::uniform_int_distribution<int> rand_int_8_32(8, 32);

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        datastructure::BinaryHeap<int> heap;

        rng.seed(rd());

        fou << "Initial:" << std::endl << heap << std::endl;

        fou << "Trying to remove the highest priority item from the heap:";
        try {
            heap.RemoveMin();
            fou << std::endl << "Removed" << std::endl << std::endl;
        } catch (const std::length_error& e) {
            fou << std::endl << e.what() << std::endl << std::endl;
        }

        int size, value, min_item;
        fin >> size;

        std::vector<int> values;

        for (int i = 0; i < size; i++) {
            fin >> value;
            values.push_back(value);
            heap.Insert(value);
            fou << "After inserting " << value << ":" << std::endl << heap
                << std::endl;
        }

        for (int i = 0; i < 5; i++) {
            int rand_value = rand_int_0_100(rng);
            heap.Insert(rand_value);
            fou << "====After inserting a random values (" << rand_value
                << ") into the binary heap:" << std::endl << heap << std::endl;
        }

        int current_min = heap.FindMin();
        fou << "====Highest priority item = " << current_min << std::endl
            << std::endl;

        heap.RemoveMin();
        fou << "====After removing the highest prority item (" << current_min
            << ") from the binary heap:" << std::endl << heap << std::endl;

        current_min = heap.FindMin();
        fou << "====New highest priority item = " << current_min << std::endl;

        heap.RemoveMin(min_item);
        fou << "====After removing the highest prority item and copy value to"
            << "  min_item = " << min_item << std::endl << heap << std::endl;

        std::vector<int> heap_vector_form = heap.ToVector();

        fou << "====Exporting heap into a vector:" << std::endl;
        for (const auto& object : heap_vector_form) fou << ' ' << object;
        fou << std::endl;

        std::vector<int> random_values;

        for (int i = 0, rand_count = rand_int_8_32(rng); i < rand_count; i++) {
            random_values.push_back(rand_int_0_100(rng));
        }

        fou << "====A random list of values:" << std::endl;
        for (const auto& object : random_values) fou << ' ' << object;
        fou << std::endl;

        datastructure::BinaryHeap<int> heap_from_vector(random_values);

        fou << "====Building a heap from this random list:" << std::endl
            << heap_from_vector << std::endl;

        datastructure::BinaryHeap<int> copyheap = heap;

        fou << "====After creating a copy of the original binary heap:\n";
        fou << "  Orignial binary heap:" << std::endl << heap << std::endl;
        fou << "  Copied binary heap:" << std::endl << copyheap << std::endl;

        copyheap.Clear();
        int rand_int_1 = rand_int_0_100(rng);
        int rand_int_2 = rand_int_0_100(rng);
        int rand_int_3 = rand_int_0_100(rng);
        copyheap.Insert(rand_int_1);
        copyheap.Insert(rand_int_2);
        copyheap.Insert(rand_int_3);
        fou << "====After clearing the copied binary heap and inserting 3 "
            << "random number into the copied binary heap (" << rand_int_1
            << ", " << rand_int_2 << ", " << rand_int_3 << "):" << std::endl;
        fou << "  Orignial binary heap:" << std::endl << heap << std::endl;
        fou << "  Copied binary heap:" << std::endl << copyheap << std::endl;

        heap = heap;
        fou << "====After trying to self assign the original binary heap:"
            << std::endl << heap << std::endl;

        heap = copyheap;
        fou << "====After assigning the copied binary heap to the original "
            << "binary heap:" << std::endl;
        fou << "  Orignial binary heap:" << std::endl << heap << std::endl;
        fou << "  Copied binary heap:" << std::endl << copyheap << std::endl;

        fou << "==========END OF TEST CASE t = " << t << "==========\n";
        if (t < num_test_cases - 1) fou << std::endl;
    }

    fin.close();
    fou.close();

    return 0;
}
