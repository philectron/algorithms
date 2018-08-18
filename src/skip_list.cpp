#include <fstream>
#include "skip_list.hpp"

using std::endl;

int main() {
    // START OF QUICK TEST AREA

    std::cout << "Skip list 1: " << endl;
    SkipList<int> skiplist1;
    skiplist1.TestCoinFlip(3000000);

    std::cout << "Skip list 2: " << endl;
    SkipList<int> skiplist2;
    skiplist2.TestCoinFlip(3000000);

    return 0;

    // END OF QUICK TEST AREA

    std::ifstream fin("../input/skip_list.in");
    std::ofstream fou("../output/skip_list.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {}

    fin.close();
    fou.close();

    return 0;
}
