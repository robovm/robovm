project(core)

enable_language(ASM)

add_library(nullvm-core SHARED 
  array.c 
  attribute.c 
  bc.c 
  class.c 
  exception.c 
  field.c 
  init.c 
  log.c 
  memory.c 
  method.c 
  native.c 
  proxy.c 
  string.c 
  thread.c 
  trampolines-${ARCH}.s
  unwind.c 
  vminterface.c
)

set_target_properties(nullvm-core PROPERTIES LINK_FLAGS "-Wl,--version-script=${core_SOURCE_DIR}/symbols.map")
target_link_libraries(nullvm-core nullvm-hyprt gc)

