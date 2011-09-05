cmake_minimum_required(VERSION 2.8)
project(build)
include(CheckCCompilerFlag)
include(ExternalProject)

enable_testing()

find_path(ZLIB_H_PATH zlib.h)
if(NOT ZLIB_H_PATH)
  message(FATAL_ERROR "zlib.h not found")
endif()

if(CMAKE_SYSTEM_NAME MATCHES "Linux")
  set(LINUX YES)
elseif(CMAKE_SYSTEM_NAME MATCHES "Darwin")
  set(DARWIN YES)
else()
  message(FATAL_ERROR "Unsupported OS: ${CMAKE_SYSTEM_NAME}")
endif()

if(NOT CMAKE_BUILD_TYPE)
   set(CMAKE_BUILD_TYPE "Debug" CACHE STRING "Choose the type of build, options are: Debug Release RelWithDebInfo MinSizeRel." FORCE)
endif()

if(NOT ARCH)
   set(ARCH "i386" CACHE STRING "Choose the architecture to build for, options are: i386 x86_64." FORCE)
endif()

if(NOT ARCH MATCHES "^(i386|x86_64)$")
  message(FATAL_ERROR "Unsupported ARCH: ${ARCH}")
endif()

if(DARWIN)
  set(EXTERNAL_PROJECT_CFLAGS "-arch ${ARCH}")
endif()
# TODO: Use CMake to build GC? There is a CMakeLists.txt but I cannot get the install to work.
ExternalProject_Add(extgc 
  URL "http://www.hpl.hp.com/personal/Hans_Boehm/gc/gc_source/gc-7.2alpha6.tar.gz"
  CONFIGURE_COMMAND bash -c "CFLAGS='${EXTERNAL_PROJECT_CFLAGS}' ../extgc/configure --prefix=${CMAKE_BINARY_DIR}/gc"
)
if(DARWIN)
  ExternalProject_Add_Step(
    extgc after_install 
    COMMAND rm -f "${CMAKE_BINARY_DIR}/gc/lib/libgc.dylib"
    COMMAND cp "${CMAKE_BINARY_DIR}/gc/lib/libgc.1.dylib" "${CMAKE_BINARY_DIR}/gc/lib/libgc.dylib"
    COMMAND install_name_tool -id @loader_path/libgc.dylib "${CMAKE_BINARY_DIR}/gc/lib/libgc.dylib"
    DEPENDEES install
    COMMENT "Running install_name_tool on libgc.dylib"
  )
endif()

include_directories(hyprt/include core/include ${ZLIB_H_PATH} ${CMAKE_BINARY_DIR}/gc/include)
link_directories(${CMAKE_BINARY_DIR}/gc/lib)
if(LINUX)
  add_definitions(-D_GNU_SOURCE)
endif()
add_definitions(-DGC_THREADS)
add_definitions(-include ${CMAKE_BINARY_DIR}/gc/include/gc/gc.h)
add_definitions(-DIPv6_FUNCTION_SUPPORT)
add_definitions(-fasynchronous-unwind-tables) # Generate eh frames for C code. Without it we cannot unwind.

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
  add_definitions(-DDARWIN)
  include_directories(hyprt/include/unix)
  # Prevent the linker on Mac OS X >= 10.6 from compacting FDEs since that breaks _Unwind_FindEnclosingFunction
  set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Xlinker -no_compact_unwind")
  set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Xlinker -no_compact_unwind")
  set(CMAKE_MODULE_LINKER_FLAGS "${CMAKE_MODULE_LINKER_FLAGS} -Xlinker -no_compact_unwind")
endif()

if (ARCH STREQUAL "x86_64")
  add_definitions(-DHYX86_64)
  add_definitions(-DNVM_X86_64)
  if(DARWIN)
    add_definitions(-arch x86_64)
  endif()
elseif (ARCH STREQUAL "i386")
  add_definitions(-DHYX86)
  add_definitions(-DNVM_I386)
  if(DARWIN)
    add_definitions(-arch i386)
  endif()
endif()

set(INSTALL_DIR $ENV{HOME}/.nullvm/lib/${OS}/${ARCH})

if(LINUX)
  install(FILES ${CMAKE_BINARY_DIR}/gc/lib/libgc.so.1.0.3 DESTINATION ${INSTALL_DIR} RENAME libgc.so.1)
elseif(DARWIN)
  install(FILES ${CMAKE_BINARY_DIR}/gc/lib/libgc.dylib DESTINATION ${INSTALL_DIR})
endif()

add_subdirectory(core/src)
add_subdirectory(bc/src)
add_subdirectory(hyprt/src)
add_subdirectory(rt/src)

