#include "quick_sort.hpp"

#include <fstream>
#include <iostream>
#include <vector>

int main() {
    std::ifstream fin("../input/quick_sort.in");
    std::ofstream fou("../output/quick_sort.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    int num_test_cases, n;

    fin >> num_test_cases;

    // sort half of the test cases in arrays
    fou << "Performing quick sort on arrays" << std::endl << std::endl;
    for (int t = 1; t <= num_test_cases / 2; t++) {
        fin >> n;
        int array[n];
        for (int i = 0; i < n; i++) fin >> array[i];

        fou << "Before:";
        for (int i = 0; i < n; i++) fou << ' ' << array[i];

        algorithm::QuickSort<int>(array, n);

        fou << std::endl << "After :";
        for (int i = 0; i < n; i++) fou << ' ' << array[i];
        fou << std::endl << std::endl;
    }

    fou << "==============================" << std::endl << std::endl;

    // sort the remaining half of the test cases in vectors
    fou << "Performing quick sort on std::vectors" << std::endl << std::endl;
    for (int t = num_test_cases / 2 + 1; t <= num_test_cases; t++) {
        fin >> n;
        std::vector<int> array(n);
        for (int i = 0; i < n; i++) fin >> array[i];

        fou << "Before:";
        for (const auto& value : array) fou << ' ' << value;

        algorithm::QuickSort<int>(array);

        fou << std::endl << "After :";
        for (const auto& value : array) fou << ' ' << value;
        if (t < num_test_cases) fou << std::endl << std::endl;
    }

    fin.close();
    fou.close();
}