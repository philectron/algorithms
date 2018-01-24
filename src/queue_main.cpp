#include <fstream>
#include "queue.hpp"

int main(void) {
    std::ifstream infile("input/queue.txt");
    std::ofstream outfile("output/queue.txt");

    if (!infile.is_open() || !outfile.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    try {
        int num_test_cases;
        infile >> num_test_cases;

        for (int t = 0; t < num_test_cases; t++) {
            Queue<int> queue;
            int size;
            infile >> size;

            for (int i = 0; i < size; i++) {
                int value;
                infile >> value;
                queue.Enqueue(value);
            }

            outfile << "Initial:\n";
            outfile << queue;
            outfile << std::endl;

            outfile << "After push 0 to back:\n";
            queue.Enqueue(0);
            outfile << queue;
            outfile << std::endl;

            outfile << "After removing the front node:\n";
            queue.Dequeue();
            outfile << queue;
            outfile << std::endl;

            outfile << "After push 1 to back:\n";
            queue.Enqueue(1);
            outfile << queue;
            outfile << std::endl;

            outfile << "After push 2 to back:\n";
            queue.Enqueue(2);
            outfile << queue;
            outfile << std::endl;

            outfile << "After removing the front node:\n";
            queue.Dequeue();
            outfile << queue;
            outfile << std::endl;

            outfile << "Front node: " << queue.Front() << std::endl
                    << std::endl;

            outfile << "After creating a copy and change the copy to "
                    << "1 <-> 2 <-> 3 :\n";
            Queue<int> copy = queue;
            copy.Clear();
            copy.Enqueue(1);
            copy.Enqueue(2);
            copy.Enqueue(3);
            outfile << "Original queue:\n";
            outfile << queue;
            outfile << "Copied queue:\n";
            outfile << copy;
            outfile << std::endl;

            outfile << "After assigning copy queue to original queue:\n";
            queue = copy;
            outfile << "Original queue:\n";
            outfile << queue;
            outfile << "Copied queue:\n";
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
