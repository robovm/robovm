project(hycommon)
add_library(hycommon STATIC 
  shared/libglob.c shared/iohelp.c 
  shared/exceptions.c shared/strhelp.c 
  shared/utf8encode.c shared/utf8decode.c
)

