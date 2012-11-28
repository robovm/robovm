project(bc)

include_directories(../../core/include ${CMAKE_BINARY_DIR}/gc/include)

if (WITH_FPIC)
  remove_definitions(-fPIC)
endif()

add_library(robovm-bc STATIC 
  bc.c
  classinfo.c
  MurmurHash3.c
)

set_target_properties(robovm-bc PROPERTIES SUFFIX "${LIB_SUFFIX}")
install(TARGETS robovm-bc DESTINATION ${INSTALL_DIR})
