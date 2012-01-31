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
  set(OS_FAMILY linux)
elseif(CMAKE_SYSTEM_NAME MATCHES "Darwin")
  set(DARWIN YES)
  set(OS_FAMILY darwin)
else()
  message(FATAL_ERROR "Unsupported OS: ${CMAKE_SYSTEM_NAME}")
endif()

if(NOT CMAKE_BUILD_TYPE)
  set(CMAKE_BUILD_TYPE "Debug" CACHE STRING "Choose the type of build, options are: Debug Release RelWithDebInfo MinSizeRel." FORCE)
endif()

if(NOT ARCH)
  set(ARCH "i386" CACHE STRING "Choose the architecture to build for, options are: i386 x86_64 arm." FORCE)
endif()

if(NOT ARCH MATCHES "^(i386|x86_64|arm)$")
  message(FATAL_ERROR "Unsupported ARCH: ${ARCH}")
endif()

if(DARWIN)
  if(NOT OS)
    set(OS "ios" CACHE STRING "Choose the os to build for, options are: macosx ios." FORCE)
  endif()
  if(NOT OS MATCHES "^(macosx|ios)$")
    message(FATAL_ERROR "Unsupported OS: ${OS}")
  endif()
  if (OS STREQUAL "macosx")
    set(MACOSX YES)
  else()
    set(IOS YES)
    if(ARCH STREQUAL "i386")
      set(DEFSYSROOT "/Developer/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator5.0.sdk")
    else()
      set(DEFSYSROOT "/Developer/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS5.0.sdk")
    endif()
    if(NOT SYSROOT)
      set(SYSROOT "${DEFSYSROOT}" CACHE STRING "The product will be built against the headers and libraries located inside the indicated SDK." FORCE)
    endif()
    if(SYSROOT STREQUAL "")
      message(FATAL_ERROR "Empty SYSROOT")
    endif()
  endif()
else()
  set(OS linux)
endif()

if(DARWIN)
  set(EXTGC_CFLAGS "-DNO_DYLD_BIND_FULLY_IMAGE -arch ${ARCH}")
  set(EXTGC_LDFLAGS "-no_implicit_dylibs")
  if(IOS)
    set(EXTGC_CFLAGS "${EXTGC_CFLAGS} -isysroot ${SYSROOT}")
    set(EXTGC_LDFLAGS "${EXTGC_LDFLAGS} -isysroot ${SYSROOT}")
  endif()
endif()
# TODO: Use CMake to build GC? There is a CMakeLists.txt but I cannot get the install to work.
ExternalProject_Add(extgc 
  URL "http://www.hpl.hp.com/personal/Hans_Boehm/gc/gc_source/gc-7.2alpha6.tar.gz"
  PATCH_COMMAND bash -c "patch -d ../extgc -p1 -i '${CMAKE_SOURCE_DIR}/gc-1.patch' && patch -d ../extgc -p1 -i '${CMAKE_SOURCE_DIR}/gc-2.patch'"
  CONFIGURE_COMMAND bash -c "CFLAGS='${EXTGC_CFLAGS}' LDFLAGS='${EXTGC_LDFLAGS}' ../extgc/configure --prefix=${CMAKE_BINARY_DIR}/gc"
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
add_definitions(-Wall)
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
  add_definitions(-DLINUX)
  include_directories(hyprt/include/unix)
elseif(DARWIN)
  # -no_compact_unwind prevents the linker on Mac OS X >= 10.6 from compacting FDEs since that breaks _Unwind_FindEnclosingFunction
  set(LINKER_FLAGS "-arch ${ARCH} -Xlinker -no_compact_unwind -Xlinker -no_implicit_dylibs")
  if(MACOSX)
    add_definitions(-DMACOSX)
  else()
    add_definitions(-DIOS)
    add_definitions(-isysroot ${SYSROOT})
    set(LINKER_FLAGS "${LINKER_FLAGS} -isysroot ${SYSROOT}")
  endif()
  SET(HY_OS macosx)
  add_definitions(-DDARWIN)
  add_definitions(-arch ${ARCH})
  include_directories(hyprt/include/unix)
  set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} ${LINKER_FLAGS}")
  set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} ${LINKER_FLAGS}")
  set(CMAKE_MODULE_LINKER_FLAGS "${CMAKE_MODULE_LINKER_FLAGS} ${LINKER_FLAGS}")
endif()

if (ARCH STREQUAL "x86_64")
  add_definitions(-DHYX86_64)
  add_definitions(-DNVM_X86_64)
elseif (ARCH STREQUAL "i386")
  add_definitions(-DHYX86)
  add_definitions(-DNVM_I386)
elseif (ARCH STREQUAL "arm")
  add_definitions(-DHYARM)
  add_definitions(-DNVM_ARM)
endif()

set(INSTALL_DIR ${CMAKE_SOURCE_DIR}/binaries/${OS}/${ARCH}/${CMAKE_BUILD_TYPE})

if(LINUX)
  install(FILES ${CMAKE_BINARY_DIR}/gc/lib/libgc.so.1.0.3 DESTINATION ${INSTALL_DIR} RENAME libgc.so.1)
elseif(DARWIN)
  install(FILES ${CMAKE_BINARY_DIR}/gc/lib/libgc.dylib DESTINATION ${INSTALL_DIR})
endif()

add_subdirectory(core/src)
add_subdirectory(bc/src)
add_subdirectory(hyprt/src)
add_subdirectory(rt/src)

