project(core)
include(../../drlvm/drlvm-defines.cmake)

include_directories(../../drlvm/vm/include ../../drlvm/vm/port/include ../include ${CMAKE_BINARY_DIR}/gc/include ${CMAKE_BINARY_DIR}/apr/include/apr-1)

set(SRC
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
  call0-${OS_FAMILY}-${ARCH}.s
  proxy0-${OS_FAMILY}-${ARCH}.s
  trycatch-${OS_FAMILY}-${ARCH}.s
  unwind.c
)

add_library(robovm-core STATIC ${SRC})
add_dependencies(robovm-core extgc)

install(TARGETS robovm-core DESTINATION ${INSTALL_DIR})

add_executable(test_call0 test/test_call0.c test/CuTest.c call0-${OS_FAMILY}-${ARCH}.s unwind.c)
add_test(testCall0ReturnByte test_call0 "testCall0ReturnByte")
add_test(testCall0ReturnInt test_call0 "testCall0ReturnInt")
add_test(testCall0ReturnPtr test_call0 "testCall0ReturnPtr")
add_test(testCall0ReturnLong test_call0 "testCall0ReturnLong")
add_test(testCall0ReturnFloat test_call0 "testCall0ReturnFloat")
add_test(testCall0ReturnDouble test_call0 "testCall0ReturnDouble")
add_test(testCall0OneArgOfEach test_call0 "testCall0OneArgOfEach")
add_test(testCall0ManyArgsOfEach test_call0 "testCall0ManyArgsOfEach")
add_test(testCall0Unwind test_call0 "testCall0Unwind")

add_executable(test_proxy0 test/test_proxy0.c test/CuTest.c proxy0-${OS_FAMILY}-${ARCH}.s unwind.c)
add_test(testProxy0ReturnByte test_proxy0 "testProxy0ReturnByte")
add_test(testProxy0ReturnInt test_proxy0 "testProxy0ReturnInt")
add_test(testProxy0ReturnPtr test_proxy0 "testProxy0ReturnPtr")
add_test(testProxy0ReturnLong test_proxy0 "testProxy0ReturnLong")
add_test(testProxy0ReturnFloat test_proxy0 "testProxy0ReturnFloat")
add_test(testProxy0ReturnDouble test_proxy0 "testProxy0ReturnDouble")
add_test(testProxy0OneArgOfEach test_proxy0 "testProxy0OneArgOfEach")
add_test(testProxy0ManyArgsOfEach test_proxy0 "testProxy0ManyArgsOfEach")
add_test(testProxy0Unwind test_proxy0 "testProxy0Unwind")
add_test(testProxy0ReturnAddress test_proxy0 "testProxy0ReturnAddress")

add_executable(test_trycatch test/test_trycatch.c test/CuTest.c trycatch-${OS_FAMILY}-${ARCH}.s)
add_test(testTrycatchEnterLeaveOnce test_trycatch "testTrycatchEnterLeaveOnce")
add_test(testTrycatchEnterLeaveMultiple test_trycatch "testTrycatchEnterLeaveMultiple")
add_test(testTrycatchJumpOnce test_trycatch "testTrycatchJumpOnce")
add_test(testTrycatchJumpNested test_trycatch "testTrycatchJumpNested")
