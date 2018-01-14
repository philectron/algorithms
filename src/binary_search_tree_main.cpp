#include <cstdlib>
#include <ctime>
#include <fstream>
#include <vector>
#include "binary_search_tree.hpp"

int main(void) {
    std::ifstream infile("testcases/binary_search_tree_input.txt");

    if (!infile.is_open()) {
        std::cerr << "Could not open file." << std::endl;
        return 1;
    }

    int num_test_cases;
    infile >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        BinarySearchTree<int> tree;
        tree.Print();
        std::cout << "\nTry to find min/max in an empty tree:\n";
        std::cout << "Min = ";
        try {
            std::cout << tree.FindMin() << std::endl;
        } catch (const std::out_of_range& e) {
            std::cout << e.what() << std::endl;
        };

        std::cout << "Max = ";
        try {
            std::cout << tree.FindMax() << std::endl;
        } catch (const std::out_of_range& e) {
            std::cout << e.what() << std::endl;
        };

        int size;
        infile >> size;
        std::vector<int> values(size);

        for (int i = 0; i < size; i++) {
            infile >> values[i];
            tree.Insert(values[i]);
        }

        std::cout << "\nInitial:\n";
        tree.Print();

        std::cout << "\nAfter removing a random node: ";
        if (!values.empty()) {
            srand(static_cast<unsigned int>(time(nullptr)));
            int index = rand() % size;
            int target = values[index];
            std::cout << "Removed " << target << std::endl;
            tree.Remove(target);
            values.erase(values.begin() + index);
            tree.Print();
        }

        if (!values.empty()) {
            std::cout << "\nMin = " << tree.FindMin() << std::endl;
            std::cout << "Max = " << tree.FindMax() << std::endl;
            int find = values[rand() % size];
            if (tree.Contains(find)) {
                std::cout << "\nTree contains " << find << std::endl;
            } else {
                std::cout << "\nTree does not have " << find << std::endl;
            }
        }

        std::cout << "\n After copying the tree and changing the copy:\n";
        BinarySearchTree<int> copy = tree;
        copy.Clear();
        copy.Insert(-20);
        copy.Insert(-5);
        copy.Insert(-18);
        copy.Insert(-1);
        copy.Insert(-50);
        std::cout << "    Copied tree:\n";
        copy.Print();
        std::cout << "\n    Original tree:\n";
        tree.Print();

        std::cout << "\n After assigning copied tree to original tree:\n";
        tree = copy;
        std::cout << "    Original tree:\n";
        tree.Print();
        std::cout << "\n    Copied tree:\n";
        copy.Print();
    }

    infile.close();

    return 0;
}
