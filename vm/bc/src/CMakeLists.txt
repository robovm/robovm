project(bc)

include_directories(../../hyprt/include ../../core/include ${CMAKE_BINARY_DIR}/gc/include)

if (WITH_FPIC)
  remove_definitions(-fPIC)
endif()

add_library(robovm-bc STATIC 
  bc.c
  classinfo.c
  MurmurHash3.c
)

install(TARGETS robovm-bc DESTINATION ${INSTALL_DIR})

