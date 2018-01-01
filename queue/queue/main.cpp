#include <fstream>
#include "queue.hpp"

int main(void) {
    std::ifstream testin("testin.txt");
    try {
        if (testin.is_open()) {
            int num_test_cases;
            testin >> num_test_cases;

            for (int t = 0; t < num_test_cases; t++) {
                Queue<int> queue;
                int size;
                testin >> size;

                for (int i = 0; i < size; i++) {
                    int value;
                    testin >> value;
                    queue.Enqueue(value);
                }

                std::cout << "Initial:\n";
                queue.Print();
                std::cout << std::endl;

                std::cout << "After push 0 to back:\n";
                queue.Enqueue(0);
                queue.Print();
                std::cout << std::endl;

                std::cout << "After removing the front node:\n";
                queue.Dequeue();
                queue.Print();
                std::cout << std::endl;

                std::cout << "After push 1 to back:\n";
                queue.Enqueue(1);
                queue.Print();
                std::cout << std::endl;

                std::cout << "After push 2 to back:\n";
                queue.Enqueue(2);
                queue.Print();
                std::cout << std::endl;

                std::cout << "After removing the front node:\n";
                queue.Dequeue();
                queue.Print();
                std::cout << std::endl;

                std::cout << "Front node: " << queue.Front() << std::endl
                          << std::endl;

                std::cout << "After creating a copy and change the copy to "
                          << "1 <-> 2 <-> 3 :\n";
                Queue<int> copy = queue;
                copy.Clear();
                copy.Enqueue(1);
                copy.Enqueue(2);
                copy.Enqueue(3);
                std::cout << "Original queue:\n";
                queue.Print();
                std::cout << "Copied queue:\n";
                copy.Print();
                std::cout << std::endl;

                std::cout << "After assigning copy queue to original queue:\n";
                queue = copy;
                std::cout << "Original queue:\n";
                queue.Print();
                std::cout << "Copied queue:\n";
                copy.Print();
                std::cout << std::endl << std::endl;
            }

            testin.close();
        }
    } catch (const std::out_of_range& e) {
        std::cout << e.what() << std::endl;
    }

    system("pause");
    return 0;
}
