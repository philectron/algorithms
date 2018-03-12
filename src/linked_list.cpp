#include <fstream>
#include "linked_list.hpp"

int main(void) {
    std::ifstream fin("../input/linked_list.txt");
    std::ofstream fout("../output/linked_list.txt");

    if (!fin.is_open() || !fout.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        LinkedList<int> list;
        int size;
        fin >> size;

        for (int i = 0; i < size; i++) {
            int value;
            fin >> value;
            list.PushBack(value);
        }

        fout << "Initial:\n";
        fout << list;
        fout << std::endl;

        fout << "After removing the front node:\n";
        list.PopFront();
        fout << list;
        fout << std::endl;

        fout << "After removing the back node:\n";
        list.PopBack();
        fout << list;
        fout << std::endl;

        fout << "After push 0 to the front:\n";
        list.PushFront(0);
        fout << list;
        fout << std::endl;

        fout << "Front node: " << list.Front() << std::endl;
        fout << "Back node: " << list.Back() << std::endl << std::endl;

        fout << "After insert 100 to index 5 "
             << "(or at the back for smaller lists):\n";
        if (list.Size() < 5) {
            list.PushBack(100);
        } else {
            list.Insert(list.Begin() + 5, 100);
        }
        fout << list;
        fout << std::endl;

        fout << "After erase index 5 "
             << "(or at the back for smaller lists):\n";
        if (list.Size() < 6) {
            list.PopBack();
        } else {
            list.Erase(list.Begin() + 5);
        }
        fout << list;
        fout << std::endl;

        fout << "After creating a copy and change the copy to "
             << "1 <-> 2 <-> 3 :\n";
        LinkedList<int> copy = list;
        copy.Clear();
        copy.PushBack(1);
        copy.PushBack(2);
        copy.PushBack(3);
        fout << "Original list:\n";
        fout << list;
        fout << "Copied list:\n";
        fout << copy;
        fout << std::endl;

        fout << "After assigning copy list to original list:\n";
        list = copy;
        fout << "Original list:\n";
        fout << list;
        fout << "Copied list:\n";
        fout << copy;
        fout << std::endl << std::endl;
    }

    fin.close();
    fout.close();

    return 0;
}
