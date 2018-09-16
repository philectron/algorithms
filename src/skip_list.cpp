#include "skip_list.hpp"

#include <fstream>

int main() {
    std::ifstream fin("../input/skip_list.in");
    std::ofstream fou("../output/skip_list.ou");

    if (!fin.is_open() || !fou.is_open()) {
        std::cerr << "Could not open file(s)." << std::endl;
        return 1;
    }

    std::mt19937 rng;
    std::random_device rd;
    std::uniform_int_distribution<int> coinflip(
        std::uniform_int_distribution<>(0, 1));
    std::uniform_int_distribution<int> rand_int_0_100(
        std::uniform_int_distribution<>(0, 100));

    int num_test_cases;
    fin >> num_test_cases;

    for (int t = 0; t < num_test_cases; t++) {
        datastructure::SkipList<int> skiplist;
        int size;
        int front_node = 0, back_node = 0, coinflip_node = 0;

        rng.seed(rd());
        fin >> size;
        for (int i = 0; i < size; i++) {
            int value;
            fin >> value;
            skiplist.Insert(value);

            // save front and back nodes for later tests
            if (i == 0) {
                front_node = value;
                back_node = value;
            } else {
                if (value < front_node) front_node = value;
                if (value > back_node) back_node = value;
            }

            // save a random node, called the coin flip node
            if (coinflip(rng)) coinflip_node = value;
        }

        fou << "Initial:\n" << skiplist << std::endl;

        fou << "After removing the front node (" << front_node << "):\n";
        skiplist.Remove(front_node);
        fou << skiplist << std::endl;

        fou << "After removing the back node (" << back_node << "):\n";
        skiplist.Remove(back_node);
        fou << skiplist << std::endl;

        int random_insert_1 = rand_int_0_100(rng);
        fou << "After inserting a random int from 0 to 100 ("
            << random_insert_1 << "):\n";
        skiplist.Insert(random_insert_1);
        fou << skiplist << std::endl;

        int random_insert_2 = rand_int_0_100(rng);
        fou << "After inserting another random int from 0 to 100 ("
            << random_insert_2 << "):\n";
        skiplist.Insert(random_insert_2);
        fou << skiplist << std::endl;

        fou << "After removing the first occurrence of "
            << coinflip_node << ":\n";
        skiplist.Remove(coinflip_node);
        fou << skiplist << std::endl;

        fou << "Checking if " << coinflip_node << " is in the skip list:\n";
        if (skiplist.Contains(coinflip_node)) {
            fou << "Skip list contains " << coinflip_node << std::endl;
        } else {
            fou << "Skip list does not contain " << coinflip_node << std::endl;
        }
        fou << skiplist << std::endl;

        fou << "After inserting " << coinflip_node << " twice:\n";
        skiplist.Insert(coinflip_node);
        skiplist.Insert(coinflip_node);
        fou << skiplist << std::endl;

        fou << "Checking if " << coinflip_node << " is in the skip list:\n";
        if (skiplist.Contains(coinflip_node)) {
            fou << "Skip list contains " << coinflip_node << std::endl;
        } else {
            fou << "Skip list does not contain " << coinflip_node << std::endl;
        }
        fou << skiplist << std::endl;

        fou << "After removing all instances of " << coinflip_node << ":\n";
        skiplist.RemoveAll(coinflip_node);
        fou << skiplist << std::endl;

        fou << "Checking if " << coinflip_node << " is in the skip list:\n";
        if (skiplist.Contains(coinflip_node)) {
            fou << "Skip list contains " << coinflip_node << std::endl;
        } else {
            fou << "Skip list does not contain " << coinflip_node << std::endl;
        }
        fou << skiplist << std::endl;

        fou << "After creating a hard copy and a soft copy:\n";
        datastructure::SkipList<int> hardcopy = skiplist;
        datastructure::SkipList<int> softcopy;
        skiplist.SoftCopy(softcopy);
        fou << "  Original skip list:\n" << skiplist;
        fou << "  Hard copied skip list:\n" << hardcopy;
        fou << "  Soft copied skip list:\n" << softcopy << std::endl;

        fou << "After changing the hard copied list to three random int "
            << "from 0 to 100:\n";
        hardcopy.Clear();
        hardcopy.Insert(rand_int_0_100(rng));
        hardcopy.Insert(rand_int_0_100(rng));
        hardcopy.Insert(rand_int_0_100(rng));
        fou << "  Original skip list:\n" << skiplist;
        fou << "  Hard copied skip list:\n" << hardcopy << std::endl;

        fou << "After trying to self-assign the original skip list:\n";
        skiplist = skiplist;
        fou << skiplist << std::endl;

        fou << "After assigning the soft copy to the original:\n";
        skiplist = softcopy;
        fou << "  Original skip list (which should be changed):\n" << skiplist;
        fou << "  Soft copied skip list:\n" << softcopy << std::endl;

        fou << "==========END OF TEST CASE t = " << t << "==========\n\n";
    }

    fin.close();
    fou.close();

    return 0;
}
