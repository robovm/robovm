project(bclib)

include_directories(../../core/include ${CMAKE_BINARY_DIR}/gc/include)

if (WITH_FPIC)
  remove_definitions(-fPIC)
endif()

add_library(robovm-bclib STATIC 
  bc.c
  classinfo.c
  MurmurHash3.c
)

set_target_properties(robovm-bclib PROPERTIES SUFFIX "${LIB_SUFFIX}")
install(TARGETS robovm-bclib DESTINATION ${INSTALL_DIR})
