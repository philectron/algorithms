#include "linked_list.hpp"

#include <fstream>

int main() {
    std::ifstream fin("../input/linked_list.in");
    std::ofstream fou("../output/linked_list.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        datastructure::LinkedList<int> list;
        int size;
        fin >> size;

        for (int i = 0; i < size; i++) {
            int value;
            fin >> value;
            list.PushBack(value);
        }

        fou << "Initial:\n";
        fou << list;
        fou << std::endl;

        fou << "After removing the front node:\n";
        list.PopFront();
        fou << list;
        fou << std::endl;

        fou << "After removing the back node:\n";
        list.PopBack();
        fou << list;
        fou << std::endl;

        fou << "After push 0 to the front:\n";
        list.PushFront(0);
        fou << list;
        fou << std::endl;

        fou << "Front node: " << list.Front() << std::endl;
        fou << "Back node: " << list.Back() << std::endl << std::endl;

        fou << "After insert 100 to index 5 "
            << "(or at the back for smaller lists):\n";
        if (list.Size() < 5) {
            list.PushBack(100);
        } else {
            list.Insert(list.Begin() + 5, 100);
        }
        fou << list;
        fou << std::endl;

        fou << "After erase index 5 "
            << "(or at the back for smaller lists):\n";
        if (list.Size() < 6) {
            list.PopBack();
        } else {
            list.Erase(list.Begin() + 5);
        }
        fou << list;
        fou << std::endl;

        fou << "After creating a copy and change the copy to "
            << "1 <-> 2 <-> 3 :\n";
        datastructure::LinkedList<int> copy = list;
        copy.Clear();
        copy.PushBack(1);
        copy.PushBack(2);
        copy.PushBack(3);
        fou << "Original list:\n";
        fou << list;
        fou << "Copied list:\n";
        fou << copy;
        fou << std::endl;

        fou << "After assigning copy list to original list:\n";
        list = copy;
        fou << "Original list:\n";
        fou << list;
        fou << "Copied list:\n";
        fou << copy;
        fou << std::endl << std::endl;
    }

    fin.close();
    fou.close();

    return 0;
}
