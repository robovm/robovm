project(core)

enable_language(ASM)
add_custom_command(OUTPUT trampolines-${OS}-${ARCH}.s.o COMMAND as -o trampolines-${OS}-${ARCH}.s.o ${core_SOURCE_DIR}/trampolines-${OS}-${ARCH}.s)

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
  trampolines-${OS}-${ARCH}.s.o
  unwind.c 
  vminterface.c
)

if(LINUX)
  set_target_properties(nullvm-core PROPERTIES LINK_FLAGS "-Wl,--version-script=${core_SOURCE_DIR}/symbols.linux" INSTALL_RPATH "$ORIGIN")
elseif(DARWIN)
  set_target_properties(nullvm-core PROPERTIES LINK_FLAGS "-exported_symbols_list ${core_SOURCE_DIR}/symbols.darwin" INSTALL_RPATH "$ORIGIN")
endif()
target_link_libraries(nullvm-core nullvm-hyprt gc)
install(TARGETS nullvm-core DESTINATION ${INSTALL_DIR}/lib)

