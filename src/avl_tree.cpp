#include "avl_tree.hpp"

#include <fstream>
#include <iostream>
#include <random>
#include <vector>

using std::endl;

int main() {
    std::ifstream fin("../input/avl_tree.in");
    std::ofstream fou("../output/avl_tree.ou");

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
        AvlTree<int> avltree;

        rng.seed(rd());

        fou << "Initial:" << endl << avltree << endl;

        int size, value, rand_inside_value, rand_value;
        fin >> size;

        std::uniform_int_distribution<int> rand_index(0, size - 1);
        std::vector<int> values;

        for (int i = 0; i < size; i++) {
            fin >> value;
            values.push_back(value);
            avltree.Insert(value);
            fou << "After inserting " << value << ":" << endl << avltree
                << endl;
        }

        if (size > 0) rand_inside_value = values[rand_index(rng)];
        rand_value = rand_int_0_100(rng);

        fou << "====Searching for a random value known to be in the AVL tree ("
            << rand_inside_value << "):" << endl;
        if (avltree.Contains(rand_inside_value)) {
            fou << "AVL tree contains " << rand_inside_value << endl;
        } else {
            fou << "AVL tree does not contain " << rand_inside_value << endl;
        }
        fou << endl;

        avltree.Remove(rand_inside_value);
        fou << "====After removing an instance of this inside value ("
            << rand_inside_value << ") from the AVL tree:" << endl
            << avltree << endl;

        fou << "====Check again if the AVL tree contains " << rand_inside_value
            << ":" << endl;
        if (avltree.Contains(rand_inside_value)) {
            fou << "AVL tree contains " << rand_inside_value << endl;
        } else {
            fou << "AVL tree does not contain " << rand_inside_value << endl;
        }
        fou << endl;

        for (int i = 0; i < 4; i++) avltree.Insert(rand_value);
        fou << "====After inserting a random value (" << rand_value
            << ") four times into the AVL tree:" << endl << avltree << endl;

        avltree.Remove(rand_value);
        fou << "====After removing an instance of this value ("
            << rand_value << ") from the AVL tree:" << endl
            << avltree << endl;

        fou << "====Check if the AVL tree still contains " << rand_value
            << ":" << endl;
        if (avltree.Contains(rand_value)) {
            fou << "AVL tree contains " << rand_value << endl;
        } else {
            fou << "AVL tree does not contain " << rand_value << endl;
        }
        fou << endl;

        avltree.RemoveAll(rand_value);
        fou << "====After removing all instances of this value ("
            << rand_value << ") from the AVL tree:" << endl
            << avltree << endl;

        fou << "====Check again if the AVL tree still contains " << rand_value
            << ":" << endl;
        if (avltree.Contains(rand_value)) {
            fou << "AVL tree contains " << rand_value << endl;
        } else {
            fou << "AVL tree does not contain " << rand_value << endl;
        }
        fou << endl;

        AvlTree<int> copytree = avltree;

        fou << "====After creating a copy of the original AVL tree:" << endl;
        fou << "  Orignial AVL tree:" << endl << avltree << endl;
        fou << "  Copied AVL tree:" << endl << copytree << endl;

        copytree.Clear();
        int rand_int_1 = rand_int_0_100(rng);
        int rand_int_2 = rand_int_0_100(rng);
        int rand_int_3 = rand_int_0_100(rng);
        copytree.Insert(rand_int_1);
        copytree.Insert(rand_int_2);
        copytree.Insert(rand_int_3);
        fou << "====After clearing the copied AVL tree and inserting 3 random "
            << "number into the copied AVL tree (" << rand_int_1 << ", "
            << rand_int_2 << ", " << rand_int_3 << "):" << endl;
        fou << "  Orignial AVL tree:" << endl << avltree << endl;
        fou << "  Copied AVL tree:" << endl << copytree << endl;

        avltree = avltree;
        fou << "====After trying to self assign the original AVL tree:" << endl
            << avltree << endl;

        avltree = copytree;
        fou << "====After assigning the copied AVL tree to the original "
            << "AVL tree:" << endl;
        fou << "  Orignial AVL tree:" << endl << avltree << endl;
        fou << "  Copied AVL tree:" << endl << copytree << endl;

        fou << "==========END OF TEST CASE t = " << t << "==========\n";
        if (t < num_test_cases - 1) fou << endl;
    }

    fin.close();
    fou.close();

    return 0;
}
