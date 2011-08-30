cmake_minimum_required(VERSION 2.8)
project(build)
include(CheckCCompilerFlag)
include(ExternalProject)

enable_testing()

# TODO: Use CMake to build GC? There is a CMakeLists.txt but I cannot get the install to work.
ExternalProject_Add(extgc 
  URL "http://www.hpl.hp.com/personal/Hans_Boehm/gc/gc_source/gc-7.2alpha6.tar.gz"
  CONFIGURE_COMMAND ../extgc/configure --prefix=${CMAKE_BINARY_DIR}/gc
)

find_path(ZLIB_H_PATH zlib.h)
if(NOT ZLIB_H_PATH)
  message(FATAL_ERROR "zlib.h not found")
endif()

if(CMAKE_SYSTEM_NAME MATCHES "Linux")
  SET(LINUX YES)
elseif(CMAKE_SYSTEM_NAME MATCHES "Darwin")
  SET(DARWIN YES)
else()
  message(FATAL_ERROR "Unsupported OS: ${CMAKE_SYSTEM_NAME}")
endif()

if(NOT CMAKE_BUILD_TYPE)
   SET(CMAKE_BUILD_TYPE "Debug" CACHE STRING "Choose the type of build, options are: Debug Release RelWithDebInfo MinSizeRel." FORCE)
endif()

if(NOT ARCH)
   SET(ARCH "i386" CACHE STRING "Choose the architecture to build for, options are: i386 x86_64." FORCE)
endif()

if(NOT ARCH MATCHES "^(i386|x86_64)$")
  message(FATAL_ERROR "Unsupported ARCH: ${ARCH}")
endif()

include_directories(hyprt/include core/include ${ZLIB_H_PATH} ${CMAKE_BINARY_DIR}/gc/include)
link_directories(${CMAKE_BINARY_DIR}/gc/lib)

if((LINUX OR DARWIN) AND CMAKE_SIZEOF_VOID_P MATCHES "8")
  CHECK_C_COMPILER_FLAG("-fPIC" WITH_FPIC)
  if (WITH_FPIC)
    add_definitions(-fPIC)
  endif()
endif()

if(LINUX)
  SET(HY_OS linux)
  SET(OS linux)
  add_definitions(-DLINUX)
  include_directories(hyprt/include/unix)
elseif(DARWIN)
  SET(HY_OS macosx)
  SET(OS darwin)
  add_definitions(-DMACOSX)
  include_directories(hyprt/include/unix)
endif()

if (ARCH STREQUAL "x86_64")
  add_definitions(-DHYX86_64)
  add_definitions(-DNVM_X86_64)
elseif (ARCH STREQUAL "i386")
  add_definitions(-DHYX86_64)
  add_definitions(-DNVM_I386)
endif()

add_definitions(-DIPv6_FUNCTION_SUPPORT)

SET(INSTALL_DIR $ENV{HOME}/.nullvm/lib/${OS}/${ARCH})

add_subdirectory(core/src)
add_subdirectory(bc/src)
add_subdirectory(hyprt/src)
add_subdirectory(rt/src)

