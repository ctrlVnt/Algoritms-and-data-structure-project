CC = gcc
all: create bin/sorting_array bin/sorting_array_tests
	
create:
	@mkdir -p build
	@mkdir -p bin

CFLAGS = -g

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) makefile

build/%.o: src/%.c $(COMMON_DEPS)
	@$(CC) $(CFLAGS) -c $< -o $@

bin/sorting_array: build/sorting_array.o build/sorting_array_main.o $(COMMON_DEPS)
	@$(CC) -o bin/sorting_array build/sorting_array.o build/sorting_array_main.o

bin/sorting_array_tests: build/sorting_array_tests.o build/sorting_array.o build/unity.o $(COMMON_DEPS)
	@$(CC) -o bin/sorting_array_tests  build/sorting_array_tests.o build/sorting_array.o build/unity.o

main:
	@./bin/sorting_array "records.csv"

test:
	@./bin/sorting_array_tests

clean:
	rm -f build/* bin/* sorted.csv