#include "vector.hpp"

#include <fstream>
#include <iostream>

int main() {
    std::ifstream fin("../input/vector.in");
    std::ofstream fou("../output/vector.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        datastructure::Vector<int> vector;
        int size;
        fin >> size;

        for (int i = 0; i < size; i++) {
            int value;
            fin >> value;
            vector.PushBack(value);
        }

        fou << "Initial:\n";
        fou << vector;
        fou << std::endl;

        try {
            fou << "After removing the front node:\n";
            vector.RemoveAt(0);
            fou << vector << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the front node: " << e.what() << std::endl;
        }

        try {
            fou << "After removing the back node:\n";
            vector.PopBack();
            fou << vector << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the back node: " << e.what() << std::endl;
        }

        fou << "After push 0 to the front:\n";
        vector.InsertAt(0, 0);
        fou << vector << std::endl;

        try {
            fou << "Front node: " << vector.Front() << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not access the front node: " << e.what() << std::endl;
        }

        try {
            fou << "Back node: " << vector.Back() << std::endl << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the back node: " << e.what() << std::endl;
        }

        try {
            fou << "After creating a copy and change the copy to 1 2 3 :\n";
            datastructure::Vector<int> copy = vector;
            copy.Clear();
            copy.PushBack(1);
            copy.PushBack(2);
            copy.PushBack(3);
            fou << "Original vector:\n";
            fou << vector;
            fou << "Copied vector:\n";
            fou << copy;
            fou << std::endl;
        } catch (const std::logic_error& e) {
            fou << "Could not make a copy: " << e.what() << std::endl;
        }

        fou << "----------------------------------------\n\n";
    }

    fin.close();
    fou.close();
}
