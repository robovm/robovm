project(hyprt)

include_directories(../include ../../rt/android/external/zlib)
if(LINUX OR DARWIN)
  include_directories(../include/unix)
endif()

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -DGC_THREADS -include ${CMAKE_BINARY_DIR}/gc/include/gc/gc.h")

add_subdirectory(common)
add_subdirectory(pool)
if(LINUX OR DARWIN)
  add_subdirectory(thread/unix)
  add_subdirectory(port/unix)
endif()

