CXX = g++

INCLUDE_DIR = include
TEST_DIR = test
OUTPUT_DIR = bin

CXXFLAGS = -std=c++14 -Wall -I$(INCLUDE_DIR) -lgtest -lgtest_main -pthread

ALL = linear_search_test

# ALL = \
# 		avl_tree_test \
# 		binary_heap_test \
# 		binary_search_test \
# 		binary_search_tree_test \
# 		bubble_sort_test \
# 		deque_test \
# 		hash_table_test \
# 		insertion_sort_test \
# 		linear_search_test \
# 		linked_list_test \
# 		merge_sort_test \
# 		queue_test \
# 		quick_sort_test \
# 		selection_sort_test \
# 		skip_list_test \
# 		stack_test \
# 		vector_test

all: init $(ALL)

init:
	mkdir -p $(OUTPUT_DIR)

$(TEST_DIR)/%.o: $(SRC_DIR)/%.cpp
	$(CXX) $< -c $(CXXFLAGS) -o $@

avl_tree_test: $(TEST_DIR)/avl_tree_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

binary_heap_test: $(TEST_DIR)/binary_heap_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

binary_search_test: $(TEST_DIR)/binary_search_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

binary_search_tree_test: $(TEST_DIR)/binary_search_tree_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

bubble_sort_test: $(TEST_DIR)/bubble_sort_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

deque_test: $(TEST_DIR)/deque_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

hash_table_test: $(TEST_DIR)/hash_table_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

insertion_sort_test: $(TEST_DIR)/insertion_sort_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

linear_search_test: $(TEST_DIR)/linear_search_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

linked_list_test: $(TEST_DIR)/linked_list_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

merge_sort_test: $(TEST_DIR)/merge_sort_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

queue_test: $(TEST_DIR)/queue_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

quick_sort_test: $(TEST_DIR)/quick_sort_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

selection_sort_test: $(TEST_DIR)/selection_sort_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

skip_list_test: $(TEST_DIR)/skip_list_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

stack_test: $(TEST_DIR)/stack_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

vector_test: $(TEST_DIR)/vector_test.o
	$(CXX) $? $(CXXFLAGS) -o $(OUTPUT_DIR)/$@

.PHONY: clean

clean:
	rm -rf $(OUTPUT_DIR) $(TEST_DIR)/*.o
