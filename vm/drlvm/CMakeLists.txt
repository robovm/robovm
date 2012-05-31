project(drlvm)
include(drlvm-defines.cmake)

include_directories(vm/include vm/port/include ${CMAKE_BINARY_DIR}/apr/include/apr-1)


add_subdirectory(vm/port/src)
add_subdirectory(vm/thread/src)

add_library(robovm-drlvm STATIC ${DRLVM_PORT_OBJECTS} ${DRLVM_THREAD_OBJECTS})
add_dependencies(robovm-drlvm extapr)
install(TARGETS robovm-drlvm DESTINATION ${INSTALL_DIR})
