#include <fstream>
#include "queue.hpp"

int main(void) {
    std::ifstream fin("../input/queue.txt");
    std::ofstream fout("../output/queue.txt");

    if (!fin.is_open() || !fout.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    try {
        int num_test_cases;
        fin >> num_test_cases;

        for (int t = 0; t < num_test_cases; t++) {
            Queue<int> queue;
            int size;
            fin >> size;

            for (int i = 0; i < size; i++) {
                int value;
                fin >> value;
                queue.Enqueue(value);
            }

            fout << "Initial:\n";
            fout << queue;
            fout << std::endl;

            fout << "After push 0 to back:\n";
            queue.Enqueue(0);
            fout << queue;
            fout << std::endl;

            fout << "After removing the front node:\n";
            queue.Dequeue();
            fout << queue;
            fout << std::endl;

            fout << "After push 1 to back:\n";
            queue.Enqueue(1);
            fout << queue;
            fout << std::endl;

            fout << "After push 2 to back:\n";
            queue.Enqueue(2);
            fout << queue;
            fout << std::endl;

            fout << "After removing the front node:\n";
            queue.Dequeue();
            fout << queue;
            fout << std::endl;

            fout << "Front node: " << queue.Front() << std::endl
                 << std::endl;

            fout << "After creating a copy and change the copy to "
                 << "1 <-> 2 <-> 3 :\n";
            Queue<int> copy = queue;
            copy.Clear();
            copy.Enqueue(1);
            copy.Enqueue(2);
            copy.Enqueue(3);
            fout << "Original queue:\n";
            fout << queue;
            fout << "Copied queue:\n";
            fout << copy;
            fout << std::endl;

            fout << "After assigning copy queue to original queue:\n";
            queue = copy;
            fout << "Original queue:\n";
            fout << queue;
            fout << "Copied queue:\n";
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
