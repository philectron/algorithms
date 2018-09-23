#include "linear_search.hpp"

#include <fstream>
#include <iostream>
#include <random>
#include <vector>

int main() {
    std::ifstream fin("../input/linear_search.in");
    std::ofstream fou("../output/linear_search.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)" << std::endl;
        return 1;
    }

    std::mt19937 rng;
    std::random_device rd;
    std::uniform_int_distribution<int> rand_int_0_20(0, 20);

    int num_test_cases, n;
    fin >> num_test_cases;

    // linear search half of the test cases in arrays
    fou << "Performing linear search on arrays" << std::endl << std::endl;
    for (int t = 0; t < num_test_cases / 2; t++) {
        fin >> n;
        int array[n];
        for (int i = 0; i < n; i++) fin >> array[i];

        rng.seed(rd());

        std::uniform_int_distribution<int> rand_index(0, n - 1);
        int rand_i = rand_index(rng);

        fou << "====Array:" << std::endl;
        for (int i = 0; i < n; i++) {
            fou << array[i];
            if (i < n - 1) fou << ' ';
        }
        fou << std::endl << std::endl;

        int rand_element = array[rand_i];
        fou << "Searching for an element known to be in the array ("
            << rand_element << " at index " << rand_i << ")" << std::endl;
        int search_result = algorithm::LinearSearch(rand_element, array, n);
        if (search_result != -1) {
            fou << "Found at index " << search_result << std::endl;
        } else {
            fou << "Not found" << std::endl;
        }
        fou << std::endl;

        int rand_object = rand_int_0_20(rng);
        fou << "Searching for a random object from 0 to 20 ("
            << rand_object << ")" << std::endl;
        search_result = algorithm::LinearSearch(rand_object, array, n);
        if (search_result != -1) {
            fou << "Found at index " << search_result << std::endl;
        } else {
            fou << "Not found" << std::endl;
        }
        fou << std::endl;
    }

    fou << "==============================" << std::endl << std::endl;

    // linear search half of the test cases in vectors
    fou << "Performing linear search on std::vectors\n\n";
    for (int t = num_test_cases / 2; t < num_test_cases; t++) {
        fin >> n;
        std::vector<int> array(n);
        for (int i = 0; i < n; i++) fin >> array[i];

        rng.seed(rd());

        std::uniform_int_distribution<int> rand_index(0, n - 1);
        int rand_i = rand_index(rng);

        fou << "====Array:" << std::endl;
        for (int i = 0; i < n; i++) {
            fou << array[i];
            if (i < n - 1) fou << ' ';
        }
        fou << std::endl << std::endl;

        int rand_element = array[rand_i];
        fou << "Searching for an element known to be in the array ("
            << rand_element << " at index " << rand_i << ")" << std::endl;
        int search_result = algorithm::LinearSearch(rand_element, array);
        if (search_result != -1) {
            fou << "Found at index " << search_result << std::endl;
        } else {
            fou << "Not found" << std::endl;
        }
        fou << std::endl;

        int rand_object = rand_int_0_20(rng);
        fou << "Searching for a random object from 0 to 20 ("
            << rand_object << ")" << std::endl;
        search_result = algorithm::LinearSearch(rand_object, array);
        if (search_result != -1) {
            fou << "Found at index " << search_result << std::endl;
        } else {
            fou << "Not found" << std::endl;
        }
        if (t < num_test_cases - 1) fou << std::endl;

    }

    fin.close();
    fou.close();

    return 0;
}
