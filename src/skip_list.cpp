#include <fstream>
#include "skip_list.hpp"

using std::cout;
using std::endl;

int main() {
    // START OF QUICK TEST AREA
    SkipList<int> s;

    s.Insert(1);
    s.Insert(55);
    s.Insert(5);
    s.Insert(10);
    s.Insert(3);
    s.Insert(15);
    s.Insert(23);

    cout << s << endl;

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
