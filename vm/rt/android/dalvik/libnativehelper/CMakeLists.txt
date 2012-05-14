project(android-dalvik-libnativehelper)

include_directories(
  include/nativehelper
)

set(SRC
  JNIHelp.cpp
)

add_library(android-dalvik-libnativehelper OBJECT ${SRC})
set(DALVIK_LIBNATIVEHELPER_OBJECTS $<TARGET_OBJECTS:android-dalvik-libnativehelper> PARENT_SCOPE)

