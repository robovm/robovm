project(android-dalvik-vm)

include_directories(
  ../libnativehelper/include/nativehelper
)

set(SRC
  InlineNative.cpp
  native/java_util_concurrent_atomic_AtomicLong.cpp
)

add_library(android-dalvik-vm OBJECT ${SRC})
set(DALVIK_VM_OBJECTS $<TARGET_OBJECTS:android-dalvik-vm> PARENT_SCOPE)

