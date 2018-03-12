#include <cstdlib>
#include <ctime>
#include <fstream>
#include <random>
#include <vector>
#include "binary_search_tree.hpp"

int main(void) {
    std::ifstream fin("../input/binary_search_tree.txt");
    std::ofstream fout("../output/binary_search_tree.txt");

    if (!fin.is_open() || !fout.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        BinarySearchTree<int> tree;
        fout << tree;
        fout << "\nTry to find min/max in an empty tree:\n";
        fout << "Min = ";
        try {
            fout << tree.FindMin() << std::endl;
        } catch (const std::out_of_range& e) {
            fout << e.what() << std::endl;
        };

        fout << "Max = ";
        try {
            fout << tree.FindMax() << std::endl;
        } catch (const std::out_of_range& e) {
            fout << e.what() << std::endl;
        };

        int size;
        fin >> size;
        std::vector<int> values(size);

        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_int_distribution<int> distr(0, size - 1);

        for (int i = 0; i < size; i++) {
            fin >> values[i];
            tree.Insert(values[i]);
        }

        fout << "\nInitial:\n";
        fout << tree;

        fout << "\nAfter removing a random node: ";
        if (!values.empty()) {
            int index = distr(gen);
            int target = values[index];
            fout << "Removed " << target << std::endl;
            tree.Remove(target);
            values.erase(values.begin() + index);
            fout << tree;
        }

        if (!values.empty()) {
            fout << "\nMin = " << tree.FindMin() << std::endl;
            fout << "Max = " << tree.FindMax() << std::endl;
            int find = values[distr(gen)];
            if (tree.Contains(find)) {
                fout << "\nTree contains " << find << std::endl;
            } else {
                fout << "\nTree does not have " << find << std::endl;
            }
        }

        fout << "\nAfter copying the tree and changing the copy:\n";
        BinarySearchTree<int> copy = tree;
        copy.Clear();
        copy.Insert(-20);
        copy.Insert(-5);
        copy.Insert(-18);
        copy.Insert(-1);
        copy.Insert(-50);
        fout << "    Copied tree:\n";
        fout << copy;
        fout << "\n    Original tree:\n";
        fout << tree;

        fout << "\nAfter assigning copied tree to original tree:\n";
        tree = copy;
        fout << "    Original tree:\n";
        fout << tree;
        fout << "\n    Copied tree:\n";
        fout << copy;
    }

    fin.close();
    fout.close();

    return 0;
}
