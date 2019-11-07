// random.hpp
//
// Phi Luu
//
// Data Structures and Algorithms
//
// This random number generator class provides randomly generated data, such as
// numbers and strings, to be used in unit testing.

#ifndef INCLUDE_RANDOM_HPP_
#define INCLUDE_RANDOM_HPP_

#include <stdexcept>
#include <random>
#include <string>
#include <vector>

// This class provides methods to randomly generate test data.
class Random {
public:
    Random() { rng_.seed(rd_()); }

    // Returns a random integer ranging from  low  to  high  (inclusive).
    template <class T>
    T RandomInteger(T low, T high) {
        std::uniform_int_distribution<T> randomize(low, high);
        return randomize(rng_);
    }

    // Returns a random real number ranging from  low  to  high  (inclusive).
    template <class T>
    T RandomReal(T low, T high) {
        std::uniform_real_distribution<T> randomize(low, high);
        return randomize(rng_);
    }

    // Returns a random character ranging from  low  to  high  (inclusive).
    // The default range is from space to tilde.
    char RandomCharacter(char low = ' ', char high = '~') {
        std::uniform_int_distribution<int> randomize((int)low, (int)high);
        return (char)randomize(rng_);
    }

    // Fills an array with the provided size with random integers ranging
    // from  low  to  high  (inclusive).
    template <class T>
    void RandomIntegerArray(T* array, size_t size, T low, T high) {
        if (!array)
            throw std::length_error("RandomIntegerArray(): Null array");

        for (size_t i = 0; i < size; i++)
            array[i] = RandomInteger<T>(low, high);
    }

    // Fills an array with the provided size with random real numbers ranging
    // from  low  to  high  (inclusive).
    template <class T>
    void RandomRealArray(T* array, size_t size, T low, T high) {
        if (!array)
            throw std::length_error("RandomRealArray(): Null array");

        for (size_t i = 0; i < size; i++)
            array[i] = RandomReal<T>(low, high);
    }

    // Fills a string with the provided size with random characters ranging
    // from  low  to  high  (inclusive).
    // The default range is from space to tilde.
    void RandomString(char* string,
                      size_t length,
                      char low = ' ',
                      char high = '~') {
        if (!string)
            throw std::length_error("RandomString(): Null string");

        for (size_t i = 0; i < length; i++)
            string[i] = RandomCharacter(low, high);

        string[length] = '\0';
    }

    // Returns a string filled with random characters ranging
    // from  low  to  high  (inclusive).
    // The default range is from space to tilde.
    std::string RandomString(size_t length_limit,
                             char low = ' ',
                             char high = '~') {
        std::string string;

        std::uniform_int_distribution<size_t> randomize(0, length_limit);
        for (size_t i = 0, len = randomize(rng_); i < len; i++)
            string += RandomCharacter(low, high);

        return string;
    }

    // Returns a vector filled with integers ranging
    // from  low  to  high  (inclusive).
    template <class T>
    std::vector<T> RandomIntegerVector(size_t size_limit, T low, T high) {
        std::vector<T> vector(RandomInteger<size_t>(0, size_limit));
        for (size_t i = 0, size = vector.size(); i < size; i++)
            vector[i] = RandomInteger<T>(low, high);

        return vector;
    }

    // Returns a vector filled with real numbers ranging
    // from  low  to  high  (inclusive).
    template <class T>
    std::vector<T> RandomRealVector(size_t size_limit, T low, T high) {
        std::vector<T> vector(RandomInteger<size_t>(0, size_limit));
        for (size_t i = 0, size = vector.size(); i < size; i++)
            vector[i] = RandomReal<T>(low, high);

        return vector;
    }

    // Returns a vector filled with string whose characters are randomized
    // ranging from  low  to  high  (inclusive).
    // The default range from is space to tilde.
    std::vector<std::string> RandomStringVector(size_t size_limit,
                                                size_t length_limit,
                                                char low = ' ',
                                                char high = '~') {
        std::vector<std::string> vector(RandomInteger<size_t>(0, size_limit));
        for (size_t i = 0, size = vector.size(); i < size; i++) {
            vector[i] = RandomString(length_limit, low, high);
        }

        return vector;
    }

private:
    static std::mt19937 rng_;
    static std::random_device rd_;
};

std::mt19937 Random::rng_;
std::random_device Random::rd_;

#endif  // #ifndef INCLUDE_RANDOM_HPP_
