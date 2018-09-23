#include "deque.hpp"

#include <fstream>
#include <iostream>

int main() {
    std::ifstream fin("../input/deque.in");
    std::ofstream fou("../output/deque.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        datastructure::Deque<int> deque;
        int size;
        fin >> size;
        int input_array[size];

        for (int i = 0; i < size; i++) fin >> input_array[i];

        // push the same data to back and then to front
        for (int i = 0; i < size; i++) deque.PushBack(input_array[i]);
        for (int i = 0; i < size; i++) deque.PushFront(input_array[i]);

        fou << "Initial:\n";
        fou << deque;
        fou << std::endl;

        try {
            fou << "After removing the front node (" << deque.Front() << "):\n";
            deque.PopFront();
            fou << deque << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the front node: " << e.what() << std::endl;
        }

        try {
            fou << "After removing the back node (" << deque.Back() << "):\n";
            deque.PopBack();
            fou << deque << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the back node: " << e.what() << std::endl;
        }

        fou << "After push 0 to the front:\n";
        deque.PushFront(0);
        fou << deque << std::endl;

        try {
            fou << "Front node: " << deque.Front() << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not access the front node: " << e.what() << std::endl;
        }

        try {
            fou << "Back node: " << deque.Back() << std::endl << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the back node: " << e.what() << std::endl;
        }

        try {
            fou << "After creating a copy and change the copy to 1 2 3 :\n";
            datastructure::Deque<int> copy = deque;
            copy.Clear();
            copy.PushBack(1);
            copy.PushBack(2);
            copy.PushBack(3);
            fou << "Original deque:\n";
            fou << deque;
            fou << "Copied deque:\n";
            fou << copy;
            fou << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not make a copy: " << e.what() << std::endl;
        }

        fou << "========================================" << std::endl;
        if (t < num_test_cases - 1) fou << std::endl;
    }

    fin.close();
    fou.close();
}
