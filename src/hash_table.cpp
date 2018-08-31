#include "hash_table.hpp"

// Use the class Student as an example
class Student;

int main() {
    // TODO

// Use the class Student as an example
class Student {
public:
    Student(string firstname = "", string lastname = "", double gpa = 0.0,
            int age = 0)
        : firstname_{firstname},
          lastname_{lastname},
          gpa_{gpa},
          age_{age} {}

    // Must define equality to use the Hash class

    bool operator==(const Student& rhs) const {
        return firstname_.compare(rhs.firstname_) == 0
               && lastname_.compare(rhs.lastname_) == 0;
    }

    bool operator!=(const Student& rhs) const { return !(*this == rhs); }

    // Getters

    string GetFirstName() const { return firstname_; }

    string GetLastName() const { return lastname_; }

    string GetFullName() const { return GetFirstName() + ' ' + GetLastName(); }

    // Rounds GPA to 2 decimal places.
    double GetGpa() const { return round(gpa_ * 100.0) / 100.0; }

    int GetAge() const { return age_; }

    // Setters

    void SetFirstName(const string& firstname) { firstname_.assign(firstname); }

    void SetLastName(const string& lastname) { lastname_.assign(lastname); }

    void SetFullName(const string& firstname, const string& lastname) {
        SetFirstName(firstname);
        SetLastName(lastname);
    }

    void SetGpa(const double& gpa) { gpa_ = gpa; }

    void SetAge(const int& age) { age_ = age; }

    // Print method - for debugging purposes
    friend ostream& operator<<(ostream& out, const Student& student) {
        out << "{ Name: " << student.GetFullName()
            << ", GPA: " << student.GetGpa()
            << ", Age: " << student.GetAge() << " }";
        return out;
    }

private:
    string firstname_;
    string lastname_;
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
