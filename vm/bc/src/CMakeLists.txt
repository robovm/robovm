project(bc)

add_library(nullvm-bc STATIC 
  bc.c
)

install(TARGETS nullvm-bc DESTINATION ${INSTALL_DIR}/lib)

