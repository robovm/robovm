# source files.
SRC = init.c class.c string.c exception.c array.c field.c method.c native.c map.c bc.c trampolines-x86_64.s

OBJ = init.o class.o string.o exception.o array.o field.o method.o native.o map.o bc.o trampolines-x86_64.o


OUT = libnullvm.a

# include directories
INCLUDES = -I. -Iinclude -I../gc/include

# C compiler flags
CCFLAGS = -g

ASFLAGS = -g

# compiler
CCC = gcc

AS = as

.SUFFIXES: .c .s

default: $(OUT)

.c.o:
	$(CCC) $(INCLUDES) $(CCFLAGS) -c $< -o $@

.s.o:
	$(AS) $(ASFLAGS) -o $@ $<

$(OUT): $(OBJ)
	ar rcs $(OUT) $(OBJ)

install: $(OUT)
	mkdir -p ~/.nullvm/include
	mkdir -p ~/.nullvm/include/nullvm
	mkdir -p ~/.nullvm/lib
	cp include/*.h ~/.nullvm/include
	cp include/nullvm/*.h ~/.nullvm/include/nullvm
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
map.o: include/nullvm.h
bc.o: include/nullvm.h

