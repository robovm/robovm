SHELL := /bin/bash
TOP := $(abspath $(dir $(lastword $(MAKEFILE_LIST))).)
BASE := $(TOP)/src
TARGET := $(TOP)/target
ARCH := x86_64
OS := linux
INSTALL := $(HOME)/.nullvm

include $(BASE)/files.mk

OBJ=$(patsubst %.s, $(TARGET)/%.o, $(patsubst %.c, $(TARGET)/%.o, $(SRC)))
OUT = $(TARGET)/libnullvm.so
INCLUDES = -I $(TOP)/include -I $(TOP)/include/harmony/shared -I $(TOP)/include/harmony/unix -I $(INSTALL)/gc/include -I $(dir $<)../shared -I $(dir $<)../unix -I $(dir $<)../../shared -I $(dir $<)../../unix
CCFLAGS = -g -fPIC -DLINUX -DLINUX_X86_64 -DHYX86_64 -DIPv6_FUNCTION_SUPPORT -DHYPORT_LIBRARY_DEFINE
ASFLAGS = -g
CCC = gcc
AS = as

.SUFFIXES: .c .s

default: fetch-depends $(OUT)

fetch-depends:
	if [ ! -f $(INSTALL)/gc/lib/libgc.a ]; \
	then \
		rm -rf $(INSTALL)/gc; \
		rm -rf $(TARGET)/gc*; \
		$(SHELL) -c 'cd $(TARGET); wget http://www.hpl.hp.com/personal/Hans_Boehm/gc/gc_source/gc.tar.gz; tar xvfz gc.tar.gz'; \
		$(SHELL) -c 'cd $(TARGET)/gc*; ./configure --prefix=$(INSTALL)/gc; make; make check; make install'; \
	fi

$(TARGET)/%.o: $(BASE)/%.c
	mkdir -p $(dir $@)
	$(CCC) $(INCLUDES) $(CCFLAGS) -c $< -o $@

$(TARGET)/%.o: $(BASE)/%.s
	mkdir -p $(dir $@)
	$(AS) $(ASFLAGS) -o $@ $<

$(OUT): $(OBJ)
	gcc -shared -Wl,-soname,$(notdir $(OUT)) -Wl,--version-script $(TOP)/symbols.map -o $(OUT) $(OBJ) -L $(INSTALL)/gc/lib -lgc -lpthread -ldl

clean:
	rm -rf $(OBJ) $(OUT)

install: $(OUT)
	mkdir -p $(INSTALL)/include
	mkdir -p $(INSTALL)/include/nullvm
	mkdir -p $(INSTALL)/include/harmony
	mkdir -p $(INSTALL)/lib
	cp $(TOP)/include/*.h $(INSTALL)/include
	cp $(TOP)/include/nullvm/*.h $(INSTALL)/include/nullvm
	cp $(TOP)/include/harmony/shared/*.h $(INSTALL)/include/harmony
	cp $(TOP)/include/harmony/unix/*.h $(INSTALL)/include/harmony
	cp $(OUT) $(INSTALL)/lib/
	cp $(INSTALL)/gc/lib/libgc.so.1 $(INSTALL)/lib/

