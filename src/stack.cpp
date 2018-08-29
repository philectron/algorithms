#include <fstream>

#include "stack.hpp"

using std::endl;

int main() {
    std::ifstream fin("../input/stack.in");
    std::ofstream fou("../output/stack.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    try {
        int num_test_cases;
        fin >> num_test_cases;

        for (int t = 0; t < num_test_cases; t++) {
            Stack<int> stack;
            int size;
            fin >> size;

            for (int i = 0; i < size; i++) {
                int value;
                fin >> value;
                stack.Push(value);
            }

            fou << "Initial:\n";
            fou << stack;
            fou << endl;

            fou << "After push 0 to top:\n";
            stack.Push(0);
            fou << stack;
            fou << endl;

            fou << "After removing the top node:\n";
            stack.Pop();
            fou << stack;
            fou << endl;

            fou << "After push 1 to top:\n";
            stack.Push(1);
            fou << stack;
            fou << endl;

            fou << "After push 2 to top:\n";
            stack.Push(2);
            fou << stack;
            fou << endl;

            fou << "After removing the top node:\n";
            stack.Pop();
            fou << stack;
            fou << endl;

            fou << "Top node: " << stack.Top() << endl
                 << endl;

            fou << "After creating a copy and change the copy to "
                 << "1 -> 2 -> 3 :\n";
            Stack<int> copy = stack;
            copy.Clear();
            copy.Push(3);
            copy.Push(2);
            copy.Push(1);
            fou << "Original stack:\n";
            fou << stack;
            fou << "Copied stack:\n";
            fou << copy;
            fou << endl;

            fou << "After assigning copy stack to original stack:\n";
            stack = copy;
            fou << "Original stack:\n";
            fou << stack;
            fou << "Copied stack:\n";
            fou << copy;
            fou << endl << endl;
        }
    } catch (const std::out_of_range& e) {
        fou << e.what() << endl;
    }

    fin.close();
    fou.close();

    return 0;
}
