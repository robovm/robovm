project(core)

add_custom_command(OUTPUT trampolines-${ARCH}.s.o COMMAND ${CMAKE_ASM_COMPILER} -o trampolines-${ARCH}.s.o ${core_SOURCE_DIR}/trampolines-${ARCH}.s)

add_library(nullvm-core SHARED 
  array.c 
  attribute.c 
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
  trampolines-${ARCH}.s.o
  unwind.c 
  vminterface.c
)

set_target_properties(nullvm-core PROPERTIES LINK_FLAGS "-Wl,--version-script=${core_SOURCE_DIR}/symbols.map" INSTALL_RPATH "$ORIGIN")
target_link_libraries(nullvm-core nullvm-hyprt gc)
install(TARGETS nullvm-core DESTINATION ${INSTALL_DIR}/lib)

