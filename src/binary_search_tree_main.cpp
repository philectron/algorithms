#include <cstdlib>
#include <ctime>
#include <fstream>
#include <vector>
#include "binary_search_tree.hpp"

int main(void) {
    std::ifstream infile("input/binary_search_tree.txt");
    std::ofstream outfile("output/binary_search_tree.txt");

    if (!infile.is_open() || !outfile.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    int num_test_cases;
    infile >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        BinarySearchTree<int> tree;
        outfile << tree;
        outfile << "\nTry to find min/max in an empty tree:\n";
        outfile << "Min = ";
        try {
            outfile << tree.FindMin() << std::endl;
        } catch (const std::out_of_range& e) {
            outfile << e.what() << std::endl;
        };

        outfile << "Max = ";
        try {
            outfile << tree.FindMax() << std::endl;
        } catch (const std::out_of_range& e) {
            outfile << e.what() << std::endl;
        };

        int size;
        infile >> size;
        std::vector<int> values(size);

        for (int i = 0; i < size; i++) {
            infile >> values[i];
            tree.Insert(values[i]);
        }

        outfile << "\nInitial:\n";
        outfile << tree;

        outfile << "\nAfter removing a random node: ";
        if (!values.empty()) {
            srand(static_cast<unsigned int>(time(nullptr)));
            int index = rand() % size;
            int target = values[index];
            outfile << "Removed " << target << std::endl;
            tree.Remove(target);
            values.erase(values.begin() + index);
            outfile << tree;
        }

        if (!values.empty()) {
            outfile << "\nMin = " << tree.FindMin() << std::endl;
            outfile << "Max = " << tree.FindMax() << std::endl;
            int find = values[rand() % size];
            if (tree.Contains(find)) {
                outfile << "\nTree contains " << find << std::endl;
            } else {
                outfile << "\nTree does not have " << find << std::endl;
            }
        }

        outfile << "\nAfter copying the tree and changing the copy:\n";
        BinarySearchTree<int> copy = tree;
        copy.Clear();
        copy.Insert(-20);
        copy.Insert(-5);
        copy.Insert(-18);
        copy.Insert(-1);
        copy.Insert(-50);
        outfile << "    Copied tree:\n";
        outfile << copy;
        outfile << "\n    Original tree:\n";
        outfile << tree;

        outfile << "\nAfter assigning copied tree to original tree:\n";
        tree = copy;
        outfile << "    Original tree:\n";
        outfile << tree;
        outfile << "\n    Copied tree:\n";
        outfile << copy;
    }

    infile.close();
    outfile.close();

    return 0;
}
