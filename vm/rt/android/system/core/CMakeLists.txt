project(android-system-core)

include_directories(
  include
)

add_subdirectory(libcutils)
set(SYSTEM_CORE_OBJECTS ${SYSTEM_CORE_LIBCUTILS_OBJECTS} PARENT_SCOPE)

