# source files.
SRC = init.c class.c string.c exception.c array.c field.c method.c native.c

OBJ = $(SRC:.c=.o)

OUT = libnullvm.a

# include directories
INCLUDES = -I. -Iinclude -I../gc/include

# C compiler flags
CCFLAGS = -g

# compiler
CCC = gcc

.SUFFIXES: .c

default: $(OUT)

.c.o:
	$(CCC) $(INCLUDES) $(CCFLAGS) -c $< -o $@

$(OUT): $(OBJ)
	ar rcs $(OUT) $(OBJ)

install: $(OUT)
	mkdir -p ~/.nullvm/include
	mkdir -p ~/.nullvm/lib
	cp include/*.h ~/.nullvm/include
	cp $(OUT) ~/.nullvm/lib/$(OUT)

clean:
	rm -f $(OBJ) $(OUT)

init.o: include/nullvm.h
class.o: include/nullvm.h
exception.o: include/nullvm.h
string.o: include/nullvm.h
array.o: include/nullvm.h
field.o: include/nullvm.h
method.o: include/nullvm.h
native.o: include/nullvm.h

