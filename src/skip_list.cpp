#include <fstream>
#include "skip_list.hpp"

using std::cout;
using std::endl;

int main() {
    // START OF QUICK TEST AREA
    SkipList<int> s;

    s.Insert(1);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(55);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(5);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(10);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(3);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(15);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(23);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(14);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(56);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(2);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    s.Insert(10);
    cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    cout << "Hard copy and print:" << endl;
    SkipList<int> p = s;
    cout << "P Height = " << p.Height() << ", P Size = " << p.Size() << endl << p << endl;
    cout << "S Height = " << s.Height() << ", S Size = " << s.Size() << endl << s << endl;

    cout << "Soft copy and print:" << endl;
    SkipList<int> r;
    s.SoftCopy(r);
    cout << "R Height = " << r.Height() << ", R Size = " << r.Size() << endl << r << endl;
    cout << "S Height = " << s.Height() << ", S Size = " << s.Size() << endl << s << endl;

    cout << "Remove all 10's from P" << endl;
    p.RemoveAll(10);
    cout << "P Height = " << p.Height() << ", P Size = " << p.Size() << endl << p << endl;
    cout << "S Height = " << s.Height() << ", S Size = " << s.Size() << endl << s << endl;

    // s.Print();

    // if (s.Contains(23)) {
    //     cout << "Contains 23" << endl << endl;
    // } else {
    //     cout << "Not contain 23" << endl << endl;
    // }

    // if (s.Contains(24)) {
    //     cout << "Contains 24" << endl << endl;
    // } else {
    //     cout << "Not contain 24" << endl << endl;
    // }

    // s.Remove(5);
    // cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    // s.RemoveAll(10);
    // cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    // s.Insert(24);
    // cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    // if (s.Contains(24)) {
    //     cout << "Contains 24" << endl << endl;
    // } else {
    //     cout << "Not contain 24" << endl << endl;
    // }

    // s.RemoveAll(14);
    // cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    // s.RemoveAll(15);
    // cout << "Height = " << s.Height() << ", Size = " << s.Size() << endl << s << endl;

    return 0;

    // END OF QUICK TEST AREA

    std::ifstream fin("../input/skip_list.in");
    std::ofstream fou("../output/skip_list.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << endl;
        return 1;
    }

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {}

    fin.close();
    fou.close();

    return 0;
}
