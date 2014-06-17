project(android-external-zlib)

add_definitions(-DUSE_MMAP)

set(SRC
  src/adler32.c
  src/compress.c
  src/crc32.c
  src/gzclose.c
  src/gzlib.c
  src/gzread.c
  src/gzwrite.c
  src/uncompr.c
  src/deflate.c
  src/trees.c
  src/zutil.c
  src/inflate.c
  src/infback.c
  src/inftrees.c
  src/inffast.c
)

add_library(android-external-zlib OBJECT ${SRC})
set(EXTERNAL_ZLIB_OBJECTS $<TARGET_OBJECTS:android-external-zlib> PARENT_SCOPE)

