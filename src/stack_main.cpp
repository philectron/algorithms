#include <fstream>
#include "stack.hpp"

int main(void) {
    std::ifstream infile("input/stack.txt");
    std::ofstream outfile("output/stack.txt");

    if (!infile.is_open() || !outfile.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    try {
        int num_test_cases;
        infile >> num_test_cases;

        for (int t = 0; t < num_test_cases; t++) {
            Stack<int> stack;
            int size;
            infile >> size;

            for (int i = 0; i < size; i++) {
                int value;
                infile >> value;
                stack.Push(value);
            }

            outfile << "Initial:\n";
            outfile << stack;
            outfile << std::endl;

            outfile << "After push 0 to top:\n";
            stack.Push(0);
            outfile << stack;
            outfile << std::endl;

            outfile << "After removing the top node:\n";
            stack.Pop();
            outfile << stack;
            outfile << std::endl;

            outfile << "After push 1 to top:\n";
            stack.Push(1);
            outfile << stack;
            outfile << std::endl;

            outfile << "After push 2 to top:\n";
            stack.Push(2);
            outfile << stack;
            outfile << std::endl;

            outfile << "After removing the top node:\n";
            stack.Pop();
            outfile << stack;
            outfile << std::endl;

            outfile << "Top node: " << stack.Top() << std::endl
                    << std::endl;

            outfile << "After creating a copy and change the copy to "
                    << "1 -> 2 -> 3 :\n";
            Stack<int> copy = stack;
            copy.Clear();
            copy.Push(3);
            copy.Push(2);
            copy.Push(1);
            outfile << "Original stack:\n";
            outfile << stack;
            outfile << "Copied stack:\n";
            outfile << copy;
            outfile << std::endl;

            outfile << "After assigning copy stack to original stack:\n";
            stack = copy;
            outfile << "Original stack:\n";
            outfile << stack;
            outfile << "Copied stack:\n";
            outfile << copy;
            outfile << std::endl << std::endl;
        }
    } catch (const std::out_of_range& e) {
        outfile << e.what() << std::endl;
    }

    infile.close();
    outfile.close();

    return 0;
}
