#include "hash_table.hpp"

// Use the class Student as an example
class Student;

int main() {
    // TODO

    return 0;
}

class Student {
public:
    Student(std::string name = "", double gpa = 0.0, int age = 0)
        : name_{name}, gpa_{gpa}, age_{age} {}

    // Must define equality to use the Hash class

    bool operator==(const Student& rhs) const {
        return name_.compare(rhs.name_) == 0;
    }

    bool operator!=(const Student& rhs) const { return !(*this == rhs); }

    // Getters

    const std::string& GetName() const { return name_; }

    std::string GetName() { return name_; }

    const double& GetGpa() const { return gpa_; }

    double GetGpa() { return gpa_; }

    const int& GetAge() const { return age_; }

    int GetAge() { return age_; }

    // Setters

    void SetName(const std::string& new_name) { name_.assign(new_name); }

    void SetGpa(const double& new_gpa) { gpa_ = new_gpa; }

    void SetAge(const int& new_age) { age_ = new_age; }

private:
    std::string name_;
    double gpa_;
    int age_;
};

// Defines the hash function for the Student class
template <>
class Hash<Student> {
public:
    // Hashes using student's name
    size_t operator()(const Student& s) {
        static Hash<std::string> hashfunc;

        return hashfunc(s.GetName());
    }
}
