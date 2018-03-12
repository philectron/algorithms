CXX = g++

INC_DIR = include
SRC_DIR = src
OUT_DIR = bin
OUTFILE_DIR = output

CXXFLAGS = -pipe -O3 -std=c++0x -Wall -Wextra -lm -I$(INC_DIR)

ALL = \
		binary_search_tree \
		linked_list \
		queue \
		stack

all: init $(ALL)

init:
	mkdir -p $(OUT_DIR)
	mkdir -p $(OUTFILE_DIR)

$(SRC_DIR)/%.o: $(SRC_DIR)/%.cpp
	$(CXX) -c $(CXXFLAGS) $< -o $@


binary_search_tree: $(SRC_DIR)/binary_search_tree.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

linked_list: $(SRC_DIR)/linked_list.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

queue: $(SRC_DIR)/queue.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@

stack: $(SRC_DIR)/stack.o
	$(CXX) $(CXXFLAGS) $? -o $(OUT_DIR)/$@


.PHONY: clean

clean:
	rm -f $(SRC_DIR)/*.o
	rm -rf $(OUT_DIR) $(OUTFILE_DIR)
