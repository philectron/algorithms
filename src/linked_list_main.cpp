#include <fstream>
#include <iostream>
#include "linked_list.hpp"

int main(void) {
    std::ifstream infile("testcases/linked_list_input.txt");

    if (!infile.is_open()) {
        std::cerr << "Could not open file." << std::endl;
        return 1;
    }

    int num_test_cases;
    infile >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        LinkedList<int> list;
        int size;
        infile >> size;

        for (int i = 0; i < size; i++) {
            int value;
            infile >> value;
            list.PushBack(value);
        }

        std::cout << "Initial:\n";
        list.Print();
        std::cout << std::endl;

        std::cout << "After removing the front node:\n";
        list.PopFront();
        list.Print();
        std::cout << std::endl;

        std::cout << "After removing the back node:\n";
        list.PopBack();
        list.Print();
        std::cout << std::endl;

        std::cout << "After push 0 to the front:\n";
        list.PushFront(0);
        list.Print();
        std::cout << std::endl;

        std::cout << "Front node: " << list.Front() << std::endl;
        std::cout << "Back node: " << list.Back() << std::endl << std::endl;

        std::cout << "After insert 100 to index 5 "
                  << "(or at the back for smaller lists):\n";
        if (list.Size() < 5) {
            list.PushBack(100);
        } else {
            list.Insert(list.Begin() + 5, 100);
        }
        list.Print();
        std::cout << std::endl;

        std::cout << "After erase index 5 "
                  << "(or at the back for smaller lists):\n";
        if (list.Size() < 6) {
            list.PopBack();
        } else {
            list.Erase(list.Begin() + 5);
        }
        list.Print();
        std::cout << std::endl;

        std::cout << "After creating a copy and change the copy to "
                  << "1 <-> 2 <-> 3 :\n";
        LinkedList<int> copy = list;
        copy.Clear();
        copy.PushBack(1);
        copy.PushBack(2);
        copy.PushBack(3);
        std::cout << "Original list:\n";
        list.Print();
        std::cout << "Copied list:\n";
        copy.Print();
        std::cout << std::endl;

        std::cout << "After assigning copy list to original list:\n";
        list = copy;
        std::cout << "Original list:\n";
        list.Print();
        std::cout << "Copied list:\n";
        copy.Print();
        std::cout << std::endl << std::endl;
    }

    infile.close();

    return 0;
}
