#include <fstream>
#include <iostream>
#include <vector>
#include "bubble_sort.hpp"

using std::endl;

int main(void) {
    std::ifstream fin("../input/bubble_sort.in");
    std::ofstream fou("../output/bubble_sort.ou");

    if ((!fin.is_open()) || (!fou.is_open())) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    int testcases, n;

    fin >> testcases;

    // sort half of the test cases in arrays
    fou << "Bubblesorting arrays: " << endl << endl;
    for (int t = 1; t <= testcases / 2; t++) {
        fin >> n;
        int array[n];
        for (int i = 0; i < n; i++) fin >> array[i];

        fou << "Before: ";
        for (int i = 0; i < n; i++) fou << array[i] << ' ';

        BubbleSort<int>(array, n);

        fou << endl << "After : ";
        for (int i = 0; i < n; i++) fou << array[i] << ' ';
        fou << endl << endl;
    }

    fou << "------------------------------" << endl << endl;

    // sort the remaining half of the test cases in vectors
    fou << "Bubblesorting vectors: " << endl << endl;
    for (int t = testcases / 2 + 1; t <= testcases; t++) {
        fin >> n;
        std::vector<int> array(n);
        for (int i = 0; i < n; i++) fin >> array[i];

        fou << "Before: ";
        for (auto const& value : array) fou << value << ' ';

        BubbleSort<std::vector<int> >(array);

        fou << endl << "After : ";
        for (auto const& value : array) fou << value << ' ';
        fou << endl << endl;
    }

    fin.close();
    fou.close();

    return 0;
}
