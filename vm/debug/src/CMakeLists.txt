project(debug)

include_directories(../../core/include ${CMAKE_BINARY_DIR}/gc/include)

set(SRC
  debug.c 
)

add_library(robovm-debug STATIC ${SRC})

set_target_properties(robovm-debug PROPERTIES SUFFIX "${LIB_SUFFIX}")
install(TARGETS robovm-debug DESTINATION ${INSTALL_DIR})
