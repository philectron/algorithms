CXX = g++

INC_DIR = include
SRC_DIR = src
OUT_DIR = bin
FOU_DIR = output

CXXFLAGS = -g -pipe -O3 -std=c++0x -Wall -Wextra -lm -I$(INC_DIR)

ALL = \
		avl_tree \
		binary_heap \
		binary_search \
		binary_search_tree \
		bubble_sort \
		hash_table \
		insertion_sort \
		linear_search \
		linked_list \
		merge_sort \
		queue \
		quick_sort \
		selection_sort \
		skip_list \
		stack \
		vector

all: init $(ALL)

init:
	mkdir -p $(OUT_DIR) $(FOU_DIR)

$(SRC_DIR)/%.o: $(SRC_DIR)/%.cpp
	$(CXX) -c $(CXXFLAGS) $< -o $@


avl_tree: $(SRC_DIR)/avl_tree.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

binary_heap: $(SRC_DIR)/binary_heap.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

binary_search: $(SRC_DIR)/binary_search.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

binary_search_tree: $(SRC_DIR)/binary_search_tree.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

bubble_sort: $(SRC_DIR)/bubble_sort.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

hash_table: $(SRC_DIR)/hash_table.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

insertion_sort: $(SRC_DIR)/insertion_sort.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

linear_search: $(SRC_DIR)/linear_search.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

linked_list: $(SRC_DIR)/linked_list.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

merge_sort: $(SRC_DIR)/merge_sort.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

queue: $(SRC_DIR)/queue.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

quick_sort: $(SRC_DIR)/quick_sort.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

selection_sort: $(SRC_DIR)/selection_sort.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

skip_list: $(SRC_DIR)/skip_list.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

stack: $(SRC_DIR)/stack.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

vector: $(SRC_DIR)/vector.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@


.PHONY: clean

clean:
	rm -f $(SRC_DIR)/*.o
	rm -rf $(OUT_DIR) $(FOU_DIR)
