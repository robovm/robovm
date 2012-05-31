project(drlvm-port-thread)

include_directories(include)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -DGC_THREADS -include ${CMAKE_BINARY_DIR}/gc/include/gc/gc.h -Wno-unused-function -Wno-unused-variable -Wno-uninitialized")

set(SRC
  linux/port_thread_tls_os.c
  linux/thread_os.c
)

add_library(drlvm-port-thread OBJECT ${SRC})
set(DRLVM_PORT_THREAD_OBJECTS $<TARGET_OBJECTS:drlvm-port-thread> PARENT_SCOPE)
