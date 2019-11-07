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
#include <vector>

// This class provides methods to randomly generate test data.
class Random {
public:
    Random() { rng_.seed(rd_()); }

    // Returns a random integer ranging from  low  to  high  (inclusive).
    template <class T>
    T RandomInteger(const T& low, const T& high) {
        std::uniform_int_distribution<T> randomize(low, high);
        return randomize(rng_);
    }

    // Returns a random real number ranging from  low  to  high  (inclusive).
    template <class T>
    T RandomReal(const T& low, const T& high) {
        std::uniform_real_distribution<T> randomize(low, high);
        return randomize(rng_);
    }

    // Fills an array with the provided size with integers ranging
    // from  low  to  high  (inclusive).
    template <class T>
    void RandomIntegerArray(T* array, size_t size, const T& low, const T& high) {
        if (!array)
            throw std::length_error("RandomIntegerArray(): Null array");

        std::uniform_int_distribution<T> randomize(low, high);
        for (size_t i = 0; i < size; i++)
            array[i] = randomize(rng_);

    }

    // Fills an array with the provided size with real numbers ranging
    // from  low  to  high  (inclusive).
    template <class T>
    void RandomRealArray(T* array, size_t size, const T& low, const T& high) {
        if (!array)
            throw std::length_error("RandomRealArray(): Null array");

        std::uniform_real_distribution<T> randomize(low, high);
        for (size_t i = 0; i < size; i++)
            array[i] = randomize(rng_);
    }

    // Returns a vector filled with integers ranging
    // from  low  to  high  (inclusive).
    template <class T>
    std::vector<T> RandomIntegerVector(const size_t& size_limit,
                                       const T& low,
                                       const T& high) {
        std::uniform_int_distribution<size_t> randomize_size(0, size_limit);
        std::uniform_int_distribution<T> randomize(low, high);

        std::vector<T> vector(randomize_size(rng_));
        for (size_t i = 0, size = vector.size(); i < size; i++)
            vector[i] = randomize(rng_);

        return vector;
    }

    // Returns a vector filled with real numbers ranging
    // from  low  to  high  (inclusive).
    template <class T>
    std::vector<T> RandomRealVector(const size_t& size_limit,
                                    const T& low,
                                    const T& high) {
        std::uniform_int_distribution<size_t> randomize_size(0, size_limit);
        std::uniform_real_distribution<T> randomize(low, high);

        std::vector<T> vector(randomize_size(rng_));
        for (size_t i = 0, size = vector.size(); i < size; i++)
            vector[i] = randomize(rng_);

        return vector;
    }

private:
    static std::mt19937 rng_;
    static std::random_device rd_;
};

std::mt19937 Random::rng_;
std::random_device Random::rd_;

#endif  // #ifndef INCLUDE_RANDOM_HPP_
