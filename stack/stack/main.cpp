#include <fstream>
#include "stack.hpp"

int main(void) {
    std::ifstream testinput("testinput.txt");
    try {
        if (testinput.is_open()) {
            int num_test_cases;
            testinput >> num_test_cases;

            for (int t = 0; t < num_test_cases; t++) {
                Stack<int> stack;
                int size;
                testinput >> size;

                for (int i = 0; i < size; i++) {
                    int value;
                    testinput >> value;
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

            testinput.close();
        }
    } catch (const std::out_of_range& e) {
        std::cout << e.what() << std::endl;
    }

	system("pause");
    return 0;
}
