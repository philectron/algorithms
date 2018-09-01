#include "avl_tree.hpp"

#include <fstream>
#include <iostream>

using std::endl;

int main() {
    std::ifstream fin("../input/avl_tree.in");
    std::ofstream fou("../output/avl_tree.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }


    fin.close();
    fou.close();

    return 0;
}
