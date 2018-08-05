#include <fstream>
#include <iostream>
#include <random>
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

        fou << "After removing the front node:\n";
        vector.RemoveAt(0);
        fou << vector;
        fou << endl;

        fou << "After removing the back node:\n";
        vector.PopBack();
        fou << vector;
        fou << endl;

        fou << "After push 0 to the front:\n";
        vector.InsertAt(0);
        fou << vector;
        fou << endl;

        fou << "Front node: " << vector.Front() << endl;
        fou << "Back node: " << vector.Back() << endl << endl;

        fou << "After insert 100 to index 5 "
            << "(or at the back for smaller vectors):\n";
        if (vector.Size() < 5) {
            vector.PushBack(100);
        } else {
            vector.Insert(vector.Begin() + 5, 100);
        }
        fou << vector;
        fou << endl;

        fou << "After erase index 5 "
            << "(or at the back for smaller vectors):\n";
        if (vector.Size() < 6) {
            vector.PopBack();
        } else {
            vector.Erase(vector.Begin() + 5);
        }
        fou << vector;
        fou << endl;

        fou << "After creating a copy and change the copy to "
            << "1 <-> 2 <-> 3 :\n";
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

        fou << "After assigning copy vector to original vector:\n";
        vector = copy;
        fou << "Original vector:\n";
        fou << vector;
        fou << "Copied vector:\n";
        fou << copy;
        fou << endl << endl;
 
    }

    fin.close();
    fou.close();
}
