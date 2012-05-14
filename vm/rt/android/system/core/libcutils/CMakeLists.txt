project(android-system-core-libcutils)

set(SRC
  strdup8to16.c
)

add_library(android-system-core-libcutils OBJECT ${SRC})
set(SYSTEM_CORE_LIBCUTILS_OBJECTS $<TARGET_OBJECTS:android-system-core-libcutils> PARENT_SCOPE)

