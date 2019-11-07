CXX = g++

INCLUDE_DIR = include
UTILITY_DIR = utility
TEST_DIR = test
OUTPUT_DIR = bin

CXXFLAGS = -std=c++17 -Wall -I$(INCLUDE_DIR) -I$(UTILITY_DIR) -lgtest -lgtest_main -pthread

ALL = linear-search-test

all: init $(ALL)

init:
	mkdir -p $(OUTPUT_DIR)

$(TEST_DIR)/%.o: $(TEST_DIR)/%.cpp
	$(CXX) $< -c $(CXXFLAGS) -o $@

avl-tree-test: $(TEST_DIR)/avl-tree-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

binary-heap-test: $(TEST_DIR)/binary-heap-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

binary-search-test: $(TEST_DIR)/binary-search-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

binary-search-tree-test: $(TEST_DIR)/binary-search-tree-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

bubble-sort-test: $(TEST_DIR)/bubble-sort-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

deque-test: $(TEST_DIR)/deque-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

hash-table-test: $(TEST_DIR)/hash-table-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

insertion-sort-test: $(TEST_DIR)/insertion-sort-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

linear-search-test: $(TEST_DIR)/linear-search-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

linked-list-test: $(TEST_DIR)/linked-list-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

merge-sort-test: $(TEST_DIR)/merge-sort-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

queue-test: $(TEST_DIR)/queue-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

quick-sort-test: $(TEST_DIR)/quick-sort-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

selection-sort-test: $(TEST_DIR)/selection-sort-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

skip-list-test: $(TEST_DIR)/skip-list-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

stack-test: $(TEST_DIR)/stack-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

vector-test: $(TEST_DIR)/vector-test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

.PHONY: clean

clean:
	rm -rf $(OUTPUT_DIR) $(TEST_DIR)/*.o
