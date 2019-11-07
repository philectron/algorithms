// reader.hpp
//
// Phi Luu
//
// Data Structures and Algorithms
//
// This reader class reads the test data from the file system so the unit tests
// don't have to initialize the data on their own. The reader can help avoid
// repetitive data reading throughout the tests.

#ifndef INCLUDE_READER_HPP_
#define INCLUDE_READER_HPP_

#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

// This class defines how test data is read from files to suitable variables
// and objects.
class Reader {
public:
    // Reads in a single number or string from a file.
    //
    // Params:
    // filename: The name of the file to read the data from
    //
    // Returns:
    // The number or string read from the file
    template <class T>
    T ReadNumString(std::string filename) {
        file_.exceptions(FLAG_BITS_);
        file_.open(DATA_DIR_ + filename);

        // read in a single integer
        T numstring;
        file_ >> numstring;

        file_.close();
        return numstring;
    }

    // Reads in an array from a file.
    //
    // Params:
    // filename: The name of the file to read the data from
    // array: The array to output the read data to
    // size: The size of the array
    template <class T>
    void ReadArray(std::string filename, T* array, size_t size) {
        file_.exceptions(FLAG_BITS_);
        file_.open(DATA_DIR_ + filename);

        // the first line contains the size of the array
        file_ >> size;

        // the second lines contains the elements of the array
        for (size_t i = 0; i < size; i++) file_ >> array[i];

        file_.close();
    }

    // Reads in a vector from a file.
    //
    // Params:
    // filename: The name of the file to read the data from
    //
    // Returns:
    // A vector filled with the data read from the file
    template <class T>
    std::vector<T> ReadVector(std::string filename) {
        file_.exceptions(FLAG_BITS_);
        file_.open(DATA_DIR_ + filename);

        // the first line contains the size of the vector
        size_t size;
        file_ >> size;

        // the second lines contains the elements of the vector
        std::vector<T> vector(size);
        for (size_t i = 0; i < size; i++) file_ >> vector[i];

        file_.close();
        return vector;
    }

private:
    std::ifstream file_;
    static const std::ios_base::iostate FLAG_BITS_;
    static const std::string DATA_DIR_;
};

// must define the static constants of the class outside of its declaration
const std::ios_base::iostate Reader::FLAG_BITS_ = std::ifstream::failbit |
                                                  std::ifstream::badbit;
const std::string Reader::DATA_DIR_ = "data/";

#endif  // #ifndef INCLUDE_READER_HPP_
