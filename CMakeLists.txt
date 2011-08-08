cmake_minimum_required(VERSION 2.8)
project(build)
include(CheckCCompilerFlag)

find_path(ZLIB_H_PATH zlib.h)
if(NOT ZLIB_H_PATH)
  message(FATAL_ERROR "zlib.h not found")
endif()
find_path(GC_H_PATH gc.h)
if(NOT GC_H_PATH)
  message(FATAL_ERROR "gc.h not found")
endif()

if(CMAKE_SYSTEM_NAME MATCHES "Linux")
  SET(LINUX YES)
else()
  message(FATAL_ERROR "Unsupported OS: ${CMAKE_SYSTEM_NAME}")
endif()

SET(ARCH x86_64)

include_directories(portlib/include libnullvm/include ${ZLIB_H_PATH} ${GC_H_PATH})

if(LINUX AND CMAKE_SIZEOF_VOID_P MATCHES "8")
  CHECK_C_COMPILER_FLAG("-fPIC" WITH_FPIC)
endif()

if(LINUX)
  SET(HY_OS linux)
  add_definitions(-DLINUX)
  include_directories(portlib/include/unix)
endif()

if (ARCH STREQUAL "x86_64")
  add_definitions(-DHYX86_64)
endif()

add_definitions(-DIPv6_FUNCTION_SUPPORT)

add_subdirectory(portlib/src)
add_subdirectory(libnullvm/src)
add_subdirectory(rt/src/main/native)

