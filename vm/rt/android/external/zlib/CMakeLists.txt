project(android-external-zlib)

add_definitions(-DUSE_MMAP)

set(SRC
  adler32.c
  compress.c
  crc32.c
  gzclose.c
  gzlib.c
  gzread.c
  gzwrite.c
  uncompr.c
  deflate.c
  trees.c
  zutil.c
  inflate.c
  infback.c
  inftrees.c
  inffast.c
)

add_library(android-external-zlib OBJECT ${SRC})
set(EXTERNAL_ZLIB_OBJECTS $<TARGET_OBJECTS:android-external-zlib> PARENT_SCOPE)

