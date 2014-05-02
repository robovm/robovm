cmake_minimum_required(VERSION 2.8)
project(robovm-libimobiledevice)
include(ExternalProject)

find_package(JNI REQUIRED)

if(OS STREQUAL "linux")
  set(LINUX YES)
elseif(OS STREQUAL "macosx")
  set(MACOSX YES)
endif()

if (ARCH STREQUAL "x86")
  set(X86 YES)
elseif (ARCH STREQUAL "x86_64")
  set(X86_64 YES)
endif()

set(C_CXX_FLAGS "${C_CXX_FLAGS} -fdata-sections -ffunction-sections")
if(MACOSX AND X86)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -arch i386 -mmacosx-version-min=10.6")
  set(LD_FLAGS "${LD_FLAGS} -arch i386 -mmacosx-version-min=10.6")
elseif(MACOSX AND X86_64)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -arch x86_64 -mmacosx-version-min=10.6")
  set(LD_FLAGS "${LD_FLAGS} -arch x86_64 -mmacosx-version-min=10.6")
elseif(LINUX AND X86)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -m32 -fPIC")
  set(LD_FLAGS "${LD_FLAGS} -m32")
elseif(LINUX AND X86_64)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -m64 -fPIC")
  set(LD_FLAGS "${LD_FLAGS} -m64")
endif()

if(MACOSX)
  exec_program(xcode-select ARGS --print-path OUTPUT_VARIABLE XCODE_PATH)
  set(SYSROOT "${XCODE_PATH}/Platforms/MacOSX.platform/Developer/SDKs/MacOSX10.9.sdk")
  if(NOT IS_DIRECTORY ${SYSROOT})
    set(SYSROOT "${XCODE_PATH}/Platforms/MacOSX.platform/Developer/SDKs/MacOSX10.8.sdk")
  endif()
  if(NOT IS_DIRECTORY ${SYSROOT})
    message(FATAL_ERROR "No supported MacOSX SDK found")
  endif()
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -isysroot ${SYSROOT}")
endif()

set(EXTPREFIX "${CMAKE_BINARY_DIR}/ext")
set(EXTCFLAGS "${C_CXX_FLAGS} -I${EXTPREFIX}/include -I${EXTPREFIX}/include/libusb-1.0 -I${EXTPREFIX}/include/libxml2")
ExternalProject_Add(extlibxml
  URL ftp://xmlsoft.org/libxml2/libxml2-2.9.1.tar.gz
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${EXTCFLAGS}' ./configure --enable-shared=no --prefix=${EXTPREFIX} --with-minimum=on --without-python --without-ftp --without-http --without-html --without-docbook --without-xpath --without-xinclude --without-xptr --without-c14n --without-catalog --without-valid --without-schemas --without-schematron --without-modules --without-lzma --without-zlib"
  BUILD_IN_SOURCE 1
)
ExternalProject_Add(extlibusbx
  URL http://kent.dl.sourceforge.net/project/libusbx/releases/1.0.16/source/libusbx-1.0.16.tar.bz2
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${EXTCFLAGS}' ./configure --enable-shared=no --prefix=${EXTPREFIX}"
  BUILD_IN_SOURCE 1
)
ExternalProject_Add(extlibplist
  URL http://www.libimobiledevice.org/downloads/libplist-1.11.tar.bz2
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${EXTCFLAGS}' PKG_CONFIG_PATH='${EXTPREFIX}/lib/pkgconfig' ./configure --enable-shared=no --prefix=${EXTPREFIX} --without-cython"
  BUILD_IN_SOURCE 1
  DEPENDS extlibxml
)
ExternalProject_Add(extlibusbmuxd
  URL http://www.libimobiledevice.org/downloads/libusbmuxd-1.0.9.tar.bz2
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${EXTCFLAGS}' PKG_CONFIG_PATH='${EXTPREFIX}/lib/pkgconfig' ./configure --enable-shared=no --prefix=${EXTPREFIX}"
  BUILD_IN_SOURCE 1
  DEPENDS extlibusbx extlibplist
)
set(LIBIMOBILEDEVICE_CFLAGS "${EXTCFLAGS}")
if(MACOSX)
  set(LIBIMOBILEDEVICE_CFLAGS "${EXTCFLAGS} -std=gnu89")
endif()
ExternalProject_Add(extlibimobiledevice
  URL http://www.libimobiledevice.org/downloads/libimobiledevice-1.1.6.tar.bz2
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${LIBIMOBILEDEVICE_CFLAGS}' LDFLAGS='-L${EXTPREFIX}/lib -lxml2' PKG_CONFIG_PATH='${EXTPREFIX}/lib/pkgconfig' ./configure --prefix ${EXTPREFIX} --enable-shared=no --without-cython"
  BUILD_IN_SOURCE 1
  DEPENDS extlibxml extlibplist extlibusbmuxd
)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${C_CXX_FLAGS}")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${C_CXX_FLAGS} -fno-rtti")
set(CMAKE_INSTALL_DO_STRIP YES)

include_directories(${JAVA_INCLUDE_PATH} ${JAVA_INCLUDE_PATH2} ${EXTPREFIX}/include)

set(LIBS -limobiledevice -lusbmuxd -lplist -lssl -lxml2 -lcrypto)
set(LDFLAGS -L${EXTPREFIX}/lib)

if(MACOSX)
  set(LIBS ${LIBS} -liconv)
endif()

set(SRC
  src/main/native/libimobiledevice_wrap.c
)

add_library(robovm-libimobiledevice SHARED ${SRC})
add_dependencies(robovm-libimobiledevice extlibimobiledevice)
target_link_libraries(robovm-libimobiledevice ${LIBS} ${LDFLAGS})
if(LINUX)
  set_target_properties(robovm-libimobiledevice PROPERTIES LINK_FLAGS "-Wl,--gc-sections -Wl,--version-script=${CMAKE_SOURCE_DIR}/src/main/native/exports_linux.sym")
elseif(MACOSX)
  set_target_properties(robovm-libimobiledevice PROPERTIES LINK_FLAGS "-Wl,-dead_strip -Wl,-exported_symbols_list -Wl,${CMAKE_SOURCE_DIR}/src/main/native/exports_macosx.sym")
endif()
set(INSTALL_DIR ${CMAKE_SOURCE_DIR}/src/main/resources/org/robovm/libimobiledevice/binding/${OS}/${ARCH})
install(TARGETS robovm-libimobiledevice DESTINATION ${INSTALL_DIR})
