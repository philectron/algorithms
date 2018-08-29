#include <fstream>

#include "queue.hpp"

using std::endl;

int main() {
    std::ifstream fin("../input/queue.in");
    std::ofstream fou("../output/queue.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
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

            fou << "Initial:\n";
            fou << queue;
            fou << endl;

            fou << "After push 0 to back:\n";
            queue.Enqueue(0);
            fou << queue;
            fou << endl;

            fou << "After removing the front node:\n";
            queue.Dequeue();
            fou << queue;
            fou << endl;

            fou << "After push 1 to back:\n";
            queue.Enqueue(1);
            fou << queue;
            fou << endl;

            fou << "After push 2 to back:\n";
            queue.Enqueue(2);
            fou << queue;
            fou << endl;

            fou << "After removing the front node:\n";
            queue.Dequeue();
            fou << queue;
            fou << endl;

            fou << "Front node: " << queue.Front() << endl
                 << endl;

            fou << "After creating a copy and change the copy to "
                 << "1 <-> 2 <-> 3 :\n";
            Queue<int> copy = queue;
            copy.Clear();
            copy.Enqueue(1);
            copy.Enqueue(2);
            copy.Enqueue(3);
            fou << "Original queue:\n";
            fou << queue;
            fou << "Copied queue:\n";
            fou << copy;
            fou << endl;

            fou << "After assigning copy queue to original queue:\n";
            queue = copy;
            fou << "Original queue:\n";
            fou << queue;
            fou << "Copied queue:\n";
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
