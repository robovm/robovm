project(android)

include_directories(
  system/core/include/arch/${OS_FAMILY}-${ARCH}
  system/core/include
  libnativehelper/include/nativehelper
)

add_definitions(-DICU_DATA_DIR="/usr/share/icu")
if(IOS AND X86)
  add_definitions(-DICU_DATA_DIR_PREFIX_ENV_VAR="IPHONE_SIMULATOR_ROOT")
endif()
add_definitions(-DHAVE_ANDROID_OS=1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -include AndroidConfig.h")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -include AndroidConfig.h")

add_subdirectory(dalvik)
add_subdirectory(libcore)
add_subdirectory(libnativehelper)
add_subdirectory(external/expat)
add_subdirectory(external/fdlibm)
add_subdirectory(external/icu4c)
add_subdirectory(external/javasqlite)
add_subdirectory(external/openssl)
add_subdirectory(external/sqlite)
add_subdirectory(external/zlib)
set(ANDROID_OBJECTS
  ${DALVIK_OBJECTS} 
  ${LIBCORE_OBJECTS} 
  ${LIBNATIVEHELPER_OBJECTS} 
  ${EXTERNAL_EXPAT_OBJECTS} 
  ${EXTERNAL_FDLIBM_OBJECTS}
  ${EXTERNAL_ICU4C_OBJECTS}
  ${EXTERNAL_JAVASQLITE_OBJECTS}
  ${EXTERNAL_OPENSSL_OBJECTS}
  ${EXTERNAL_SQLITE_OBJECTS}
  ${EXTERNAL_ZLIB_OBJECTS}
  PARENT_SCOPE
)

