#include "merge_sort.hpp"

#include <fstream>
#include <iostream>
#include <vector>

using algorithm::MergeSort;
using std::endl;

int main() {
    std::ifstream fin("../input/merge_sort.in");
    std::ofstream fou("../output/merge_sort.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    int num_test_cases, n;

    fin >> num_test_cases;

    // sort half of the test cases in arrays
    fou << "Performing merge sort on arrays" << endl << endl;
    for (int t = 1; t <= num_test_cases / 2; t++) {
        fin >> n;
        int array[n];
        for (int i = 0; i < n; i++) fin >> array[i];

        fou << "Before:";
        for (int i = 0; i < n; i++) fou << ' ' << array[i];

        MergeSort<int>(array, n);

        fou << endl << "After :";
        for (int i = 0; i < n; i++) fou << ' ' << array[i];
        fou << endl << endl;
    }

    fou << "==============================" << endl << endl;

    // sort the remaining half of the test cases in vectors
    fou << "Performing merge sort on std::vectors" << endl << endl;
    for (int t = num_test_cases / 2 + 1; t <= num_test_cases; t++) {
        fin >> n;
        std::vector<int> array(n);
        for (int i = 0; i < n; i++) fin >> array[i];

        fou << "Before:";
        for (const auto& value : array) fou << ' ' << value;

        MergeSort<std::vector<int>>(array);

        fou << endl << "After :";
        for (const auto& value : array) fou << ' ' << value;
        fou << endl;
        if (t < num_test_cases - 1) fou << endl;
    }

    fin.close();
    fou.close();

    return 0;
}
