CC = gcc
all: create bin/skip_list bin/skip_list_tests

create:
	@mkdir -p build
	@mkdir -p bin

CFLAGS = -g

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) makefile

build/%.o: src/%.c $(COMMON_DEPS)
	@$(CC) $(CFLAGS) -c $< -o $@

bin/skip_list: build/skip_list.o build/skip_list_main.o $(COMMON_DEPS)
	@$(CC) -o bin/skip_list build/skip_list.o build/skip_list_main.o

bin/skip_list_tests: build/skip_list_tests.o build/skip_list.o build/unity.o $(COMMON_DEPS)
	@$(CC) -o bin/skip_list_tests  build/skip_list_tests.o build/skip_list.o build/unity.o

main:
	@./bin/skip_list "data/dictionary.txt" "data/correctme.txt"

test:
	@./bin/skip_list_tests

clean:
	rm -f build/* bin/*