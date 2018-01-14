CXX = g++

INC_DIR = include
SRC_DIR = src
LIBS = -lm

CXXFLAGS = -g -std=c++11 -Wall -Wextra -I$(INC_DIR) $(LIBS)

ALL = \
		binary_search_tree \
		linked_list \
		queue \
		stack

all: $(ALL)

$(SRC_DIR)/%.o: $(SRC_DIR)/%.cpp
	$(CXX) -c $(CXXFLAGS) $< -o $@

binary_search_tree: $(SRC_DIR)/binary_search_tree_main.o
	$(CXX) $(CXXFLAGS) $? -o $@

linked_list: $(SRC_DIR)/linked_list_main.o
	$(CXX) $(CXXFLAGS) $? -o $@

queue: $(SRC_DIR)/queue_main.o
	$(CXX) $(CXXFLAGS) $? -o $@

stack: $(SRC_DIR)/stack_main.o
	$(CXX) $(CXXFLAGS) $? -o $@

.PHONY: clean

clean:
	rm -f $(ALL) $(SRC_DIR)/*.o
