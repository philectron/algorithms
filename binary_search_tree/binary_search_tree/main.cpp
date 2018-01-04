#include "binary_search_tree.hpp"

int main(void) {
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

    tree.Insert(5);
    tree.Insert(2);
    tree.Insert(60);
    tree.Insert(1);
    tree.Insert(3);
    tree.Insert(70);
    tree.Insert(0);
    tree.Insert(4);
    tree.Insert(65);
    tree.Insert(80);
    tree.Insert(75);
    tree.Insert(100);

    std::cout << "\nOriginal tree:\n";
    tree.Print();

    std::cout << "\nAfter removing zero-child node 4:\n";
    tree.Remove(4);
    tree.Print();

    std::cout << "\nAfter removing one-child node 1:\n";
    tree.Remove(1);
    tree.Print();

    std::cout << "\nAfter removing two-child node 70:\n";
    tree.Remove(70);
    tree.Print();

    std::cout << "\nAfter copying the tree and changing the copy:\n";
    BinarySearchTree<int> copy = tree;
    copy.Clear();
    copy.Insert(6);
    copy.Insert(2);
    copy.Insert(8);
    copy.Insert(1);
    copy.Insert(5);
    copy.Insert(3);
    copy.Insert(4);
    std::cout << "    Copied tree:\n";
    copy.Print();
    std::cout << "\n    Original tree:\n";
    tree.Print();

    std::cout << "\nAfter assigning copied tree to original tree:\n";
    tree = copy;
    std::cout << "    Original tree:\n";
    tree.Print();
    std::cout << "\n    Copied tree:\n";
    copy.Print();

    std::cout << "\nMin = " << tree.FindMin() << std::endl;
    std::cout << "Max = " << tree.FindMax() << std::endl;

    if (tree.Contains(4)) {
        std::cout << "\nTree contains 4.\n";
    } else {
        std::cout << "\nTree does not contain 4.\n";
    }

    if (tree.Contains(100)) {
        std::cout << "\nTree contains 100.\n";
    } else {
        std::cout << "\nTree does not contain 100.\n";
    }

    system("pause");
    return 0;
}
