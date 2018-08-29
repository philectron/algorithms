#include <fstream>
#include <iostream>

#include "vector.hpp"

using std::endl;

int main() {
    std::ifstream fin("../input/vector.in");
    std::ofstream fou("../output/vector.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        Vector<int> vector;
        int size;
        fin >> size;

        for (int i = 0; i < size; i++) {
            int value;
            fin >> value;
            vector.PushBack(value);
        }

        fou << "Initial:\n";
        fou << vector;
        fou << endl;

        try {
            fou << "After removing the front node:\n";
            vector.RemoveAt(0);
            fou << vector << endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the front node: " << e.what() << endl;
        }

        try {
            fou << "After removing the back node:\n";
            vector.PopBack();
            fou << vector << endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the back node: " << e.what() << endl;
        }

        fou << "After push 0 to the front:\n";
        vector.InsertAt(0, 0);
        fou << vector << endl;

        try {
            fou << "Front node: " << vector.Front() << endl;
        } catch (const std::logic_error& e) {
            fou << "Could not access the front node: " << e.what() << endl;
        }

        try {
            fou << "Back node: " << vector.Back() << endl << endl;
        } catch (const std::logic_error& e) {
            fou << "Could not remove the back node: " << e.what() << endl;
        }

        try {
            fou << "After creating a copy and change the copy to 1 2 3 :\n";
            Vector<int> copy = vector;
            copy.Clear();
            copy.PushBack(1);
            copy.PushBack(2);
            copy.PushBack(3);
            fou << "Original vector:\n";
            fou << vector;
            fou << "Copied vector:\n";
            fou << copy;
            fou << endl;
        } catch (const std::logic_error& e) {
            fou << "Could not make a copy: " << e.what() << endl;
        }

        fou << "----------------------------------------" << endl << endl;
    }

    fin.close();
    fou.close();
}
