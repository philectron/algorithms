#include <fstream>
#include "stack.hpp"

int main(void) {
    std::ifstream fin("../input/stack.txt");
    std::ofstream fout("../output/stack.txt");

    if (!fin.is_open() || !fout.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
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

            fout << "Initial:\n";
            fout << stack;
            fout << std::endl;

            fout << "After push 0 to top:\n";
            stack.Push(0);
            fout << stack;
            fout << std::endl;

            fout << "After removing the top node:\n";
            stack.Pop();
            fout << stack;
            fout << std::endl;

            fout << "After push 1 to top:\n";
            stack.Push(1);
            fout << stack;
            fout << std::endl;

            fout << "After push 2 to top:\n";
            stack.Push(2);
            fout << stack;
            fout << std::endl;

            fout << "After removing the top node:\n";
            stack.Pop();
            fout << stack;
            fout << std::endl;

            fout << "Top node: " << stack.Top() << std::endl
                 << std::endl;

            fout << "After creating a copy and change the copy to "
                 << "1 -> 2 -> 3 :\n";
            Stack<int> copy = stack;
            copy.Clear();
            copy.Push(3);
            copy.Push(2);
            copy.Push(1);
            fout << "Original stack:\n";
            fout << stack;
            fout << "Copied stack:\n";
            fout << copy;
            fout << std::endl;

            fout << "After assigning copy stack to original stack:\n";
            stack = copy;
            fout << "Original stack:\n";
            fout << stack;
            fout << "Copied stack:\n";
            fout << copy;
            fout << std::endl << std::endl;
        }
    } catch (const std::out_of_range& e) {
        fout << e.what() << std::endl;
    }

    fin.close();
    fout.close();

    return 0;
}
