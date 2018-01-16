#include <fstream>
#include <iostream>
#include "stack.hpp"

int main(void) {
    std::ifstream infile("testcases/stack_input.txt");

    if (!infile.is_open()) {
        std::cerr << "Could not open file." << std::endl;
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

            std::cout << "Initial:\n";
            stack.Print();
            std::cout << std::endl;

            std::cout << "After push 0 to top:\n";
            stack.Push(0);
            stack.Print();
            std::cout << std::endl;

            std::cout << "After removing the top node:\n";
            stack.Pop();
            stack.Print();
            std::cout << std::endl;

            std::cout << "After push 1 to top:\n";
            stack.Push(1);
            stack.Print();
            std::cout << std::endl;

            std::cout << "After push 2 to top:\n";
            stack.Push(2);
            stack.Print();
            std::cout << std::endl;

            std::cout << "After removing the top node:\n";
            stack.Pop();
            stack.Print();
            std::cout << std::endl;

            std::cout << "Top node: " << stack.Top() << std::endl
                      << std::endl;

            std::cout << "After creating a copy and change the copy to "
                      << "1 -> 2 -> 3 :\n";
            Stack<int> copy = stack;
            copy.Clear();
            copy.Push(3);
            copy.Push(2);
            copy.Push(1);
            std::cout << "Original stack:\n";
            stack.Print();
            std::cout << "Copied stack:\n";
            copy.Print();
            std::cout << std::endl;

            std::cout << "After assigning copy stack to original stack:\n";
            stack = copy;
            std::cout << "Original stack:\n";
            stack.Print();
            std::cout << "Copied stack:\n";
            copy.Print();
            std::cout << std::endl << std::endl;
        }

        infile.close();
    } catch (const std::out_of_range& e) {
        std::cout << e.what() << std::endl;
    }

    return 0;
}
