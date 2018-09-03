#include "avl_tree.hpp"

#include <fstream>
#include <iostream>

using std::endl;

int main() {
    std::ifstream fin("../input/avl_tree.in");
    std::ofstream fou("../output/avl_tree.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        AvlTree<int> avltree;

        fou << "Initial:" << endl << avltree << endl;

        int size, value;
        fin >> size;
        for (int i = 0; i < size; i++) {
            fin >> value;
            avltree.Insert(value);
            fou << "After inserting " << value << ":" << endl << avltree << endl;
        }

        fou << "==========END OF TEST CASE t = " << t << "==========\n\n";
    }

    fin.close();
    fou.close();

    return 0;
}
