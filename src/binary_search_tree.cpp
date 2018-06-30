#include <cstdlib>
#include <ctime>
#include <fstream>
#include <random>
#include <vector>
#include "binary_search_tree.hpp"

using std::endl;

int main() {
    std::ifstream fin("../input/binary_search_tree.in");
    std::ofstream fou("../output/binary_search_tree.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        BinarySearchTree<int> tree;
        fou << tree;
        fou << "\nTry to find min/max in an empty tree:\n";
        fou << "Min = ";
        try {
            fou << tree.FindMin() << endl;
        } catch (const std::out_of_range& e) {
            fou << e.what() << endl;
        };

        fou << "Max = ";
        try {
            fou << tree.FindMax() << endl;
        } catch (const std::out_of_range& e) {
            fou << e.what() << endl;
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

        fou << "\nInitial:\n";
        fou << tree;

        fou << "\nAfter removing a random node: ";
        if (!values.empty()) {
            int index = distr(gen);
            int target = values[index];
            fou << "Removed " << target << endl;
            tree.Remove(target);
            values.erase(values.begin() + index);
            fou << tree;
        }

        if (!values.empty()) {
            fou << "\nMin = " << tree.FindMin() << endl;
            fou << "Max = " << tree.FindMax() << endl;
            int find = values[distr(gen)];
            if (tree.Contains(find)) {
                fou << "\nTree contains " << find << endl;
            } else {
                fou << "\nTree does not have " << find << endl;
            }
        }

        fou << "\nAfter copying the tree and changing the copy:\n";
        BinarySearchTree<int> copy = tree;
        copy.Clear();
        copy.Insert(-20);
        copy.Insert(-5);
        copy.Insert(-18);
        copy.Insert(-1);
        copy.Insert(-50);
        fou << "    Copied tree:\n";
        fou << copy;
        fou << "\n    Original tree:\n";
        fou << tree;

        fou << "\nAfter assigning copied tree to original tree:\n";
        tree = copy;
        fou << "    Original tree:\n";
        fou << tree;
        fou << "\n    Copied tree:\n";
        fou << copy;
    }

    fin.close();
    fou.close();

    return 0;
}
