project(drlvm-thread)

include_directories(.)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wno-unused-function -Wno-comment -Wno-unused-variable -Wno-unused-but-set-variable")

set(SRC
  thread_init.c
  thread_native_attrs.c
  thread_native_basic.c
  thread_native_condvar.c
  thread_native_fat_monitor.c
  thread_native_groups.c
  thread_native_interrupt.c
  thread_native_iterator.c
  thread_native_latch.c
  thread_native_park.c
  thread_native_semaphore.c
  thread_native_state.c
  thread_native_suspend.c
  thread_native_thin_monitor.c
  thread_native_tls.c
  thread_ncai_common.c
  linux/os_condvar.c
)

add_library(drlvm-thread OBJECT ${SRC})
set(DRLVM_THREAD_OBJECTS $<TARGET_OBJECTS:drlvm-thread> PARENT_SCOPE)
