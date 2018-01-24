#include <fstream>
#include "linked_list.hpp"

int main(void) {
    std::ifstream infile("input/linked_list.txt");
    std::ofstream outfile("output/linked_list.txt");

    if (!infile.is_open() || !outfile.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
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

        outfile << "Initial:\n";
        outfile << list;
        outfile << std::endl;

        outfile << "After removing the front node:\n";
        list.PopFront();
        outfile << list;
        outfile << std::endl;

        outfile << "After removing the back node:\n";
        list.PopBack();
        outfile << list;
        outfile << std::endl;

        outfile << "After push 0 to the front:\n";
        list.PushFront(0);
        outfile << list;
        outfile << std::endl;

        outfile << "Front node: " << list.Front() << std::endl;
        outfile << "Back node: " << list.Back() << std::endl << std::endl;

        outfile << "After insert 100 to index 5 "
                << "(or at the back for smaller lists):\n";
        if (list.Size() < 5) {
            list.PushBack(100);
        } else {
            list.Insert(list.Begin() + 5, 100);
        }
        outfile << list;
        outfile << std::endl;

        outfile << "After erase index 5 "
                << "(or at the back for smaller lists):\n";
        if (list.Size() < 6) {
            list.PopBack();
        } else {
            list.Erase(list.Begin() + 5);
        }
        outfile << list;
        outfile << std::endl;

        outfile << "After creating a copy and change the copy to "
                << "1 <-> 2 <-> 3 :\n";
        LinkedList<int> copy = list;
        copy.Clear();
        copy.PushBack(1);
        copy.PushBack(2);
        copy.PushBack(3);
        outfile << "Original list:\n";
        outfile << list;
        outfile << "Copied list:\n";
        outfile << copy;
        outfile << std::endl;

        outfile << "After assigning copy list to original list:\n";
        list = copy;
        outfile << "Original list:\n";
        outfile << list;
        outfile << "Copied list:\n";
        outfile << copy;
        outfile << std::endl << std::endl;
    }

    infile.close();
    outfile.close();

    return 0;
}
