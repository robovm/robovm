project(core)

if(DARWIN)
  set(ASFLAGS -arch ${ARCH})
endif()

enable_language(ASM)
add_custom_command(
  OUTPUT call0-${OS}-${ARCH}.s.o 
  COMMAND as ${ASFLAGS} -g -o call0-${OS}-${ARCH}.s.o ${core_SOURCE_DIR}/call0-${OS}-${ARCH}.s 
  MAIN_DEPENDENCY call0-${OS}-${ARCH}.s 
)
add_custom_command(
  OUTPUT proxy0-${OS}-${ARCH}.s.o 
  COMMAND as ${ASFLAGS} -g -o proxy0-${OS}-${ARCH}.s.o ${core_SOURCE_DIR}/proxy0-${OS}-${ARCH}.s 
  MAIN_DEPENDENCY proxy0-${OS}-${ARCH}.s 
)

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
  call0-${OS}-${ARCH}.s.o
  proxy0-${OS}-${ARCH}.s.o
  unwind.c 
  vminterface.c
)
add_dependencies(nullvm-core extgc)

if(LINUX)
  set_target_properties(nullvm-core PROPERTIES LINK_FLAGS "-Wl,--version-script=${core_SOURCE_DIR}/symbols.linux" INSTALL_RPATH "$ORIGIN")
elseif(DARWIN)
  set_target_properties(nullvm-core PROPERTIES LINK_FLAGS "-exported_symbols_list ${core_SOURCE_DIR}/symbols.darwin" INSTALL_NAME_DIR "@loader_path")
endif()
target_link_libraries(nullvm-core nullvm-hyprt gc)
install(TARGETS nullvm-core DESTINATION ${INSTALL_DIR})

add_executable(test_call0 test/test_call0.c test/CuTest.c call0-${OS}-${ARCH}.s.o)
add_test(testCall0ReturnByte test_call0 "testCall0ReturnByte")
add_test(testCall0ReturnInt test_call0 "testCall0ReturnInt")
add_test(testCall0ReturnPtr test_call0 "testCall0ReturnPtr")
add_test(testCall0ReturnLong test_call0 "testCall0ReturnLong")
add_test(testCall0ReturnFloat test_call0 "testCall0ReturnFloat")
add_test(testCall0ReturnDouble test_call0 "testCall0ReturnDouble")
add_test(testCall0OneArgOfEach test_call0 "testCall0OneArgOfEach")
add_test(testCall0ManyArgsOfEach test_call0 "testCall0ManyArgsOfEach")
add_test(testCall0Unwind test_call0 "testCall0Unwind")
add_test(testCall0Raise test_call0 "testCall0Raise")

add_executable(test_proxy0 test/test_proxy0.c test/CuTest.c proxy0-${OS}-${ARCH}.s.o)
add_test(testProxy0ReturnByte test_proxy0 "testProxy0ReturnByte")
add_test(testProxy0ReturnInt test_proxy0 "testProxy0ReturnInt")
add_test(testProxy0ReturnPtr test_proxy0 "testProxy0ReturnPtr")
add_test(testProxy0ReturnLong test_proxy0 "testProxy0ReturnLong")
add_test(testProxy0ReturnFloat test_proxy0 "testProxy0ReturnFloat")
add_test(testProxy0ReturnDouble test_proxy0 "testProxy0ReturnDouble")
add_test(testProxy0OneArgOfEach test_proxy0 "testProxy0OneArgOfEach")
add_test(testProxy0ManyArgsOfEach test_proxy0 "testProxy0ManyArgsOfEach")
add_test(testProxy0Unwind test_proxy0 "testProxy0Unwind")

