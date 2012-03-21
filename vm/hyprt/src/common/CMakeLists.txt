project(hycommon)
add_library(nullvm-hycommon STATIC 
  shared/libglob.c shared/iohelp.c 
  shared/exceptions.c shared/strhelp.c 
  shared/utf8encode.c shared/utf8decode.c
)
install(TARGETS nullvm-hycommon DESTINATION ${INSTALL_DIR})

