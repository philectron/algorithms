#include "hash_table.hpp"

#include <cfloat>
#include <cmath>
#include <fstream>
#include <random>
#include <vector>

using std::endl;
using std::ostream;
using std::string;

// Use the class Student as an example
class Student {
public:
    Student(string firstname = "", string lastname = "", double gpa = 0.0,
            int age = 0)
        : firstname_{firstname},
          lastname_{lastname},
          gpa_{gpa},
          age_{age} {}

    Student(const Student& rhs) : gpa_{rhs.gpa_}, age_{rhs.age_} {
        SetFullName(rhs.firstname_, rhs.lastname_);
    }

    Student& operator=(const Student& rhs) {
        if (this != &rhs) {
            SetFullName(rhs.firstname_, rhs.lastname_);
            SetGpa(rhs.gpa_);
            SetAge(rhs.age_);
        }
        return *this;
    }

    // Must define equality to use the Hash class

    // Two Students equal if everything is the same.
    bool operator==(const Student& rhs) const {
        return firstname_.compare(rhs.firstname_) == 0
               && lastname_.compare(rhs.lastname_) == 0
               && abs(gpa_ - rhs.gpa_) < DBL_EPSILON
               && age_ == rhs.age_;
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
            << ", GPA: " << student.GetGpa() << ", Age: " << student.GetAge()
            << " }";
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
    size_t operator()(const Student& student) {
        static Hash<string> hashfunc;

        return hashfunc(student.GetFullName());
    }
};

void CreateCsvTestFile(std::ifstream& infile, std::ofstream& outfile);

int main() {
    std::ifstream fin("../input/hash_table.in");
    std::ofstream fou("../output/hash_table.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    HashTable<Student> hashtable;
    vector<Student> students;

    // read the CSV in  fin  row by row
    string line;
    enum CSV_COLUMNS {FIRSTNAME, LASTNAME, GPA, AGE};
    const int NUM_COLUMNS = 4;
    int line_num = 0;
    while (std::getline(fin, line)) {
        // split the CSV lines
        // format: FirstName,LastName,GPA,Age
        string columns[NUM_COLUMNS];
        int i = 0;
        for (char ch : line) {
            if (ch != ',') columns[i] += ch;
            else i++;
        }
        // use the columns with the above order to instantiate Student objects
        Student new_student(columns[FIRSTNAME], columns[LASTNAME],
                            std::stod(columns[GPA]), std::stoi(columns[AGE]));
        students.push_back(new_student);
        bool success = hashtable.Insert(new_student);
        if (!success) fou << "Failed to Insert() at line " << line_num << endl;
        line_num++;
    }

    std::mt19937 rng;
    std::random_device rd;
    std::uniform_int_distribution<int> rand_student(0, students.size() - 1);
    rng.seed(rd());

    fou << "====Post-read print:" << endl << hashtable << endl;

    fou << "====Checking if a random object is in the hash table:" << endl;
    Student rand_student_from_list = students[rand_student(rng)];
    if (hashtable.Contains(rand_student_from_list)) {
        fou << "Hash table contains " << rand_student_from_list << endl;
    } else {
        fou << "Hash table does not contain " << rand_student_from_list << endl;
    }
    fou << endl;

    fou << "====Removing this random object from the hash table:" << endl;
    if (hashtable.Remove(rand_student_from_list)) {
        fou << "Successfully removed " << rand_student_from_list << endl;
    } else {
        fou << "Failed to remove " << rand_student_from_list << endl;
    }
    fou << endl;

    fou << "====Checking if this object is still in the hash table:" << endl;
    if (hashtable.Contains(rand_student_from_list)) {
        fou << "Hash table contains " << rand_student_from_list << endl;
    } else {
        fou << "Hash table does not contain " << rand_student_from_list << endl;
    }
    fou << endl;

    Student new_custom_student("Phi", "Luu", 4.0, 20);
    fou << "====Inserting a new object into the hash table:" << endl;
    if (hashtable.Insert(new_custom_student)) {
        fou << "Successfully inserted " << new_custom_student << endl;
    } else {
        fou << "Failed to insert " << new_custom_student << endl;
    }
    fou << endl;

    fou << "====Checking if this object is in the hash table:" << endl;
    if (hashtable.Contains(new_custom_student)) {
        fou << "Hash table contains " << new_custom_student << endl;
    } else {
        fou << "Hash table does not contain " << new_custom_student << endl;
    }
    fou << endl;

    fou << "====Post-modified print:" << endl;
    fou << "Hash table should contain " << new_custom_student
        << " and should not contain " << rand_student_from_list << endl
        << hashtable << endl;

    fin.close();
    fou.close();

    return 0;
}

// Creates rows of random Student objects, and thus a row has the following
// format:
//
// FirstName,LastName,GPA,Age
//
// Credits to http://random-name-generator.info/ for those random names.
// First, go to the site and generate random names. Copy those names to the
// infile first, then execute this function.
void CreateCsvTestFile(std::ifstream& infile, std::ofstream& outfile) {
    // quit right away if files aren't opened
    if (!infile.is_open() || !outfile.is_open()) return;

    std::mt19937 rng;
    std::random_device rd;
    std::uniform_real_distribution<double> rand_gpa(2.0, 4.0);
    std::uniform_int_distribution<int> rand_age(15, 55);

    rng.seed(rd());

    // read the lines one by one
    string name;
    while (std::getline(infile, name)) {
        // replace space between first name and last name with a comma
        for (char& ch : name)
            if (ch == ' ') ch = ',';

        // output extra info (GPA and age) to  outfile
        outfile << name << ',' << round(rand_gpa(rng) * 100.0) / 100.0 << ','
                << rand_age(rng) << endl;
    }
}
