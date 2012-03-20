project(bc)

if (WITH_FPIC)
  remove_definitions(-fPIC)
endif()

add_library(nullvm-bc STATIC 
  bc.c
  classinfo.c
  MurmurHash3.c
)

install(TARGETS nullvm-bc DESTINATION ${INSTALL_DIR})

