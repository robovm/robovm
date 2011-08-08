TOP := $(abspath $(dir $(lastword $(MAKEFILE_LIST)))../../..)
BASE := $(TOP)/src/main/native

include $(BASE)/files.mk

TARGET=$(TOP)/target
OBJ=$(patsubst %.c, $(TARGET)/native/%.o, $(SRC))
OUT = $(TARGET)/libnullvm-rt.so
INCLUDES = -I $(BASE)/include -I $(HOME)/.nullvm/include -I $(HOME)/.nullvm/include/harmony -I $(HOME)/.nullvm/gc/include -I $(dir $<)../shared/ -I $(dir $<)../unix/
CCFLAGS = -g -fPIC -DLINUX -DLINUX_X86_64 -DHYX86_64 -DIPv6_FUNCTION_SUPPORT -D_IEEE_LIBM
ASFLAGS = -g
CCC = gcc
AS = as

.SUFFIXES: .c .s

default: $(OUT)

$(TARGET)/native/%.o: $(BASE)/%.c
	mkdir -p $(dir $@)
	$(CCC) $(INCLUDES) $(CCFLAGS) -c $< -o $@

$(TARGET)/native/%.o: $(BASE)/%.s
	mkdir -p $(dir $@)
	$(AS) $(ASFLAGS) -o $@ $<

$(OUT): $(OBJ)
	gcc -shared -Wl,-soname,$(notdir $(OUT)) -Wl,--version-script $(BASE)/symbols.map -o $(OUT) $(OBJ) -L $(HOME)/.nullvm/lib -lc -lz -lm -lpthread -lnullvm
#	ar rcs $(OUT) $(OBJ)

clean:
	rm -rf $(TARGET)/native $(OUT)

